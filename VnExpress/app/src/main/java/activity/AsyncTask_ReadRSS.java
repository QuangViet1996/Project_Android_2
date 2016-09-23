package activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.vnexpress.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import adapter.vnexpressAdapter;
import model.VnExpress;

/**
 * Created by quang on 09/20/2016.
 */
public class AsyncTask_ReadRSS extends AsyncTask<String, Integer, String> {
    Activity context;
    ListView listView;
    vnexpressAdapter newsAdapter;
    public static ArrayList<VnExpress> arrayList_News;
    ArrayList<Bitmap> bitmaps;
    ProgressDialog progressDialog;
    String url = "";

    public AsyncTask_ReadRSS(Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        arrayList_News = new ArrayList<VnExpress>();
        bitmaps = new ArrayList<Bitmap>();
        Log.d("test", "on get data");
        progressDialog = progressDialog.show(context, "", "Loading...");
    }

    @Override
    protected String doInBackground(String... params) {
        url = params[0];
        Log.d("test", "url:" + url);
        try {
            String title, arrDescription, description, link, date, image = "error";

            Document doc = Jsoup.connect(url).get();

            Elements elements = doc.select("item");
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);

                link = element.select("link").text();
//                if (checkLink(link)) {
//                    Document doc_image = Jsoup.connect(link).get();
//                    Element tbody = doc_image.select("tbody img").first();
                arrDescription = element.select("description").text();

//                    if (tbody == null) {
//                        image = getImage(arrDescription);
//                    } else if (checkVideo(arrDescription)) {
//                        image = getImage(arrDescription);
//                    } else {
//                        Log.d("test", "Check image in tbody: " + tbody.toString().indexOf("img"));
//                        if (tbody.toString().indexOf("img") == -1) {
//                            image = doc_image.select("parser_body").first().select("img").attr("src");
//                        } else {
//                            image = tbody.select("img").attr("src");
//                        }
//                    }
                image = getImage(arrDescription);
                title = element.select("title").text();
                description = getDescription(arrDescription);
                date = element.select("pubDate").text();

                VnExpress news = new VnExpress();

                news.setLink(link);
                news.setImage(image);
                news.setTitle(title);
                news.setDescription(description);
                news.setDate(date);
                URL url = new URL(image);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                bitmaps.add(bitmap);
                arrayList_News.add(news);
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        for (int i = 0; i < arrayList_News.size(); i++) {
            Log.d("test", "Size arraylist " + arrayList_News.get(i).getTitle() + arrayList_News.get(i).getDescription() + arrayList_News.get(1).getDate());

        }
        newsAdapter = new vnexpressAdapter(context, R.layout.item_display, arrayList_News,bitmaps);
        Log.d("test", "News Adapter " + newsAdapter.toString());
        listView = (ListView) context.findViewById(R.id.listview);
        listView.setAdapter(newsAdapter);
    }

    private String getImage(String description) {
        String image = "error";
        int start = description.indexOf("src=\"http://");
        int end = description.indexOf("\" >");

        if ((start != -1) && (end != -1)) {
            image = description.substring(start + 5, end);
        }
        return image;
    }

    private String getDescription(String descriptionData) {
        int start = descriptionData.indexOf("</br>");
        int end = descriptionData.indexOf(".");
        String description = "error";
        if ((start != -1) && (end != -1)) {
            description = descriptionData.substring(start + 5);
        }
        return description;
    }

    private boolean checkLink(String link) {
        boolean result = false;
        int check_link = link.indexOf("vnexpress.net");
        if (check_link != -1) {
            result = true;
        }
        return result;
    }

    private boolean checkVideo(String link) {
        boolean result = false;
        int check_video = link.indexOf("video");
        Log.d("test", "check link: " + check_video);
        if (check_video != -1) {
            result = true;
        }
        return result;
    }


}

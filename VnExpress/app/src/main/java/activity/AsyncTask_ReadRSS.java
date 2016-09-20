package activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import adapter.vnexpressAdapter;
import model.VnExpress;

/**
 * Created by quang on 09/20/2016.
 */
public class AsyncTask_ReadRSS extends AsyncTask<String, Integer, String>{
    Activity context;
    ListView listView;
    TextView txtTitle;
    vnexpressAdapter newsAdapter;
    ArrayList arrayList_News;
    ProgressDialog mProgressDialog;
    String url;
    String webTitle = "";

    public AsyncTask_ReadRSS(Activity context,ListView lv)
    {
        this.context=context;
        this.listView=lv;

    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        arrayList_News = new ArrayList();
        Log.d("test", "on get data");
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Jsoup example");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String title, arrDescription,description, link, date, image = "error";

            Document doc = Jsoup.connect(url).get();

            Elements elements = doc.select("item");
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);

                link = element.select("link").text();
                if (checkLink(link)) {
                    title = element.select("title").text();
                    arrDescription = element.select("description").text();
                    description = getDescription(arrDescription);
                    date = element.select("pubDate").text();

                    if (link != null) {
                        Log.d("test", "link: " + link);
                        Document doc_image = Jsoup.connect(link).get();
                        Element tbody = doc_image.select("tbody img").first();
                        Log.d("test", "data tbody: " + tbody);

                        if (tbody == null) {
                            Log.d("test","get image when data null");
                            Log.d("test","get image when data null" + description);
                            image = getImage(arrDescription);
                        }
                        else if(checkVideo(arrDescription)){
                            image = getImage(arrDescription);
                        }else {
                            Log.d("test","Check image in tbody: " + tbody.toString().indexOf("img"));
                            if(tbody.toString().indexOf("img") == -1){
                                image = doc_image.select("parser_body").first().select("img").attr("src");
                            }else {
                                image = tbody.select("img").attr("src");
                            }
                        }
                        Log.d("test", "image: " + image);
                    } else {
                        Log.d("test", "link not found");
                    }
                    Log.d("test", "title: " + title);
                    Log.d("test", "description: " + description);
                    VnExpress news = new VnExpress(title, description, date, link, image);
                    arrayList_News.add(news);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        mProgressDialog.dismiss();
        txtTitle.setText(webTitle);
        newsAdapter = new vnexpressAdapter(context, arrayList_News);
        listView.setAdapter(newsAdapter);
    }

    private String getImage(String description) {
//       Log.d("test","getImage: " + description);
        String image = "error";
        int start = description.indexOf("src=\"http://");
        int end = description.indexOf(".jpg");

        if (end == -1) {
            Log.d("test", "jpg not found");
            end = description.indexOf(".png");
        }
        if (end == -1) {
            Log.d("test", "png not found");
            end = description.indexOf(".gif");
        }
//        Log.d("test", "start:" + start + "");
//        Log.d("test", "end:" + end + "");
        if ((start != -1) && (end != -1)) {
            image = description.substring(start + 5, end + 4);
        }
        return image;
    }

    private String getDescription(String descriptionData) {
//        Log.d("test","getDescription: " + descriptionData);
        int start = descriptionData.indexOf("br");
        int end = descriptionData.indexOf(".");
//        Log.d("test", "start2:" + start + "");
//        Log.d("test", "end2:" + end + "");
        String description = "error";
        if ((start != -1) && (end != -1)) {
            description = descriptionData.substring(start + 3);
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

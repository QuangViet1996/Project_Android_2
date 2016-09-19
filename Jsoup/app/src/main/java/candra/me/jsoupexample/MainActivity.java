package candra.me.jsoupexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView txtTitle;
    NewsAdapter newsAdapter;
    ProgressDialog mProgressDialog;
    private static String url = "http://vnexpress.net/rss/tin-moi-nhat.rss";
    String webTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_news);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Jsoup example");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        new FetchData().execute();
    }

    private class FetchData extends AsyncTask<Void, Void, Void> {
        ArrayList arrayList_News;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList_News = new ArrayList();
            Log.d("test", "on get data");
        }

        @Override
        protected Void doInBackground(Void... params) {

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
                        News news = new News(title, description, date, link, image);
                        arrayList_News.add(news);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            txtTitle.setText(webTitle);
            newsAdapter = new NewsAdapter(MainActivity.this, arrayList_News);
            listView.setAdapter(newsAdapter);
        }
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

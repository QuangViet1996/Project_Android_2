package activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.VideoView;

import com.vnexpress.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import adapter.vnexpressAdapter;
import model.VnExpress;

/**
 * Created by quang on 09/21/2016.
 */

public class AsyncTask_VideoHTML extends AsyncTask<String, Integer, String> {
    Activity context;
    ListView listView;
    vnexpressAdapter newsAdapter;
   public static ArrayList<VnExpress> arrayList_Video;
    ProgressDialog progressDialog;
    String url = "http://video.vnexpress.net";
    VideoView videoView;
    Bundle bundle;
    public AsyncTask_VideoHTML(Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        arrayList_Video = new ArrayList<VnExpress>();
        Log.d("test", "on get data");
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("test", "url:" + url);
        try {
            String title_video ,link_video,link;

            Document doc = Jsoup.connect(url).get();
            Log.d("test", "url:" + doc);

            Elements elements = doc.select("div.item_video");
            Elements getVideo = doc.select("div#videoContainter");
            link_video = getVideo.select("video").attr("src");
            Log.d("test", "url:" + elements);
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                title_video = element.select("a").attr("title");
                link=element.select("a").attr("href");
                VnExpress news = new VnExpress();
                news.setLink(link);
                news.setVideo(link_video);
                news.setTitle(title_video);
                arrayList_Video.add(news);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        progressDialog.dismiss();
        for (int i = 0; i < arrayList_Video.size(); i++) {
            Log.d("test", "Size arraylist " + arrayList_Video.get(i).getTitle()+"link:"+arrayList_Video.get(i).getLink());

        }
        newsAdapter = new vnexpressAdapter(context, R.layout.item_video, arrayList_Video);
        Log.d("test", "News Adapter " + newsAdapter.toString());
        listView = (ListView) context.findViewById(R.id.lvhienthi);
        listView.setAdapter(newsAdapter);


    }




}

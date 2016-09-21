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

import adapter.video_Adapter;
import model.VnExpress;

/**
 * Created by quang on 09/21/2016.
 */

public class AsyncTask_VideoHTML extends AsyncTask<String, Integer, String> {
    Activity context;
    ListView listView;
    video_Adapter newsAdapter;
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
            String title_video, link_video, link;

            Document doc = Jsoup.connect(url).get();
            Log.d("test", "url:" + doc);
            VnExpress news = new VnExpress();

            Elements elements = doc.select("div.item_video");
            Elements getVideo = doc.select("div#main_player script");
            link_video = getVideo.toString().substring(getVideo.toString().indexOf("http://news.video."), getVideo.toString().indexOf(".mp4") + 4);
            title_video = elements.get(0).select("a").attr("title");
            link = elements.get(0).select("a").attr("href");
            news.setTitle(title_video);
            news.setLink(link);
            news.setVideo(link_video);
            arrayList_Video.add(news);
            Log.d("test","getVideo: " + link_video);

            for (int i = 1; i < elements.size(); i++) {
                Element element = elements.get(i);
                link = element.select("a").attr("href");

                Document document = (Document) Jsoup.connect(link).get();
                Elements data_video = document.select("div#main_player script");
                link_video = data_video.toString().substring(data_video.toString().indexOf("http://news.video"), data_video.toString().indexOf(".mp4") + 4);
                title_video = element.select("a").attr("title");
                Log.d("test","getVideo: " + link_video);
                VnExpress news2 = new VnExpress();
                news2.setLink(link);
                news2.setVideo(link_video);
                news2.setTitle(title_video);
                arrayList_Video.add(news2);
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
            Log.d("test", "Size arraylist " + arrayList_Video.get(i).getTitle() + "link:" + arrayList_Video.get(i).getLink());

        }
        newsAdapter = new video_Adapter(context, R.layout.item_video, arrayList_Video);
        Log.d("test", "News Adapter " + newsAdapter.toString());
        listView = (ListView) context.findViewById(R.id.lvhienthi);
        listView.setAdapter(newsAdapter);


    }


}

package adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.vnexpress.R;

import java.util.ArrayList;

import model.VnExpress;

/**
 * Created by Joyboy on 9/19/2016.
 */

public class video_Adapter extends ArrayAdapter<VnExpress> {

    Activity context;
    int resource;
    ArrayList<VnExpress> arr;

    public video_Adapter(Activity context, int resource, ArrayList<VnExpress> arr) {
        super(context, resource, arr);
        this.context = context;
        this.resource = resource;
        this.arr = arr;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        convertView = inflater.inflate(R.layout.item_video, null);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_video_title);
        txtTitle.setText( arr.get(position).getTitle());
        VideoView videoView= (VideoView) convertView.findViewById(R.id.videoView);
        videoView.setVideoPath(arr.get(position).getVideo());
        Log.d("test", "video:"+arr.get(position).getVideo());
        videoView.start();
        return convertView;
    }

    class ViewHolder {
        TextView txtTitle;
        VideoView videoView;
    }
}

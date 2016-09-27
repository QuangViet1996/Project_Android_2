package adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.vnexpress.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import model.VnExpress;

/**
 * Created by Joyboy on 9/19/2016.
 */

public class video_Adapter extends ArrayAdapter<VnExpress> {

    Activity context;
    int resource;
    ArrayList<VnExpress> arr;
    MediaController mediaController;


    public video_Adapter(Activity context, int resource, ArrayList<VnExpress> arr) {
        super(context, resource, arr);
        this.context = context;
        this.resource = resource;
        this.arr = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        if (arr.get(position).getImage() != null) {
            convertView = inflater.inflate(R.layout.item_video, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewItem);
            Picasso.with(context).load(arr.get(position).getImage())
                    .error(R.drawable.item).into(imageView);
        }
        if (arr.get(position).getTitle() != null) {
            convertView = inflater.inflate(R.layout.item_video_title, null);
            TextView txtTitle = (TextView) convertView.findViewById(R.id.videoTitle);
            txtTitle.setText(arr.get(position).getTitle());
        }
        return convertView;
    }

    class ViewHolder {
        TextView txtTitle;
        VideoView videoView;
    }
}

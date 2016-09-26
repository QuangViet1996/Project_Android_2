package adapter;

import android.app.Activity;
import android.graphics.Bitmap;
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

        convertView = inflater.inflate(R.layout.item_video, null);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_video_title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        txtTitle.setText(arr.get(position).getTitle());
        imageView.setImageBitmap(arr.get(position).getImage());
        return convertView;
    }

    class ViewHolder {
        TextView txtTitle;
        VideoView videoView;
    }
}

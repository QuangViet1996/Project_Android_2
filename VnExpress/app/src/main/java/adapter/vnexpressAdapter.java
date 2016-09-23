package adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnexpress.R;

import java.util.ArrayList;

import model.VnExpress;

/**
 * Created by Joyboy on 9/19/2016.
 */

public class vnexpressAdapter extends ArrayAdapter<VnExpress> {

    Activity context;
    int resource;
    ArrayList<VnExpress> arr;
    ArrayList<Bitmap> bitmaps;

    public vnexpressAdapter(Activity context, int resource, ArrayList<VnExpress> arr, ArrayList<Bitmap> bitmaps) {
        super(context, resource, arr);
        this.context = context;
        this.resource = resource;
        this.arr = arr;
        this.bitmaps = bitmaps;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        convertView = inflater.inflate(R.layout.item_display, null);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txt_time);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
//        Picasso.with(context).load(arr.get(position).getImage())
//                .error(R.drawable.item).into(imageView);
        imageView.setImageBitmap(bitmaps.get(position));
        Log.d("test","titleadapter: " + arr.get(position) + arr.get(position).getTitle());
        txtTitle.setText( arr.get(position).getTitle());
        txtDescription.setText( arr.get(position).getDescription());
        txtDate.setText( arr.get(position).getDate());

        return convertView;
    }

    class ViewHolder {
        TextView txtTitle, txtDescription;
        ImageView img;
    }
}

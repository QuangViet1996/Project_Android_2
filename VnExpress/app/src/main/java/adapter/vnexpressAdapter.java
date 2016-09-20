package adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnexpress.R;

import java.util.ArrayList;

import model.VnExpress;

/**
 * Created by Joyboy on 9/19/2016.
 */
public class vnexpressAdapter extends BaseAdapter {
    Context context;
    ArrayList<VnExpress> arrayList;
    LayoutInflater inflater;

    public vnexpressAdapter(Context context, ArrayList<VnExpress> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public VnExpress getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_display, null);

        VnExpress news = getItem(position);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);

        Log.d("test","titleadapter: " + news.getTitle());
        txtTitle.setText(news.getTitle());
        txtDescription.setText(news.getDescription());
//        Picasso.with(context).load(arrayList.get(position).getImage())
//                .placeholder(R.drawable.newspaper).error(R.drawable.newspaper).into(imageView);

        return convertView;
    }

    class ViewHolder {
        TextView txtTitle, txtDescription;
        ImageView img;
    }
}

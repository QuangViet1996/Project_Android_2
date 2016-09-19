package candra.me.jsoupexample;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by candra on 16-Apr-16.
 */
public class NewsAdapter extends BaseAdapter {

    Context context;
    ArrayList<News> arrayList;
    LayoutInflater inflater;

    public NewsAdapter(Context context, ArrayList<News> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public News getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.news_item_layout, null);

        News news = getItem(position);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);



        txtTitle.setText(Html.fromHtml(news.getTitle()));
        txtDescription.setText(Html.fromHtml(news.getDescription()));
        Log.d("test","image adapter: " + arrayList.get(position).getImage());
        Picasso.with(context).load(arrayList.get(position).getImage())
                .placeholder(R.drawable.icon_bai_bao).error(R.drawable.icon_bai_bao).into(imageView);

        return convertView;
    }

    class ViewHolder {
        TextView txtTitle, txtDescription;
        ImageView img;
    }
}

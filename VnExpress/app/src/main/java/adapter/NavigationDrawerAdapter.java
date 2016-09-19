package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnexpress.R;


/**
 * Created by on 11/12/2015.
 */
public class NavigationDrawerAdapter extends BaseAdapter{
    Context context;
    String[] content;
    int[] imagecontent;

    public NavigationDrawerAdapter(Context context, String[] content, int[] imagecontent)
    {
        this.context = context;
        this.content = content;
        this.imagecontent = imagecontent;
    }

    @Override
    public int getCount() {
        return content.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_display,null);

        ImageView imgview = (ImageView) view.findViewById(R.id.img);
        TextView txttitle = (TextView) view.findViewById(R.id.txt_title);
        TextView txtdiscription = (TextView) view.findViewById(R.id.txt_description);
        TextView txttime = (TextView) view.findViewById(R.id.txt_time);

        txttitle.setText(content[position]);
        imgview.setImageResource(imagecontent[position]);

        return view;
    }
}

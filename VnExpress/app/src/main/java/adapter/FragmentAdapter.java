package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vnexpress.R;

/**
 * Created by Son on 11/12/2015.
 */
public class FragmentAdapter extends BaseAdapter{
    Context context;
    String[] fra_mang;
    public FragmentAdapter(Context context, String[] fra_mang)
    {
        this.context =context;
        this.fra_mang = fra_mang;
    }
    @Override
    public int getCount() {
        return fra_mang.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_display, parent, false);
        return null;
    }
}

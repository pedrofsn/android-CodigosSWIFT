package banks.swift.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public abstract class AdapterGeneric extends BaseAdapter {

    final Context context;
    final Object[] arrayItem;

    public AdapterGeneric(Context context, Object[] arrayItem) {
        this.context = context;
        this.arrayItem = arrayItem;
    }

    @Override
    public int getCount() {
        return arrayItem.length;
    }

    @Override
    public Object getItem(int position) {
        return arrayItem[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View v, ViewGroup parent);

}

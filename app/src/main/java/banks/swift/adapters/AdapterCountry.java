package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import banks.swift.R;

public class AdapterCountry extends BaseAdapter {

    private final Context context;
    private final Object[] arrayItem;

    public AdapterCountry(Context context, Object[] arrayItem) {
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
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder viewHolder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.adapter_country, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textViewCountry = (TextView) v.findViewById(R.id.textViewCountry);

            v.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        String countryName = (String) arrayItem[position];

        if (countryName != null) {
            viewHolder.textViewCountry.setText(countryName);
            v.setTag(viewHolder);
        }

        return v;
    }

    public static class ViewHolder {
        public TextView textViewCountry;
    }
}
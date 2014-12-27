package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import banks.swift.R;

public class AdapterCountry extends AdapterGeneric {

    public AdapterCountry(Context context, Object[] arrayItem) {
        super(context, arrayItem);
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
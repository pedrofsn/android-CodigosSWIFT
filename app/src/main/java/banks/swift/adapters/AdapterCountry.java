package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import banks.swift.R;

public class AdapterCountry extends BaseAdapter {

    private Context context;
    private List<String> list;

    public AdapterCountry(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder viewHolder;

        if (v == null) {

            // inflate the layout
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.adapter_country, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.textViewCountry = (TextView) v.findViewById(R.id.textViewCountry); 

            // store the holder with the view.
            v.setTag(viewHolder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolder) v.getTag();
        }

        String nomeDoPais = list.get(position);
        if (nomeDoPais != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            viewHolder.textViewCountry.setText(nomeDoPais);
            v.setTag(viewHolder);
        }

        return v;
    }

    public static class ViewHolder {
        public TextView textViewCountry; 
    } 
}
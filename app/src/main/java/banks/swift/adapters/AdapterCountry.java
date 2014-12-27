package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import banks.swift.R;
import banks.swift.adapters.viewholder.ViewHolderCountry;

public class AdapterCountry extends AdapterGeneric {

    public AdapterCountry(Context context, Object[] arrayItem) {
        super(context, arrayItem);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolderCountry viewHolderCountry;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.adapter_country, parent, false);
            viewHolderCountry = new ViewHolderCountry(v);
            v.setTag(viewHolderCountry);

        } else {
            viewHolderCountry = (ViewHolderCountry) v.getTag();
        }

        viewHolderCountry.populate((String) arrayItem[position]);
        v.setTag(viewHolderCountry);

        return v;
    }
}
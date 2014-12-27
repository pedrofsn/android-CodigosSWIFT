package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import banks.swift.R;
import banks.swift.adapters.viewholder.ViewHolderBank;
import banks.swift.model.Bank;

public class AdapterBank extends AdapterGeneric {

    public AdapterBank(Context context, Object[] arrayItem) {
        super(context, arrayItem);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolderBank viewHolder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.adapter_bank, parent, false);
            viewHolder = new ViewHolderBank(v);
            v.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolderBank) v.getTag();
        }

        viewHolder.populate((Bank) arrayItem[position]);
        v.setTag(viewHolder);

        return v;
    }

}
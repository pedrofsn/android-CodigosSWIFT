package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import banks.swift.R;
import banks.swift.model.Bank;

public class AdapterBank extends AdapterGeneric {

    public AdapterBank(Context context, Object[] arrayItem) {
        super(context, arrayItem);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder viewHolder;

        if (v == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.adapter_bank, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textViewBankName = (TextView) v.findViewById(R.id.textViewBankName);
            viewHolder.textViewBankRegion = (TextView) v.findViewById(R.id.textViewBankRegion);
            viewHolder.textViewBankSwift = (TextView) v.findViewById(R.id.textViewBankSwift);
            viewHolder.textViewBankBranch = (TextView) v.findViewById(R.id.textViewBankBranch);
            viewHolder.rowBranch = (TableRow) v.findViewById(R.id.rowBranch);

            v.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        Bank objectItem = (Bank) arrayItem[position];

        if (objectItem != null) {

            String name = objectItem.getName();
            String city = objectItem.getCity();
            String swift = objectItem.getSwift();
            String branch = objectItem.getBranch();

            viewHolder.textViewBankName.setText(name);
            viewHolder.textViewBankRegion.setText(city);
            viewHolder.textViewBankSwift.setText(swift);

            if (!"".equals(branch)) {
                viewHolder.textViewBankBranch.setText(branch);
            } else {
                viewHolder.rowBranch.setVisibility(View.GONE);
            }

            v.setTag(viewHolder);
        }

        return v;
    }

    public static class ViewHolder {
        public TextView textViewBankName;
        public TextView textViewBankRegion;
        public TextView textViewBankSwift;
        public TextView textViewBankBranch;
        public TableRow rowBranch;
    }

}
package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import banks.swift.R;
import banks.swift.model.Bank;

public class AdapterBank extends BaseAdapter {

    private Context context;
    private List<Bank> listBanks;

    public AdapterBank(Context context, List<Bank> listBanks) {
        this.context = context;
        this.listBanks = listBanks;
    }

    @Override
    public int getCount() {
        return listBanks.size();
    }

    @Override
    public Object getItem(int position) {
        return listBanks.get(position);
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

        Bank objectItem = listBanks.get(position);

        if (objectItem != null) {

            String name = listBanks.get(position).getName();
            String city = listBanks.get(position).getCity();
            String swift = listBanks.get(position).getSwift();
            String branch = listBanks.get(position).getBranch();

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
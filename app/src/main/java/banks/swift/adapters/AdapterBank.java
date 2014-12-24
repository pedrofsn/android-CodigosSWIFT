package banks.swift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

            // inflate the layout
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.adapter_bank, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.textViewBankName = (TextView) v.findViewById(R.id.textViewBankName);
            viewHolder.textViewBankRegion = (TextView) v.findViewById(R.id.textViewBankRegion);
            viewHolder.textViewBankSwift = (TextView) v.findViewById(R.id.textViewBankSwift);
            viewHolder.textViewBankBranch = (TextView) v.findViewById(R.id.textViewBankBranch);

            // store the holder with the view.
            v.setTag(viewHolder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolder) v.getTag();
        }

        // object item based on the position
        Bank objectItem = listBanks.get(position);

        // assign values if the object is not null
        if (objectItem != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            viewHolder.textViewBankName.setText(listBanks.get(position).getName());
            viewHolder.textViewBankRegion.setText(listBanks.get(position).getCity());
            viewHolder.textViewBankSwift.setText(listBanks.get(position).getSwift());
            viewHolder.textViewBankBranch.setText(listBanks.get(position).getBranch());
            v.setTag(viewHolder);
        }

        return v;
    }

    public static class ViewHolder {
        public TextView textViewBankName;
        public TextView textViewBankRegion;
        public TextView textViewBankSwift;
        public TextView textViewBankBranch;
    }

    /*

    private List<Bank> listBanks;

    public AdapterBank(List<Bank> list) {
        listBanks = list;
    }

    @Override
    public AdapterBank.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_bank, parent, false);

        ViewHolder holder = new ViewHolder(v);
        holder.textViewBankName.setTag(holder);
        holder.textViewBankRegion.setTag(holder);
        holder.textViewBankSwift.setTag(holder);
        holder.textViewBankBranch.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewBankName.setText(listBanks.get(position).getName());
        holder.textViewBankRegion.setText(listBanks.get(position).getRegion());
        holder.textViewBankSwift.setText(listBanks.get(position).getSwift());
        holder.textViewBankBranch.setText(listBanks.get(position).getBranch());
    }

    @Override
    public int getItemCount() {
        return listBanks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBankName;
        public TextView textViewBankRegion;
        public TextView textViewBankSwift;
        public TextView textViewBankBranch;

        public ViewHolder(View v) {
            super(v);
            textViewBankName = (TextView) v.findViewById(R.id.textViewBankName);
            textViewBankRegion = (TextView) v.findViewById(R.id.textViewBankRegion);
            textViewBankSwift = (TextView) v.findViewById(R.id.textViewBankSwift);
            textViewBankBranch = (TextView) v.findViewById(R.id.textViewBankBranch);
        }
    }

     */
}
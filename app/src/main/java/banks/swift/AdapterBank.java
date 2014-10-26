package banks.swift;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterBank extends RecyclerView.Adapter<AdapterBank.ViewHolder> {

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
}
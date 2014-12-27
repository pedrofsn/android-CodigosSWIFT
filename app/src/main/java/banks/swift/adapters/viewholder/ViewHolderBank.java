package banks.swift.adapters.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import banks.swift.R;
import banks.swift.model.Bank;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public class ViewHolderBank {

    private View v;
    private TextView textViewBankName;
    private TextView textViewBankRegion;
    private TextView textViewBankSwift;
    private TextView textViewBankBranch;
    private TableRow rowBranch;

    public TextView getTextViewBankName() {
        if (textViewBankName == null) {
            textViewBankName = (TextView) v.findViewById(R.id.textViewBankName);
        }
        return textViewBankName;
    }

    public TextView getTextViewBankRegion() {
        if (textViewBankRegion == null) {
            textViewBankRegion = (TextView) v.findViewById(R.id.textViewBankRegion);
        }
        return textViewBankRegion;
    }

    public TextView getTextViewBankSwift() {
        if (textViewBankSwift == null) {
            textViewBankSwift = (TextView) v.findViewById(R.id.textViewBankSwift);
        }
        return textViewBankSwift;
    }

    public TextView getTextViewBankBranch() {
        if (textViewBankBranch == null) {
            textViewBankBranch = (TextView) v.findViewById(R.id.textViewBankBranch);
        }
        return textViewBankBranch;
    }

    public TableRow getRowBranch() {
        if (rowBranch == null) {
            rowBranch = (TableRow) v.findViewById(R.id.rowBranch);
        }
        return rowBranch;
    }

    public ViewHolderBank(View v) {
        this.v = v;
        textViewBankName = getTextViewBankName();
        textViewBankRegion = getTextViewBankRegion();
        textViewBankSwift = getTextViewBankSwift();
        textViewBankBranch = getTextViewBankBranch();
        rowBranch = getRowBranch();
        v = this.v;
    }

    public void populate(Bank objectItem) {
        if (objectItem != null) {

            String name = objectItem.getName();
            String city = objectItem.getCity();
            String swift = objectItem.getSwift();
            String branch = objectItem.getBranch();

            textViewBankName.setText(name);
            textViewBankRegion.setText(city);
            textViewBankSwift.setText(swift);

            if (!"".equals(branch)) {
                textViewBankBranch.setText(branch);
            } else {
                rowBranch.setVisibility(View.GONE);
            }
        }
    }
}

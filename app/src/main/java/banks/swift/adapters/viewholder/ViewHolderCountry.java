package banks.swift.adapters.viewholder;

import android.view.View;
import android.widget.TextView;

import banks.swift.R;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public class ViewHolderCountry {

    private View v;
    private TextView textViewCountry;

    public ViewHolderCountry(View v) {
        this.v = v;
        textViewCountry = getTextViewCountry();
    }

    public TextView getTextViewCountry() {
        if (textViewCountry == null) {
            textViewCountry = (TextView) v.findViewById(R.id.textViewCountry);
        }
        return textViewCountry;
    }

    public void populate(String objectItem) {
        if (objectItem != null) {
            textViewCountry.setText(objectItem.toUpperCase());
        }
    }
}

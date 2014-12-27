package banks.swift.fragment;

import android.view.View;

import banks.swift.R;
import banks.swift.adapters.AdapterBank;
import banks.swift.adapters.AdapterCountry;
import banks.swift.model.Bank;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public class Teste extends FragmentGeneric {

    @Override
    public void setAdapter() {
        adapter = new AdapterBank(getActivity(), mArray);
    }

    @Override
    public void updateListView(Object adapter) {
        if (adapter != null) {
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            listView.setAdapter((AdapterBank) adapter);
        }
    }

    @Override
    public void restartSearch() {
        updateListView(new AdapterBank(getActivity(), mArray));
    }

    @Override
    public void showSearchResults(Object[] result) {
        if (result != null && result.length >= 1) {
            if (mArray == null) {
                mArray = (Object[]) result;
            }
            updateListView(new AdapterBank(getActivity(), (Object[]) result));
        } else {
            Crouton.makeText(getActivity(), getString(R.string.sem_resultados), Style.ALERT).show();
        }
    }
}

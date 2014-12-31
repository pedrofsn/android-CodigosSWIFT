package banks.swift.fragment;

import android.view.View;
import android.widget.AdapterView;

import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterCountry;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public class FragmentCountries extends FragmentGeneric implements AdapterView.OnItemClickListener {

    @Override
    public void setAdapter() {
        mAdapter = new AdapterCountry(getActivity(), mArray);
    }

    @Override
    public void updateListView(Object adapter) {
        if (adapter != null) {
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            listView.setAdapter((AdapterCountry) adapter);
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void restartSearch() {
        updateListView(new AdapterCountry(getActivity(), mArray));
    }

    @Override
    public void showResults(Object[] result) {
        if (result != null && result.length >= 1) {
            if (mArray == null) {
                mArray = (Object[]) result;
            }
            updateListView(new AdapterCountry(getActivity(), (Object[]) result));
        } else {
            Crouton.makeText(getActivity(), getString(R.string.sem_resultados), Style.ALERT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ActivityMain) getActivity()).changeFragment(((String) listView.getAdapter().getItem(position)));
    }
}

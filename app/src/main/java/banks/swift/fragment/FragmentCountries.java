package banks.swift.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterCountry;
import banks.swift.asynctasks.AsyncTaskLoad;
import banks.swift.asynctasks.AsyncTaskSearch;
import banks.swift.interfaces.Searchable;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class FragmentCountries extends Fragment implements AdapterView.OnItemClickListener, Searchable {

    private ListView listView;
    private ProgressBar progressBar;

    private Object[] mArray;
    private AdapterCountry adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mArray = (Object[]) savedInstanceState.get("mArray");
            adapter = new AdapterCountry(getActivity(), mArray);
        } else {
            AsyncTaskLoad asyncTask = new AsyncTaskLoad(getActivity(), this);
            asyncTask.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateListView(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("mArray", (String[]) mArray);
    }

    @Override
    public void onLoading() {
        if (listView != null && progressBar != null) {
            listView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ActivityMain) getActivity()).changeFragment(((String) listView.getAdapter().getItem(position)));
    }

    private void updateListView(AdapterCountry adapter) {
        if (adapter != null) {
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void search(String query) {
        new AsyncTaskSearch(this, mArray, query).execute();
    }

    @Override
    public void restartSearch() {
        updateListView(new AdapterCountry(getActivity(), mArray));
    }

    @Override
    public void showSearchResults(Object[] result) {
        if (result != null && result.length >= 1) {
            if (mArray == null) {
                mArray = (Object[]) result;
            }
            updateListView(new AdapterCountry(getActivity(), (Object[]) result));
        } else {
            Crouton.makeText(getActivity(), getString(R.string.sem_resultados), Style.ALERT).show();
        }
    }
}

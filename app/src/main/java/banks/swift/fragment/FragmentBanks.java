package banks.swift.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterBank;
import banks.swift.asynctasks.AsyncTaskLoad;
import banks.swift.asynctasks.AsyncTaskSearch;
import banks.swift.interfaces.Searchable;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class FragmentBanks extends Fragment implements Searchable {

    private ListView listView;
    private ProgressBar progressBar;

    private Object[] mArray;
    private AdapterBank adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mArray = (Object[]) savedInstanceState.get("mArray");
            adapter = new AdapterBank(getActivity(), mArray);
        } else {
            AsyncTaskLoad asyncTask = new AsyncTaskLoad(getActivity(), this);
            asyncTask.execute(((ActivityMain) getActivity()).getCountry());
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
    public void onLoading() {
        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mArray", mArray);
    }

    private void updateListView(AdapterBank adapter) {
        if (adapter != null) {
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void search(String query) {
        new AsyncTaskSearch(this, mArray, query).execute();
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

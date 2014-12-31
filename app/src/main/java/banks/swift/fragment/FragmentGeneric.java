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
import banks.swift.asynctasks.AsyncTaskLoad;
import banks.swift.asynctasks.AsyncTaskSearch;
import banks.swift.interfaces.Searchable;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public abstract class FragmentGeneric extends Fragment implements Searchable {

    public ListView listView;
    public ProgressBar progressBar;

    public Object[] mArray;
    public Object mAdapter;

    public abstract void setAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mArray = (Object[]) savedInstanceState.get("mArray");
            setAdapter();
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
        updateListView(mAdapter);
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

    @Override
    public void search(String query) {
        new AsyncTaskSearch(this, mArray, query.trim()).execute();
    }

    public abstract void updateListView(Object adapter);

    public abstract void restartSearch();

    public abstract void showResults(Object[] result);


}

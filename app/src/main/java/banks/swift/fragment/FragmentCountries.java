package banks.swift.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import banks.swift.ActivitySearchable;
import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterCountry;
import banks.swift.asynctasks.AsyncTaskLoadCountries;
import banks.swift.interfaces.Loadable;
import banks.swift.interfaces.Searchable;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class FragmentCountries extends Fragment implements AdapterView.OnItemClickListener, Searchable, Loadable {

    private ListView listView;

    private String[] arrayCountries;
    private AdapterCountry adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            arrayCountries = (String[]) savedInstanceState.get("arrayCountries");
            adapter = new AdapterCountry(getActivity(), arrayCountries);
        } else {
            AsyncTaskLoadCountries asyncTask = new AsyncTaskLoadCountries(getActivity(), this);
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
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            updateListView(adapter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("arrayCountries", arrayCountries);
    }

    @Override
    public void onLoaded(Object result) {
        if (result != null) {
            this.arrayCountries = (String[]) result;
            adapter = new AdapterCountry(getActivity(), (String[]) result);
            updateListView(adapter);
        } else {
            Crouton.makeText(getActivity(), getString(R.string.ops_ocorreu_um_erro), Style.ALERT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ActivityMain) getActivity()).changeFragment(((String) listView.getAdapter().getItem(position)));
    }

    private void updateListView(AdapterCountry adapter) {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void search(String query) {
        updateListView(new AdapterCountry(getActivity(), (String[]) ((ActivitySearchable) getActivity()).search(arrayCountries, query)));
    }

    @Override
    public void restartSearch() {
        updateListView(adapter);
    }
}

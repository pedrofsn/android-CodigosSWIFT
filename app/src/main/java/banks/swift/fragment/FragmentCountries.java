package banks.swift.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterCountry;
import banks.swift.asynctasks.AsyncTaskLoadCountries;
import banks.swift.interfaces.Loadable;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class FragmentCountries extends Fragment implements AdapterView.OnItemClickListener, Loadable {

    private ListView listView;

    private ArrayList<String> countries;
    private AdapterCountry adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            countries = (ArrayList<String>) savedInstanceState.get("countries");
            adapter = new AdapterCountry(getActivity(), countries);
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
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("countries", countries);
    }

    @Override
    public void onLoaded(Object result) {
        if (result != null) {
            this.countries = (ArrayList<String>) result;
            listView.setAdapter(new AdapterCountry(getActivity(), (ArrayList<String>) result));
            listView.setOnItemClickListener(this);
        } else {
            Crouton.makeText(getActivity(), getString(R.string.ops_ocorreu_um_erro), Style.ALERT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ActivityMain) getActivity()).changeFragment(((String) listView.getAdapter().getItem(position)));
    }
}

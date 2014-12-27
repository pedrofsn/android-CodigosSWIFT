package banks.swift.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import banks.swift.ActivitySearchable;
import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterBank;
import banks.swift.asynctasks.AsyncTaskLoadBanks;
import banks.swift.interfaces.Loadable;
import banks.swift.interfaces.Searchable;
import banks.swift.model.Bank;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class FragmentBanks extends Fragment implements Searchable, Loadable {

    private ListView listView;

    private Bank[] arrayBanks;
    private AdapterBank adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            arrayBanks = (Bank[]) savedInstanceState.get("banks");
            adapter = new AdapterBank(getActivity(), arrayBanks);
        } else {
            AsyncTaskLoadBanks asyncTask = new AsyncTaskLoadBanks(getActivity(), this);
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
    }

    @Override
    public void onStart() {
        super.onStart();
        updateListView(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("banks", arrayBanks);
    }

    @Override
    public void onLoaded(Object reult) {
        if (reult != null) {
            this.arrayBanks = (Bank[]) reult;
            adapter = new AdapterBank(getActivity(), (Bank[]) reult);
            updateListView(adapter);
        } else {
            Crouton.makeText(getActivity(), getString(R.string.ops_ocorreu_um_erro), Style.ALERT).show();
        }
    }

    private void updateListView(AdapterBank adapter) {
        if (adapter != null) {
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void search(String query) {
        updateListView(new AdapterBank(getActivity(), (Bank[]) ((ActivitySearchable) getActivity()).search(arrayBanks, query)));
    }

    @Override
    public void restartSearch() {
        updateListView(adapter);
    }
}

package banks.swift.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import banks.swift.R;
import banks.swift.activities.ActivityMain;
import banks.swift.adapters.AdapterCountry;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class FragmentCountries extends Fragment {

    private ArrayList<String> countries;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countries = savedInstanceState != null ? (ArrayList<String>) savedInstanceState.get("countries") : lerArquivos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    public void onStart() {
        super.onStart();

        listView.setAdapter(new AdapterCountry(getActivity(), countries));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ActivityMain) getActivity()).altetarFragment(((String) listView.getAdapter().getItem(position)));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("countries", countries);
    }

    private ArrayList<String> lerArquivos() {
        ArrayList<String> paises = new ArrayList<String>();
        AssetManager assetManager = getActivity().getAssets();
        try {
            String[] filelist = assetManager.list("");

            for (String nomeDoPais : filelist) {
                if (nomeDoPais.endsWith(".json")) {
                    paises.add(nomeDoPais.replace(".json", "").trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return paises;
    }
}

package banks.swift.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import banks.swift.R;
import banks.swift.adapters.AdapterCountry;

public class ActivityCountries extends ActionBarActivity {

    private ArrayList<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        final ListView listView = (ListView) findViewById(R.id.listView);

        countries = savedInstanceState != null ? (ArrayList<String>) savedInstanceState.get("countries") : lerArquivos();

        listView.setAdapter(new AdapterCountry(this, countries));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityCountries.this, ActivityBanks.class);
                intent.putExtra("country", ((String) listView.getAdapter().getItem(position)));
                startActivity(intent);
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
        AssetManager assetManager = getAssets();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

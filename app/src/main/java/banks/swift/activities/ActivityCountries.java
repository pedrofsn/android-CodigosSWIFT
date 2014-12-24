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
import java.util.List;

import banks.swift.R;
import banks.swift.adapters.AdapterCountry;

public class ActivityCountries extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new AdapterCountry(this, lerArquivos()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityCountries.this, ActivityBanks.class);
                intent.putExtra("pais", ((String) listView.getAdapter().getItem(position)));
                startActivity(intent);
            }
        });
    }

    private List<String> lerArquivos() {
        List<String> paises = new ArrayList<String>();
        AssetManager assetManager = getAssets();
        try {
            String[] filelist = assetManager.list("");

            if (filelist != null) {
                for (String nomeDoPais : filelist) {
                    if (nomeDoPais.endsWith(".json")) {
                        paises.add(nomeDoPais.replace(".json", "").trim());
                    }
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

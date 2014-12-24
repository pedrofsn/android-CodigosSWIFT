package banks.swift.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import banks.swift.R;
import banks.swift.adapters.AdapterBank;
import banks.swift.model.Bank;

public class ActivityBanks extends ActionBarActivity {

    private List<Bank> list;
    private AdapterBank adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        ListView listView = (ListView) findViewById(R.id.listView);

        String pais = getIntent().getStringExtra("pais");

        Bank[] arrayBank = new Gson().fromJson(getStringJsonFromAssets(pais.concat(".json")), Bank[].class);

        list = new ArrayList<Bank>();

        list.addAll(Arrays.asList(arrayBank));

        adapter = new AdapterBank(this, list);
        listView.setAdapter(adapter);

    }

    public String getStringJsonFromAssets(String arquivo) {
        try {
            InputStream is = getAssets().open(arquivo);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
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

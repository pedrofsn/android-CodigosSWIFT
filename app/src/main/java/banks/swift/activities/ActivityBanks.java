package banks.swift.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import banks.swift.R;
import banks.swift.adapters.AdapterBank;
import banks.swift.asynctasks.AsyncTaskLoadBanks;
import banks.swift.interfaces.ILoadBanks;
import banks.swift.model.Bank;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ActivityBanks extends ActionBarActivity implements ILoadBanks {

    private ListView listView;

    private Bank[] arrayBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        listView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState != null) {
            arrayBank = (Bank[]) savedInstanceState.get("banks");
            listView.setAdapter(new AdapterBank(this, arrayBank));
        } else {
            AsyncTaskLoadBanks asyncTaskLoadBanks = new AsyncTaskLoadBanks(this);
            asyncTaskLoadBanks.execute(getIntent().getStringExtra("country"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("banks", arrayBank);
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

    @Override
    public void onLoadedBanks(Bank[] banks) {
        if (banks != null) {
            listView.setAdapter(new AdapterBank(this, banks));
        } else {
            Crouton.makeText(this, getString(R.string.ops_ocorreu_um_erro), Style.ALERT).show();
        }
    }
}

package banks.swift.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import banks.swift.R;
import banks.swift.fragment.FragmentBanks;
import banks.swift.fragment.FragmentCountries;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class ActivityMain extends ActionBarActivity {

    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentCountries())
                    .commit();
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

    public void altetarFragment(String country) {
        this.country = country;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new FragmentBanks())
                .commit();
    }

    public String getCountry() {
        return country;
    }
}
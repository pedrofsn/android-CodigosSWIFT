package banks.swift.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
    private boolean controlHomeButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentCountries())
                    .commit();
        } else {
            controlHomeButton = savedInstanceState.getBoolean("controlHomeButton");
            showHomeButton(controlHomeButton);
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
        switch (id) {
            case R.id.action_settings:

                break;

            case android.R.id.home:
                changeFragment(null);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("controlHomeButton", controlHomeButton);
    }

    public void changeFragment(String country) {
        this.country = country;

        if (country != null) {
            FragmentBanks fragmentBanks = new FragmentBanks();

            String backStateName = fragmentBanks.getClass().getName();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragmentBanks);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
            controlHomeButton = true;

        } else {
            getSupportFragmentManager().popBackStack();
            controlHomeButton = false;
        }

        showHomeButton(controlHomeButton);
    }

    public void showHomeButton(boolean showHomeButton) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
        getSupportActionBar().setHomeButtonEnabled(showHomeButton);
    }

    public String getCountry() {
        return country;
    }
}
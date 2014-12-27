package banks.swift.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import banks.swift.ActivitySearchable;
import banks.swift.R;
import banks.swift.fragment.FragmentBanks;
import banks.swift.fragment.FragmentCountries;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class ActivityMain extends ActivitySearchable {

    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentCountries = new FragmentCountries();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragmentCountries)
                    .commit();
        } else {
            isFragmentBanksVisible = savedInstanceState.getBoolean("isFragmentBanksVisible");
            showHomeButton(isFragmentBanksVisible);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFragmentBanksVisible", isFragmentBanksVisible);
    }

    @Override
    public void restartSearch() {
        if (isFragmentBanksVisible) {
            fragmentBanks.restartSearch();
        } else {
            fragmentCountries.restartSearch();
        }
    }

    @Override
    public void changeFragment(String country) {
        this.country = country;

        if (country != null) {
            fragmentBanks = new FragmentBanks();

            String backStateName = fragmentBanks.getClass().getName();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragmentBanks);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
            isFragmentBanksVisible = true;

        } else {
            getSupportFragmentManager().popBackStack();
            isFragmentBanksVisible = false;
        }

        showHomeButton(isFragmentBanksVisible);
    }

    public void showHomeButton(boolean visibility) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(visibility);
        getSupportActionBar().setHomeButtonEnabled(visibility);
    }

    public String getCountry() {
        return country;
    }

    @Override
    public void onBackPressed() {
        if (isFragmentBanksVisible) {
            isFragmentBanksVisible = false;
            showHomeButton(isFragmentBanksVisible);
        }
        super.onBackPressed();
    }

}
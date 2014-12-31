package banks.swift.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import banks.swift.R;
import banks.swift.dialog.DialogFragmentContato;
import banks.swift.fragment.FragmentBanks;
import banks.swift.fragment.FragmentCountries;

/**
 * Created by pedro.sousa on 26/12/2014.
 */
public class ActivityMain extends ActionBarActivity implements SearchView.OnQueryTextListener {

    public boolean isFragmentBanksVisible = false;
    public MenuItem searchItem;
    private String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cor_azul_marcante)));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentCountries())
                    .commit();
        } else {
            isFragmentBanksVisible = savedInstanceState.getBoolean("isFragmentBanksVisible");
            showHomeButton(isFragmentBanksVisible);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                searchView.setQuery("", false);
                restartSearch();
                return true;
            }

        });

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFragmentBanksVisible", isFragmentBanksVisible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sendMessage:
                new DialogFragmentContato().show(getSupportFragmentManager(), DialogFragmentContato.TAG);
                break;

            case android.R.id.home:
                changeFragment(null);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Fragment fragmentRunning = getFragmentRunning();
        if (s != null && !s.isEmpty() && fragmentRunning != null) {

            if (fragmentRunning instanceof FragmentBanks) {
                ((FragmentBanks) fragmentRunning).search(s);
            } else {
                ((FragmentCountries) fragmentRunning).search(s);
            }
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private void restartSearch() {
        Fragment fragmentRunning = getFragmentRunning();
        if (fragmentRunning instanceof FragmentBanks) {
            ((FragmentBanks) fragmentRunning).restartSearch();
        } else {
            ((FragmentCountries) fragmentRunning).restartSearch();
        }
    }

    public void changeFragment(String country) {
        Fragment fragmentRunning = getFragmentRunning();
        this.country = country;

        if (country != null) {
            String backStateName = fragmentRunning.getClass().getName();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, new FragmentBanks());
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
            isFragmentBanksVisible = true;
        } else {
            getSupportFragmentManager().popBackStack();
            isFragmentBanksVisible = false;
        }

        if (searchItem != null) {
            MenuItemCompat.collapseActionView(searchItem);
        }

        showHomeButton(isFragmentBanksVisible);
    }

    private void showHomeButton(boolean visibility) {
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

    private Fragment getFragmentRunning() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

}
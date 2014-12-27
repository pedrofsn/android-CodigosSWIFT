package banks.swift;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import banks.swift.fragment.FragmentBanks;
import banks.swift.fragment.FragmentCountries;
import banks.swift.model.Bank;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public abstract class ActivitySearchable extends ActionBarActivity implements SearchView.OnQueryTextListener {

    public FragmentCountries fragmentCountries;
    public FragmentBanks fragmentBanks;
    public boolean isFragmentBanksVisible = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
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

    public abstract void restartSearch();

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

    protected abstract void changeFragment(String country);

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (s != null && !s.isEmpty()) {
            if (isFragmentBanksVisible) {
                fragmentBanks.search(s);
            } else {
                fragmentCountries.search(s);
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public Object search(Object[] array, String query) {

        if (array instanceof String[]) {
            ArrayList<String> result = new ArrayList<String>();

            for (String pais : (String[]) array) {
                if (pais.toLowerCase().contains(query.toLowerCase())) {
                    result.add(pais);
                }
            }

            return result.toArray(new String[result.size()]);

        } else {
            ArrayList<Bank> result = new ArrayList<Bank>();

            for (Bank pais : (Bank[]) array) {
                if (pais.getName().toLowerCase().contains(query.toLowerCase())) {
                    result.add(pais);
                }
            }

            return result.toArray(new Bank[result.size()]);
        }
    }
}

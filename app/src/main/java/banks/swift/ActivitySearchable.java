package banks.swift;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by pedrofsn on 27/12/2014.
 */
public abstract class ActivitySearchable extends ActionBarActivity implements SearchView.OnQueryTextListener {

    public boolean isFragmentBanksVisible = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
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

    protected abstract void changeFragment(String country);

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (isFragmentBanksVisible) {

        } else {

        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private int buscaBinaria(int[] tabela, int valor) {

        boolean achou = false;
        int alto = tabela.length - 1;
        int baixo = 0;
        int meio = alto / 2;

        while (!achou && alto >= baixo) {
            if (valor == tabela[meio]) {
                achou = true;
            } else if (valor < tabela[meio]) {
                alto = meio - 1;
            } else {
                baixo = meio + 1;
            }
            meio = (alto + baixo) / 2;
        }
        return ((achou) ? meio : -1);
    }
}

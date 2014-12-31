package banks.swift.asynctasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import banks.swift.interfaces.Searchable;
import banks.swift.model.Bank;

/**
 * Created by pedro.sousa on 24/12/2014.
 */
public class AsyncTaskSearch extends AsyncTask<String, Void, Object[]> {

    private final Searchable callback;
    private final Object[] array;
    private final String query;

    public AsyncTaskSearch(Searchable callback, Object[] array, String query) {
        this.array = array;
        this.query = query;
        this.callback = callback;
    }

    @Override
    protected Object[] doInBackground(String... params) {
        return search();
    }

    @Override
    protected void onPostExecute(Object[] result) {
        super.onPostExecute(result);
        callback.showResults(result);
    }

    private Object[] search() {
        if (array instanceof String[]) {
            ArrayList<String> result = new ArrayList<>();

            for (String pais : (String[]) array) {
                if (pais.toLowerCase().contains(query.toLowerCase())) {
                    result.add(pais);
                }
            }

            return result.toArray(new String[result.size()]);

        } else {
            ArrayList<Bank> result = new ArrayList<>();

            for (Bank pais : (Bank[]) array) {
                if (pais.getName().toLowerCase().contains(query.toLowerCase())) {
                    result.add(pais);
                }
            }

            return result.toArray(new Bank[result.size()]);
        }
    }

}

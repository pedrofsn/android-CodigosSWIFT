package banks.swift.asynctasks;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import banks.swift.interfaces.Loadable;

/**
 * Created by pedro.sousa on 24/12/2014.
 */
public class AsyncTaskLoadCountries extends AsyncTask<String, Void, String[]> {

    private Context context;
    private Loadable callback;

    public AsyncTaskLoadCountries(Context context, Loadable callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected String[] doInBackground(String... params) {
        return lerArquivos();
    }

    @Override
    protected void onPostExecute(String[] countries) {
        super.onPostExecute(countries);
        callback.onLoaded(countries);
    }

    private String[] lerArquivos() {
        ArrayList<String> paises = new ArrayList<String>();
        AssetManager assetManager = context.getAssets();
        try {
            String[] filelist = assetManager.list("");

            for (String nomeDoPais : filelist) {
                if (nomeDoPais.endsWith(".json")) {
                    paises.add(nomeDoPais.replace(".json", "").trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return paises.toArray(new String[paises.size()]);
    }
}

package banks.swift.asynctasks;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import banks.swift.interfaces.ILoadCountries;

/**
 * Created by pedro.sousa on 24/12/2014.
 */
    public class AsyncTaskLoadCountries extends AsyncTask<String, Void, ArrayList<String>> {

    private Context context;
    private ILoadCountries callback;

    public AsyncTaskLoadCountries(Context context, ILoadCountries callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        return lerArquivos();
    }

    @Override
    protected void onPostExecute(ArrayList<String> countries) {
        super.onPostExecute(countries);
        callback.onLoadedCountries(countries);
    }



    private ArrayList<String> lerArquivos() {
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

        return paises;
    }
}

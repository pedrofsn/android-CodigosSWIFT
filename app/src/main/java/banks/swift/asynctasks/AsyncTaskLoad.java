package banks.swift.asynctasks;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import banks.swift.interfaces.Searchable;
import banks.swift.model.Bank;

/**
 * Created by pedro.sousa on 24/12/2014.
 */
public class AsyncTaskLoad extends AsyncTask<String, Void, Object[]> {

    private Context context;
    private Searchable callback;

    public AsyncTaskLoad(Context context, Searchable callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Object[] doInBackground(String... params) {
        if (params != null && params.length >= 1 && params[0] != null) {
            return new Gson().fromJson(getStringJsonFromAssets(params[0].concat(".json")), Bank[].class);
        } else {
            return lerArquivos();
        }
    }

    @Override
    protected void onPostExecute(Object[] result) {
        super.onPostExecute(result);
        callback.showSearchResults(result);
    }

    private String getStringJsonFromAssets(String arquivo) {
        try {
            InputStream is = context.getAssets().open(arquivo);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
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

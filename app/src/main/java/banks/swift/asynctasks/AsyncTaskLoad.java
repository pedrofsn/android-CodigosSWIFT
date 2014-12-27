package banks.swift.asynctasks;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import banks.swift.interfaces.Loadable;

/**
 * Created by pedro.sousa on 24/12/2014.
 */
public class AsyncTaskLoad<T> extends AsyncTask<String, Void, T> {

    private Context context;
    private Loadable callback;
    private Type inferedClass;

    public AsyncTaskLoad(Context context, Loadable callback) {
        this.context = context;
        this.callback = callback;
        //this.inferedClass = Class<T>;
    }

    public Type getGenericClass() {
        if (inferedClass == null) {
            Type mySuperclass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
            return tType;
        }

        return null;
    }

    @Override
    protected T doInBackground(String... params) {
   /*     if (<T >extends ArrayList<String>){
            return (T) lerArquivos();
            else
            return (T) new Gson().fromJson(getStringJsonFromAssets(params[0].concat(".json")), Bank[].class);

        }*/
        return null;
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        callback.onLoaded(result);
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

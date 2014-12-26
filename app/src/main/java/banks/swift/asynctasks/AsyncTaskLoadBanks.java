package banks.swift.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import banks.swift.interfaces.ILoadBanks;
import banks.swift.model.Bank;

/**
 * Created by pedro.sousa on 24/12/2014.
 */
public class AsyncTaskLoadBanks extends AsyncTask<String, Void, Bank[]> {

    private Context context;
    private ILoadBanks callback;

    public AsyncTaskLoadBanks(Context context) {
        this.context = context;
        this.callback = (ILoadBanks) context;
    }

    @Override
    protected Bank[] doInBackground(String... params) {
        return new Gson().fromJson(getStringJsonFromAssets(params[0].concat(".json")), Bank[].class);
    }

    @Override
    protected void onPostExecute(Bank[] banks) {
        super.onPostExecute(banks);
        callback.onLoadedBanks(banks);
        //Crouton.makeText(this, context.getString(R.string.ops_ocorreu_um_erro), Style.ALERT).show();
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
}

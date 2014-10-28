package banks.swift;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityMain extends ActionBarActivity {

    private List<Bank> list;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Bank>();

        if (list.size() <= 0) {
            list.add(new Bank("Banco do Brasil", "Brasil", new Date().toString(), "SP"));
            list.add(new Bank("Banco do Brasil", "Brasil", new Date().toString(), "SP"));
        }

        adapter = new AdapterBank(list);
        recyclerView.setAdapter(adapter);

        DownloadBank task = new DownloadBank();
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class DownloadBank extends AsyncTask<Void, Void, Bank> {

        @Override
        protected Bank doInBackground(Void... params) {
            // The connection URL
            String url = "https://dl.dropboxusercontent.com/u/9889747/apps/swift-code/banco.json";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return (Bank) restTemplate.getForObject(url, Bank.class);
        }

        @Override
        protected void onPostExecute(Bank result) {
            super.onPostExecute(result);
            list.add(result);
            adapter.notifyDataSetChanged();
        }
    }

}

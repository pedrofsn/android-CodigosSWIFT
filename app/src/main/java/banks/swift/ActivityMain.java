package banks.swift;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Bank> list = new ArrayList<Bank>();
        list.add(new Bank("Banco do Brasil", "Brasil", "13456", "SP"));
        list.add(new Bank("Banco do Brasil", "Brasil", "13456", "SP"));
        list.add(new Bank("Banco do Brasil", "Brasil", "13456", "SP"));
        list.add(new Bank("Banco do Brasil", "Brasil", "13456", "SP"));
        list.add(new Bank("Banco do Brasil", "Brasil", "13456", "SP"));

        RecyclerView.Adapter adapter = new AdapterBank(list);
        recyclerView.setAdapter(adapter);
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

}

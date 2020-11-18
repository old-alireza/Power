package alireza.sn.power;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import alireza.sn.power.adapter.CustomAdapter;
import alireza.sn.power.model.MyModule;
import alireza.sn.power.model.MyModuleRepository;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    CustomAdapter adapter;

    List<MyModule> moduleList = new ArrayList<>();

     String databaseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getDatabaseName();
        findViews();
        new GetModuleInfo().execute();
        setAdapter();
    }

    private void getDatabaseName() {
        Bundle bundle = getIntent().getExtras();
        this.databaseName = bundle.getString("db_name");
    }

    private void setAdapter() {
        adapter = new CustomAdapter(moduleList,this,databaseName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    private class GetModuleInfo extends AsyncTask<Void , Void , Void>{
        List<MyModule> list = new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            list = new MyModuleRepository(getApplicationContext(),databaseName).getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            moduleList.clear();
            moduleList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}
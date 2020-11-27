package alireza.sn.power;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private final BroadcastReceiver goToFormActivity = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String dbName = intent.getStringExtra("db_name");
            startActivity(new Intent(MainActivity.this,FormActivity.class).putExtra("db_name",dbName));
        }
    };

    private final BroadcastReceiver goToListActivity = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String dbName = intent.getStringExtra("db_name");
            startActivity(new Intent(MainActivity.this,ListActivity.class).putExtra("db_name",dbName));
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(goToFormActivity,new IntentFilter("change_to_form"));
        LocalBroadcastManager.getInstance(this).registerReceiver(goToListActivity,new IntentFilter("change_to_list"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(goToFormActivity);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(goToListActivity);
    }
}
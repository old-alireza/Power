package alireza.sn.power;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
    private Button sendBtn;
    private Button receivedBtn;

    private ModuleFragment sendModuleFrag;
    private ModuleFragment receivedModuleFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setOnClicks();
    }

    private void setOnClicks() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendModuleFrag = new ModuleFragment("send");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_fragment_main, sendModuleFrag)
                        .addToBackStack(null).commit();
            }
        });

        receivedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receivedModuleFrag = new ModuleFragment("received");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_fragment_main, receivedModuleFrag)
                        .addToBackStack(null).commit();
            }
        });
    }

    private void findViews() {
        sendBtn = findViewById(R.id.send_tools);
        receivedBtn = findViewById(R.id.received_tools);
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
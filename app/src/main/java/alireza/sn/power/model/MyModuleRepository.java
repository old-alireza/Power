package alireza.sn.power.model;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

import alireza.sn.power.R;

public class MyModuleRepository {

     String DB_NAME;
    private Context context;
    private MyModuleDatabase myModuleDatabase;

    public MyModuleRepository(Context context,String databaseName) {
        this.context = context;
        this.DB_NAME = databaseName;
        myModuleDatabase = Room.databaseBuilder(context,MyModuleDatabase.class,DB_NAME).build();
    }

    public void insert(final MyModule myModule) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                myModuleDatabase.myModuleDao().insertTask(myModule);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast toast = new Toast(context);
                toast.setDuration(Toast.LENGTH_SHORT);
                View view = LayoutInflater.from(context).inflate(R.layout.custom_toast,null);
                TextView message = view.findViewById(R.id.message_toast);
                message.setText(context.getString(R.string.toast_insert));
                toast.setView(view);
                toast.show();
                //Toast.makeText(context, context.getString(R.string.toast_insert), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public List<MyModule> getAll (){
        List<MyModule> myModules = myModuleDatabase.myModuleDao().getAll();
        return myModules;
    }

    public void update (final MyModule myModule){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                myModuleDatabase.myModuleDao().updateTask(myModule);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast toast = new Toast(context);
                toast.setDuration(Toast.LENGTH_SHORT);
                View view = LayoutInflater.from(context).inflate(R.layout.custom_toast,null);
                TextView message = view.findViewById(R.id.message_toast);
                message.setText(context.getString(R.string.toast_updated));
                toast.setView(view);
                toast.show();
               // Toast.makeText(context, context.getString(R.string.toast_updated), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public void delete (final MyModule myModule){
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                myModuleDatabase.myModuleDao().deleteTask(myModule);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast toast = new Toast(context);
                toast.setDuration(Toast.LENGTH_SHORT);
                View view = LayoutInflater.from(context).inflate(R.layout.custom_toast,null);
                TextView message = view.findViewById(R.id.message_toast);
                message.setText(context.getString(R.string.toast_deleted));
                toast.setView(view);
                toast.show();
               // Toast.makeText(context, context.getString(R.string.toast_deleted), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}

package alireza.sn.power;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class InsertViewFragment extends Fragment {
    private Button btnInsert;
    private Button btnViewList;

    private final String databaseName;

    public InsertViewFragment (String databaseName){
        this.databaseName = databaseName;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_and_insert,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setOnClick();
    }

    private void setOnClick() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("change_to_form").putExtra("db_name",databaseName));
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("change_to_list").putExtra("db_name",databaseName));
            }
        });
    }

    private void findViews(View view) {
        btnInsert = view.findViewById(R.id.button_insert);
        btnViewList = view.findViewById(R.id.button_view_list);
    }
}

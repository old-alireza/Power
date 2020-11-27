package alireza.sn.power;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ModuleFrag extends Fragment implements View.OnClickListener {

    private Button nianBtn;
    private Button PSPBtn;
    private Button resaBtn;

    private String moveDB;

    private Bundle bundle;
    public ModuleFrag () {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        getArgs();
        init();
    }

    private void getArgs() {
        Bundle bundle = getArguments();
        moveDB = bundle.getString("db_name");
    }

    private void init() {
        nianBtn.setOnClickListener(this);
        PSPBtn.setOnClickListener(this);
        resaBtn.setOnClickListener(this);
    }

    private void findViews(View view) {
        nianBtn = view.findViewById(R.id.button_nian);
        PSPBtn = view.findViewById(R.id.button_PSP);
        resaBtn = view.findViewById(R.id.button_resa);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_nian){
            bundle = new Bundle();
            bundle.putString("send_name",moveDB+"nian");

        } else if (v.getId() == R.id.button_PSP){
            bundle = new Bundle();
            bundle.putString("send_name",moveDB+"psp");

        } else if (v.getId() == R.id.button_resa){
            bundle = new Bundle();
            bundle.putString("send_name",moveDB+"resa");
        }
        Navigation.findNavController(getView()).navigate(R.id.action_moduleFrag_to_insertFragment,bundle);
    }
}
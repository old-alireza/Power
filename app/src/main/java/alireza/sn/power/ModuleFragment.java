package alireza.sn.power;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ModuleFragment extends Fragment {
    private Button nianBtn;
    private Button PSPBtn;
    private Button resaBtn;

    private InsertViewFragment nianFrag;
    private InsertViewFragment PSPFrag;
    private InsertViewFragment resaFrag;

    private final String moveDB;

    public ModuleFragment (String moveDB) {
        this.moveDB = moveDB;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module,container,false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setOnClicks();
    }

    private void setOnClicks() {
        nianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nianFrag = new InsertViewFragment(moveDB+"nian");
                getFragmentManager().beginTransaction().add(R.id.container_fragment_module, nianFrag).addToBackStack(null).commit();
            }
        });

        PSPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PSPFrag = new InsertViewFragment(moveDB+"psp");
                getFragmentManager().beginTransaction().add(R.id.container_fragment_module, PSPFrag).addToBackStack(null).commit();
            }
        });

        resaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resaFrag = new InsertViewFragment(moveDB+"resa");
                getFragmentManager().beginTransaction().add(R.id.container_fragment_module, resaFrag).addToBackStack(null).commit();
            }
        });
    }

    private void findViews(View view) {
        nianBtn = view.findViewById(R.id.button_nian);
        PSPBtn = view.findViewById(R.id.button_PSP);
        resaBtn = view.findViewById(R.id.button_resa);
    }
}

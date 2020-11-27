package alireza.sn.power;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {
    private Button sendBtn;
    private Button receivedBtn;

    private Bundle bundle;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findViews(view);
        init();

        return view;
    }

    private void init() {
        sendBtn.setOnClickListener(this);
        receivedBtn.setOnClickListener(this);
    }

    private void findViews(View view) {
        sendBtn = view.findViewById(R.id.send_tools);
        receivedBtn = view.findViewById(R.id.received_tools);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_tools){
            bundle = new Bundle();
            bundle.putString("db_name","send");

        } else if (v.getId() == R.id.received_tools){
            bundle = new Bundle();
            bundle.putString("db_name","received");
        }

        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_moduleFrag, bundle);
    }
}
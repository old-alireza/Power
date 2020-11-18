package alireza.sn.power;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import alireza.sn.power.model.MyModule;
import alireza.sn.power.model.MyModuleRepository;

public class FormActivity extends AppCompatActivity {
    private Button submitBtn;

    private EditText coreName;
    private EditText serialNumber;
    private EditText date;
    private EditText mailNumber;
    private EditText mailDate;

    private String databaseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getDatabaseName();
        findViews();
        setOnClicks();
    }

    private void getDatabaseName() {
        Bundle bundle = getIntent().getExtras();
        this.databaseName = bundle.getString("db_name");
    }

    private void setOnClicks() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCoreName = coreName.getText().toString().trim();
                String strSerialNum = serialNumber.getText().toString().trim();
                String strDate = date.getText().toString().trim();
                String strMailDate = mailDate.getText().toString().trim();
                String strMailNumber = mailNumber.getText().toString().trim();

                if (strCoreName.trim().isEmpty() ||
                strSerialNum.trim().isEmpty() ||
                strDate.trim().isEmpty() ||
                strMailNumber.trim().isEmpty()) {
                    Toast toast = new Toast(FormActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    View view = LayoutInflater.from(FormActivity.this).inflate(R.layout.custom_toast,null);
                    TextView message = view.findViewById(R.id.message_toast);
                    message.setText(R.string.toast_enter_value);
                    toast.setView(view);
                    toast.show();
                } else {
                    MyModule myModule = new MyModule(strCoreName,strSerialNum,strDate,strMailNumber,strMailDate);
                    new MyModuleRepository(getApplicationContext(),databaseName).insert(myModule);
                    finish();
                }
            }
        });
    }

    private void findViews() {
        submitBtn = findViewById(R.id.button_submit);
        coreName = findViewById(R.id.edittext_center_name);
        serialNumber = findViewById(R.id.edittext_serial_number);
        date = findViewById(R.id.edittext_date);
        mailNumber = findViewById(R.id.mail_number);
        mailDate = findViewById(R.id.mail_date);
    }
}
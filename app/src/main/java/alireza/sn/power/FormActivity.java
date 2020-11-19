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
    private EditText dateDay;
    private EditText dateMonth;
    private EditText dateYear;
    private EditText mailNumber;
    private EditText mailDateDay;
    private EditText mailDateMonth;
    private EditText mailDateYear;

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
                String strDate = getString(R.string.date_string , dateDay.getText().toString().trim(),
                        dateMonth.getText().toString().trim(),
                        dateYear.getText().toString().trim());

                String strMailDate = getString(R.string.date_string , mailDateDay.getText().toString().trim(),
                        mailDateMonth.getText().toString().trim(),
                        mailDateYear.getText().toString().trim());

                String strMailNumber = mailNumber.getText().toString().trim();
                    MyModule myModule = new MyModule(strCoreName,strSerialNum,strDate,strMailNumber,strMailDate);
                    new MyModuleRepository(getApplicationContext(),databaseName).insert(myModule);
                    finish();

            }
        });
    }

    private void findViews() {
        submitBtn = findViewById(R.id.button_submit);
        coreName = findViewById(R.id.edittext_center_name);
        serialNumber = findViewById(R.id.edittext_serial_number);
        dateDay = findViewById(R.id.edittext_date_day);
        dateMonth = findViewById(R.id.edittext_date_month);
        dateYear = findViewById(R.id.edittext_date_year);
        mailNumber = findViewById(R.id.mail_number);
        mailDateDay = findViewById(R.id.mail_date_day);
        mailDateMonth = findViewById(R.id.mail_date_month);
        mailDateYear = findViewById(R.id.mail_date_year);
    }
}
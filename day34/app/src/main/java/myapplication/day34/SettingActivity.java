package myapplication.day34;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

     private EditText editText1,editText2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
     initview();
    }
    private void initview() {
          editText1= (EditText) findViewById(R.id.SettingET1);
        editText2= (EditText) findViewById(R.id.SettingET2);
        button= (Button) findViewById(R.id.Settingbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SettingActivity.this,"提交成功",Toast.LENGTH_LONG).show();

            }
        });

    }
}

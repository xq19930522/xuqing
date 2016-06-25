package myapplication.day34;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    initview();

    }

    private void initview() {

        button1= (Button) findViewById(R.id.aboutbutton1);
        button2= (Button) findViewById(R.id.aboutbutton2);
        button3= (Button) findViewById(R.id.aboutbt3);
         button4= (Button) findViewById(R.id.aboutbt4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
         Intent intent=new Intent();
        switch (v.getId()){
            case R.id.aboutbutton1:

                break;
            case R.id.aboutbutton2:

                break;
            case R.id.aboutbt3:
            intent.setClass(AboutActivity.this, CollectorActivity.class);
            startActivity(intent);
                break;
            case R.id.aboutbt4:
                intent.setClass(AboutActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
        }


    }
}

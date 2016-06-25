package myapplication.day34;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import myapplication.day34.app.ConstantKey;
import myapplication.day34.util.Pref_Utils;


public class LoadingActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent();
				intent.setClass(LoadingActivity.this,WelcomeActivity.class);

				if (!getFirstOpenFlag()){
					intent.setClass(LoadingActivity.this,HomeActivity.class);
				}
				startActivity(intent);
				finish();
			}
		},3000);
	}
	public boolean getFirstOpenFlag(){
		return Pref_Utils.getBoolean(this, ConstantKey.PRE_KEY_FIRST_OPEN, true);
	}

}

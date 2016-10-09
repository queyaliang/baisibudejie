package demo.copy.baisi.activity;

import demo.copy.baisi.R;
import demo.copy.baisi.R.id;
import demo.copy.baisi.R.layout;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.RelativeLayout;

public class WelcomeActivity extends Activity {
	private RelativeLayout rlWelcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		rlWelcome = (RelativeLayout) findViewById(R.id.rl_welcome);
		new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				
				startActivity(new Intent(getApplicationContext(),MainActivity.class));
				finish();
				return false;
			}
		}).sendEmptyMessageDelayed(0, 2500);
	}

}

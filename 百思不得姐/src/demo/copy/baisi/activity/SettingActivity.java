package demo.copy.baisi.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;


import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.ui.Consts;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SettingActivity extends Activity {
	@ViewInject(R.id.rbtn_xiao)
	private RadioButton rbtnXiao;
	@ViewInject(R.id.rbtn_zhong)
	private RadioButton rbtnZhong;
	@ViewInject(R.id.rbtn_da)
	private RadioButton rbtnDa;

	private RadioGroup radioGroup;
	@ViewInject(R.id.tv_about)
	private TextView tvabout;
	@ViewInject(R.id.tv_help)
	private TextView tvhelp;
	private BaisiApplication app = BaisiApplication.getApplication();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		x.view().inject(this);
		tvabout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent =new Intent(SettingActivity.this,AboutActivity.class);
				startActivity(intent);
			}
		});
		tvhelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent =new Intent(SettingActivity.this,HelpActivity.class);
				startActivity(intent);
			}
		});


		int textSize = app.getCurrentTextSize();

		if (textSize==0) {
			rbtnXiao.setChecked(true);
		}
		if (textSize==1) {
			rbtnZhong.setChecked(true);
		}
		if (textSize==2) {
			rbtnDa.setChecked(true);
		}

		//判断sharepreference中是否存有cart信息  有的话则一起发送数据
		SharedPreferences pref = getSharedPreferences("textsize", Context.MODE_PRIVATE);
		final Editor edit = pref.edit();
		radioGroup=(RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rbtn_xiao){
					edit.putInt("size", 12);
				}
				if(checkedId==R.id.rbtn_zhong){
					edit.putInt("size", 15);
				}
				if(checkedId==R.id.rbtn_da){
					edit.putInt("size", 18);
					
				}
			}
		});
		edit.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

}

package demo.copy.baisi.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;


import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.Share;
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
	@ViewInject(R.id.radiogroup)
	private RadioGroup radioGroup;
	@ViewInject(R.id.tv_about)
	private TextView tvabout;
	@ViewInject(R.id.tv_help)
	private TextView tvhelp;
	@ViewInject(R.id.tv_recommend)
	private TextView tvRecommend;
	
	private BaisiApplication app = BaisiApplication.getApplication();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		x.view().inject(this);
		final SharedPreferences pref = getSharedPreferences("textsize", Context.MODE_PRIVATE);
		
		int size = pref.getInt("size", 15);
		if (size==12) {
			rbtnXiao.setChecked(true);
		}else if (size==15) {
			rbtnZhong.setChecked(true);
		}else {
			rbtnDa.setChecked(true);
		}
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

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Editor edit = pref.edit();
				if(checkedId==R.id.rbtn_xiao){
					edit.putInt("size", 12);
				}
				if(checkedId==R.id.rbtn_zhong){
					edit.putInt("size", 15);
				}
				if(checkedId==R.id.rbtn_da){
					edit.putInt("size", 18);
					
				}
				edit.commit();
			}
		});
		tvRecommend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Share.showShare(getParent());
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

}

package demo.copy.baisi.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import demo.copy.baisi.R;
import demo.copy.baisi.Wj;
import demo.copy.baisi.adapter.MainPagerAdapter;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.fragment.FunnyFragment;
import demo.copy.baisi.fragment.MineFragment;
import demo.copy.baisi.fragment.PictureFragment;
import demo.copy.baisi.fragment.RadioFragment;
import demo.copy.baisi.fragment.SearchFragment;
import demo.copy.baisi.fragment.VoiceFragment;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.NetworkUtil;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
	@ViewInject(R.id.vp_content)
	private ViewPager vpContent;
	@ViewInject(R.id.rg_bottom_buttons)
	private RadioGroup rgBottomButtons;
	@ViewInject(R.id.rbtn_picture)
	private RadioButton rbtnPicture;
	@ViewInject(R.id.rbtn_funny)
	private RadioButton rbtnFunny;
	@ViewInject(R.id.rbtn_voice)
	private RadioButton rbtnVoice;
	@ViewInject(R.id.rbtn_radio)
	private RadioButton rbtnRadio;
	@ViewInject(R.id.rbtn_search)
	private RadioButton rbtnSearch;
	@ViewInject(R.id.rbtn_mine)
	private RadioButton rbtnMine;

	//Fragment����
	private List<Fragment> fragments = new ArrayList<Fragment>();
	//ViewPager������
	private MainPagerAdapter pagerAdapter;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		NetworkUtil.checkNetworkState(this);
		Wj wj=new Wj();
		wj.showWj();

		//����Adapter
		setAdapter();
		//����Listener
		setListener();
	}
	@Override
	protected void onResume() {
		super.onResume();
		if (getSharedPreferences("textsize", Context.MODE_PRIVATE)!=null) {
			SharedPreferences share = getSharedPreferences("textsize", Context.MODE_PRIVATE);
			Consts.textSize = share.getInt("size", 15);
		}
	}
	/**
	 * ��viewpager����������
	 */
	private void setAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new PictureFragment());
		fragments.add(new FunnyFragment());
		fragments.add(new VoiceFragment());
		fragments.add(new RadioFragment());
		fragments.add(new SearchFragment());
		fragments.add(new MineFragment());
		//����Adapter
		pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
		vpContent.setAdapter(pagerAdapter);
		vpContent.setOffscreenPageLimit(3);
	}
	/**
	 * ���ü�����
	 */
	private void setListener() {

		//radioButton������
		rgBottomButtons.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rbtn_picture:
					vpContent.setCurrentItem(0);
					break;
				case R.id.rbtn_funny:
					vpContent.setCurrentItem(1);
					break;
				case R.id.rbtn_voice:
					vpContent.setCurrentItem(2);
					break;
				case R.id.rbtn_radio:
					vpContent.setCurrentItem(3);
					break;
				case R.id.rbtn_search:
					vpContent.setCurrentItem(4);
					break;
				case R.id.rbtn_mine:
					vpContent.setCurrentItem(5);
					break;
				}

			}
		});

		//viewpager������
		vpContent.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int i) {
				switch (i){
				case 0:
					rbtnPicture.setChecked(true);
					break;
				case 1:
					rbtnFunny.setChecked(true);
					break;
				case 2:
					rbtnVoice.setChecked(true);
					break;
				case 3:
					rbtnRadio.setChecked(true);
					break;
				case 4:
					rbtnSearch.setChecked(true);
					break;
				case 5:
					rbtnMine.setChecked(true);
					break;
				}
			}
			@Override
			public void onPageScrolled(int a, float b, int c) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	@Override
	protected void onDestroy() {
		BaisiApplication.getApplication().removeActivity(this);
		super.onDestroy();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

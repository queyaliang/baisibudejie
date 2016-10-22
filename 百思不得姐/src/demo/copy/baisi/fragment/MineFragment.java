package demo.copy.baisi.fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;
import demo.copy.baisi.activity.LoginActivity;
import demo.copy.baisi.activity.MapActivity;
import demo.copy.baisi.activity.SettingActivity;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Avatar;
import demo.copy.baisi.entity.TheThirdUserInfo;
import demo.copy.baisi.entity.User;
import demo.copy.baisi.presenter.IMinePresenter;
import demo.copy.baisi.presenter.impl.MinePresenter;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.ReadTheThirdUser;
import demo.copy.baisi.view.IMineView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineFragment extends Fragment implements IMineView{
	//
	@ViewInject(R.id.ivPhoto)
	private ImageView ivPhoto;
	@ViewInject(R.id.tvNickname)
	private TextView tvNickname;
	@ViewInject(R.id.itemExit)
	private RelativeLayout itemExit;
	@ViewInject(R.id.itemSettings)
	private RelativeLayout itemSettings;
	@ViewInject(R.id.tv_exit)
	private TextView tvExit;
	@ViewInject(R.id.tv_recommend)
	private TextView tvRecommend;
	@ViewInject(R.id.tv_setting)
	private TextView tvSetting;
	@ViewInject(R.id.tv_location)
	private TextView tvLocation;
	

	private IMinePresenter presenter;
	private static final int REQUEST_CODE_LOGIN_USER = 1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mine_fragment, null);
		presenter = new MinePresenter(this);
		x.view().inject(this, view);

		setListener();
		//�Զ���¼
		String token = BaisiApplication.getApplication().getToken();
		if(token != null) {
			presenter.loginWithoutPwd(token);
		}
		return view;
	}

	private void setListener() {
		
		
		MineListener listener = new MineListener();
		ivPhoto.setOnClickListener(listener);
		tvExit.setOnClickListener(listener);
		tvRecommend.setOnClickListener(listener);
		tvSetting.setOnClickListener(listener);
		
		tvLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),MapActivity.class));
			}
		});
	}

	/**
	 * �������
	 */
	class MineListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()){
			case R.id.ivPhoto:
				Intent i = new Intent(getActivity(), LoginActivity.class);
				startActivityForResult(i, REQUEST_CODE_LOGIN_USER);
				break;
			case R.id.tv_exit:
				BaisiApplication.getApplication().exit();
				break;
			case R.id.tv_recommend:
				// 调有米的sdk
				try {
					// 显示积分墙，
					// 显示在一个新的activity中，
					// activity在jar
					// youmi sdk 有一个activity,.xml,联网，解析，显示
					AdManager.getInstance(getActivity()).init(
							"893ce06024f53e92", "33510aece245f67b", false);
					// 如果使用积分广告，请务必调用积分广告的初始化接口:
					OffersManager.getInstance(getActivity()).onAppLaunch();
					OffersManager.getInstance(getActivity()).showOffersWall();

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case R.id.tv_setting:
				Intent intent = new Intent(getActivity(), SettingActivity.class);
				startActivity(intent);
				break;
			}
		}
	}

	@Override
	public void updateUserInfo() {
		User user = BaisiApplication.getApplication().getCurrentUser();
		String name = user.getNickname();
		
		//		Log.i("demo", "user.getBitmap()-->"+user.getBitmap());
		Avatar myAvatar = readUser(name);
		tvNickname.setText(name);
		
		if (myAvatar!=null) {
			ivPhoto.setImageBitmap(myAvatar.getBitmap());
		}
		

	}

	Avatar avatar = new Avatar();
	private String newname;
	
//	private String path;
	private Bitmap bitmap;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==Activity.RESULT_OK) {
			newname = data.getStringExtra("_name");
			bitmap = data.getParcelableExtra("_avatar");
			String path=null;
			if (bitmap!=null) {
				path = Environment.getExternalStorageDirectory()+"/bitmap"+newname;
				File file = new File(path);
				try {
					file.createNewFile();
				} catch (Exception e) {
				}
				FileOutputStream output = null;
				try {
					output = new FileOutputStream(file);
				} catch (Exception e) {
				}
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
				try {
					output.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				Avatar avatarr =readUser(newname);
				if (newname.equals(avatarr.getName())) {
					bitmap=avatarr.getBitmap();
					if (bitmap==null) {
						bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.touxiang);
					}
				}else {
					bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.touxiang);
				}
			}
			avatar.setName(newname);
			avatar.setBitmap(bitmap);
			Log.i("demo", "mine-->name-->"+newname);
			if (path!=null) {
				
				saveUser(newname,path);
			}
		}
	}

	public void saveUser(String name,String path) {
		SharedPreferences share = getActivity().getSharedPreferences("bitmapIndex", getActivity().MODE_PRIVATE);
		Editor editor = share.edit();
		editor.putString(""+name, path);
		editor.commit();
	}
	public Avatar readUser(String names) {
		SharedPreferences share = getActivity().getSharedPreferences("bitmapIndex", getActivity().MODE_PRIVATE);
		String path = share.getString(names,"");
		Avatar myavatar = null;
		if (path!=null) {
			myavatar = new Avatar();
			Bitmap bitmap = BitmapFactory.decodeFile(path);
			myavatar.setBitmap(bitmap);
			myavatar.setName(names);
		}
		return myavatar;
	}
		@Override
		public void onResume() {
			super.onResume();
			if (newname!=null) {
				tvNickname.setText(newname);
			}
			if (bitmap!=null) {
				ivPhoto.setImageBitmap(bitmap);
			}
			TheThirdUserInfo theThirdUser = ReadTheThirdUser.read();
			if (theThirdUser.isValid()) {
				tvNickname.setText(theThirdUser.getName());
				
			}
			
			
		}


}

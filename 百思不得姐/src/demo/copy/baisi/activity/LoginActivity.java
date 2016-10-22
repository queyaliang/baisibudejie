package demo.copy.baisi.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import com.mob.tools.utils.UIHandler;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;

import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.TheThirdUserInfo;
import demo.copy.baisi.entity.User;
import demo.copy.baisi.presenter.ILoginPresenter;
import demo.copy.baisi.presenter.impl.LoginPresenter;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.ReadTheThirdUser;
import demo.copy.baisi.util.SaveTheThirdUser;
import demo.copy.baisi.view.ILoginView;

public class LoginActivity extends Activity implements ILoginView,Callback ,PlatformActionListener{
	@ViewInject(R.id.etLoginname)
	private EditText etLoginname;
	@ViewInject(R.id.etPwd)
	private EditText etPwd;
	@ViewInject(R.id.tvNewAccount)
	private TextView tvToRegist;
	@ViewInject(R.id.tvModifyPwd)
	private TextView tvModifyPwd;
	@ViewInject(R.id.tv_xinlang_state)
	private TextView tvSina;
	@ViewInject(R.id.tv_qq_state)
	private TextView tvQQ;
	@ViewInject(R.id.btnLogin)
	private Button btnLogin;
	@ViewInject(R.id.ivPhoto)
	private CircleImageView ivPhoto;
	@ViewInject(R.id.iv_xinlang)
	private ImageView ivXinlang;
	@ViewInject(R.id.iv_qq)
	private ImageView ivQQ;
	
	private ILoginPresenter presenter;
	
	private Bitmap bitmap = null;
	
	private static final int MSG_ACTION_CCALLBACK = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShareSDK.initSDK(this);
		setContentView(R.layout.activity_login);
		
		presenter = new LoginPresenter(this);
		//注入控件   初始�?		
		x.view().inject(this);
		BaisiApplication.getApplication().addActivity(this);
		//添加监听
		setListener();
	}

	//添加监听
	private void setListener() {
		LoginViewListener listener = new LoginViewListener();
		tvToRegist.setOnClickListener(listener);
		btnLogin.setOnClickListener(listener);
		ivPhoto.setOnClickListener(listener);
		ivXinlang.setOnClickListener(listener);
		ivQQ.setOnClickListener(listener);
		
	}

	public void back(View view){
		finish();
	}

	@Override
	public void loginSuccess() {
		Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
		User user = BaisiApplication.getApplication().getCurrentUser();
		user.setBitmap(bitmap);
		String name = BaisiApplication.getApplication().getCurrentUser().getNickname();
		Intent intent = new Intent();
		intent.putExtra("_name", name);
		intent.putExtra("_avatar", bitmap);
		setResult(RESULT_OK,intent);
		finish();
	}

	@Override
	public void loginFailed(String errorMessage) {
		Toast.makeText(this,"登录失败,"+errorMessage,Toast.LENGTH_SHORT).show();
	}

	class LoginViewListener implements View.OnClickListener{
		public void onClick(View view) {
			switch (view.getId()){
				case R.id.tvNewAccount://新用户注�?					
					Intent i = new Intent(LoginActivity.this, RegistActivity.class);
					startActivity(i);
					break;
				case R.id.btnLogin:
					String name=etLoginname.getText().toString();
					String pwd=etPwd.getText().toString();
					if(name.equals("") || pwd.equals("")){
						Toast.makeText(LoginActivity.this,"请输入账号或密码",Toast.LENGTH_SHORT).show();
						return;
					}
					presenter.login(name, pwd);
					break;
				case R.id.ivPhoto:
				Intent intent = new Intent(LoginActivity.this, ImageActivity.class);
				startActivityForResult(intent, 11);
				break;
				case R.id.iv_xinlang:
					Platform weixinfd = ShareSDK.getPlatform(SinaWeibo.NAME);
					if (weixinfd.isValid ()){
						weixinfd.removeAccount();
						TheThirdUserInfo user = ReadTheThirdUser.read();
						user.setValid(false);
						SaveTheThirdUser.save(user);
						Log.i("demo", "LoginActivity-->第三方已成功退出");
						return;
					}
					weixinfd.setPlatformActionListener(LoginActivity.this);
					weixinfd.showUser(null);
					break;
				case R.id.iv_qq:
					Log.i("demo", "点击qq第三方登录");
					Platform qq = ShareSDK.getPlatform(QQ.NAME);
					if (qq.isValid ()){
						qq.removeAccount();
					}
					qq.setPlatformActionListener(LoginActivity.this);
					qq.showUser(null);
					break;
			}
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case 777:
			bitmap = data.getParcelableExtra("xiangji");
			break;
		case 888:
			bitmap = data.getParcelableExtra("xiangce");
			break;
		case 999:
			bitmap = data.getParcelableExtra("xitong");
		}
		ivPhoto.setImageBitmap(bitmap);
	}
	@Override
	protected void onDestroy() {
		BaisiApplication.getApplication().removeActivity(this);
		super.onDestroy();
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.arg1) {
		case 1: {
			// 成功
			Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();
		}
			break;
		case 2: {
			// 失败
			Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();
			String expName = msg.obj.getClass().getSimpleName();
			if ("WechatClientNotExistException".equals(expName)
					|| "WechatTimelineNotSupportedException".equals(expName)
					|| "WechatFavoriteNotSupportedException".equals(expName)) {
				Toast.makeText(LoginActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
			}
		}
			break;
		case 3: {
			// 取消
			Toast.makeText(LoginActivity.this, "取消····", Toast.LENGTH_SHORT)
					.show();
		}
			break;
		}

		return false;
	}

	@Override
	public void onCancel(Platform platform, int action) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
		
	}
	@Override
	public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = platform;
		
		UIHandler.sendMessage(msg, this);
		System.out.println(res);
		//获取资料
		String name = platform.getDb().getUserName();//获取用户名字	
		String icon = platform.getDb().getUserIcon(); //获取用户头像
		Log.i("demo", "LoginActivity-->第三方已成功登录");
		Log.i("demo","头像链接"+icon);
		TheThirdUserInfo user = new TheThirdUserInfo();
		user.setValid(true);
		user.setName(name);
		user.setUrl(icon);
		SaveTheThirdUser.save(user);
		String userid =platform.getDb().getUserId();
		String token = platform.getDb().getToken();
		String refresh_token111 = platform.getDb().get("refresh_token");
		System.out.println("token==="+token);
	
		System.out.println("userid===="+userid);
		String unionid = (String) res.get("unionid");
		System.out.println("unionid==="+unionid);
		System.out.println("refresh_token111==="+refresh_token111);
	}
	

	@Override
	public void onError(Platform platform, int action, Throwable t) {

		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = t;
		UIHandler.sendMessage(msg, this);
		// 分享失败的统计
		ShareSDK.logDemoEvent(4, platform);
		
	}
}

package demo.copy.baisi.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.User;
import demo.copy.baisi.presenter.ILoginPresenter;
import demo.copy.baisi.presenter.impl.LoginPresenter;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.view.ILoginView;

public class LoginActivity extends Activity implements ILoginView {
	@ViewInject(R.id.etLoginname)
	private EditText etLoginname;
	@ViewInject(R.id.etPwd)
	private EditText etPwd;
	@ViewInject(R.id.tvNewAccount)
	private TextView tvToRegist;
	@ViewInject(R.id.tvModifyPwd)
	private TextView tvModifyPwd;
	@ViewInject(R.id.btnLogin)
	private Button btnLogin;
	@ViewInject(R.id.ivPhoto)
	private CircleImageView ivPhoto;
	
	private ILoginPresenter presenter;
	
	private Bitmap bitmap = null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		presenter = new LoginPresenter(this);
		//注入控件   初始�?		
		x.view().inject(this);
		//添加监听
		setListener();
	}

	//添加监听
	private void setListener() {
		LoginViewListener listener = new LoginViewListener();
		tvToRegist.setOnClickListener(listener);
		btnLogin.setOnClickListener(listener);
		ivPhoto.setOnClickListener(listener);
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
}

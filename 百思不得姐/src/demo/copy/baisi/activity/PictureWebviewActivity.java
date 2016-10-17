package demo.copy.baisi.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;

public class PictureWebviewActivity extends Activity {
	@ViewInject(R.id.webView)
	private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_webview);
		x.view().inject(this);
		BaisiApplication.getApplication().addActivity(this);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("_url");
		webview.loadUrl(url);
	}
	@Override
	protected void onDestroy() {
		BaisiApplication.getApplication().removeActivity(this);
		super.onDestroy();
	}
}

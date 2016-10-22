package demo.copy.baisi.activity;

import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	/**
	 * 声明webview控件
	 */
	private WebView webview ;

	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webview =(WebView) findViewById(R.id.webView);
		Intent intent = getIntent();

		//声明控件
		init();
		//创建一个链接
		String url = intent.getStringExtra("_url");
		Log.i("demo", "url-->"+url);
		
//		StringBuffer sb = new StringBuffer();  
//        sb.append("<html>")  
//       .append("<head>")  
//       .append("<meta http-equiv='Content-Type' content='text/html'; charset='UTF-8'>")  
//       .append("<style type='text/css'>")  
//       .append(".response-img {max-width: 100%;}")  
//       .append("#box {width: 100%;height: 100%;display: table;text-align: center;background: #fff;}")  
//       .append("#box span {display: table-cell;vertical-align: middle;}")  
//       .append("</style>")  
//       .append("<title>")  
//       .append("</title>")  
//       .append("</head>")  
//       .append("<body style='text-align: center;' onClick='window.myInterfaceName.showToast(\"finish Activity\")'>")  
//       .append("<div id='box'>")  
//       .append("<span>")  
//       .append("<img src='" + url + "' class='response-img' style='width: 100%'/>")  
//       .append("</span>")  
//       .append("</div>")  
//       .append("</body>")  
//       .append("</html>");  
////       webview.addJavascriptInterface(sb, url);  
//       webview.loadDataWithBaseURL(null, sb.toString(),  "text/html", "UTF-8", null); 
		
		//把数据加载到webview控件中
		webview.setBackgroundColor(Color.BLACK);
		webview.setScrollBarStyle(0);
////		webview.setInitialScale(5);//为25%，最小缩放等级 
		WebSettings webSettings =webview.getSettings();
////		webSettings.setJavaScriptEnabled(true);  
////		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);  
		webSettings.setUseWideViewPort(true);//关键点  
//
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
//
////		webSettings.setDisplayZoomControls(false);  
		webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本  
////		webSettings.setAllowFileAccess(true); // 允许访问文件  
		webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮  
		webSettings.setSupportZoom(true); // 支持缩放  
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
//		DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		int mDensity = metrics.densityDpi;
//		if (mDensity == 120) {
//			webSettings.setDefaultZoom(ZoomDensity.CLOSE);
//		}else if (mDensity == 160) {
//			webSettings.setDefaultZoom(ZoomDensity.MEDIUM);
//		}else if (mDensity == 240) {
//			webSettings.setDefaultZoom(ZoomDensity.FAR);
//		}
		webview.loadUrl(url);
		//用系统或其他浏览器浏览该链接
		webview.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//
				view.loadUrl(url);
				return true;
			}

		});
//		WebSettings settings =webview.getSettings();
//
//		settings.setJavaScriptEnabled(true);
//		settings.setUseWideViewPort(true);
//		settings.setLoadWithOverviewMode(true);
//		settings.setCacheMode(WebSettings.LOAD_DEFAULT);

		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// 
				if (newProgress==100) {
					//加载完毕关闭
					closedialog();
				} else {
					//打开
					opendialog(newProgress);
				}

				super.onProgressChanged(view, newProgress);
			}
		});
	}

	/**
	 * 打开progressdialog
	 * @param newProgress
	 */
	protected void opendialog(int newProgress) {
		Activity activity = WebViewActivity.this;  
		while (activity.getParent() != null) {  
			activity = activity.getParent();  
		} 
		// 
		if(dialog==null){
			dialog = new ProgressDialog(activity);
			dialog.setTitle("加载");
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setProgress(newProgress);
			dialog.show();

		}
		else{
			dialog.setProgress(newProgress);
		}
	}
	/**
	 * 关闭progressdialog
	 */
	protected void closedialog() {
		// 
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
			dialog=null;

		}

	}
	/**
	 * 更改物理键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 
		if(keyCode==KeyEvent.KEYCODE_BACK){

			if(webview.canGoBack()){
				webview.goBack();
				return true;
			}
			else{
				finish();
			}


		}



		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		//初始化控件
		webview = (WebView) findViewById(R.id.wv);
	}
	@Override
	protected void onDestroy() {
		BaisiApplication.getApplication().removeActivity(this);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

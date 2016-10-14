package demo.copy.baisi.activity;

import demo.copy.baisi.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

		/**
		 * ����webview�ؼ�
		 */
		private WebView webview ;
		
		private ProgressDialog dialog;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_webview);
			
			Intent intent = getIntent();
			
			//�����ؼ�
			init();
			//����һ������
			String url = intent.getStringExtra("_url");
			Log.i("demo", "url-->"+url);
			//�����ݼ��ص�webview�ؼ���
			webview.loadUrl(url);
			webview.setBackgroundColor(Color.BLACK);
			//��ϵͳ��������������������
			webview.setWebViewClient(new WebViewClient(){
				
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					//
					view.loadUrl(url);
					return true;
				}
				
			});
			WebSettings settings =webview.getSettings();
			
			settings.setJavaScriptEnabled(true);
			settings.setUseWideViewPort(true);
			settings.setLoadWithOverviewMode(true);
			settings.setCacheMode(WebSettings.LOAD_DEFAULT);
			
			webview.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					// 
					if (newProgress==100) {
						//������Ϲر�
						closedialog();
					} else {
						//��
						opendialog(newProgress);
					}
					
					super.onProgressChanged(view, newProgress);
				}
			});
			
			
			
		}
		
		/**
		 * ��progressdialog
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
				dialog.setTitle("����");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setProgress(newProgress);
				dialog.show();
				
			}
			else{
				dialog.setProgress(newProgress);
			}
		}
		/**
		 * �ر�progressdialog
		 */
		protected void closedialog() {
			// 
			if(dialog!=null&&dialog.isShowing()){
				dialog.dismiss();
				dialog=null;
				
			}
			
		}
		/**
		 * ���������
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
	 * ��ʼ���ؼ�
	 */
	private void init() {
		//��ʼ���ؼ�
		webview = (WebView) findViewById(R.id.wv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

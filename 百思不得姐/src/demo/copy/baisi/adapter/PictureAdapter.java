package demo.copy.baisi.adapter;

import java.io.File;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import demo.copy.baisi.R;
import demo.copy.baisi.activity.PictureWebviewActivity;
import demo.copy.baisi.activity.WebViewActivity;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.defineview.MyWebView;
import demo.copy.baisi.entity.Pictures;
import demo.copy.baisi.presenter.impl.GifOrImagePresenter;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.BitmapCache;
import demo.copy.baisi.view.IGifOrImageView;

public class PictureAdapter extends BaseAdapter implements IGifOrImageView{

	// 鏁版嵁
	private List<Pictures> list;
	private Context context;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	private ListView listview;	
//	File file = new File(BaisiApplication.getApplication().getCacheDir(), "webcache");
	private GifOrImagePresenter presenter;

	public PictureAdapter(List<Pictures> list, Context context,
			ListView listView) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		presenter = new GifOrImagePresenter(this);
		RequestQueue mqueue = BaisiApplication.getRequestQueue();
		imageLoader = new ImageLoader(mqueue, new BitmapCache());
		this.listview = listView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Pictures getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item_picture, null);
			holder = new ViewHolder();
			holder.webView = (MyWebView) convertView.findViewById(R.id.ivMsg);
			holder.ivPic = (CircleImageView) convertView
					.findViewById(R.id.ivPic);
			holder.tvMsgInfo = (TextView) convertView
					.findViewById(R.id.tvMsgInfo);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
			holder.tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
			holder.tvTucao = (TextView) convertView.findViewById(R.id.tvTuCao);
			holder.tvUserName = (TextView) convertView
					.findViewById(R.id.tvUserName);
			holder.tvZan = (TextView) convertView.findViewById(R.id.tvZan);
			holder.layout = (RelativeLayout) convertView.findViewById(R.id.rlContent);
			convertView.setTag(holder);
		}else{
		holder = (ViewHolder) convertView.getTag();
		}
		// 锟斤拷holder锟叫的控硷拷锟斤拷锟叫革拷值
		Pictures item = getItem(position);
		Log.i("YYYYY", "" + item.getProfile_image());

		holder.tvUserName.setText(item.getName()); 		// 鐢ㄦ埛鍚�
		holder.tvTime.setText(item.getCreate_time()); 	// 褰撳墠鏃堕棿
		holder.tvZan.setText(item.getLove() + ""); 		// 璧�
		holder.tvTucao.setText(item.getHate() + ""); 	// 鎭�
		holder.tvTotal.setText(item.getText()); 		// text
		holder.tvTotal.setTextSize(Consts.textSize);
		// 使锟斤拷ImageLoader锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟酵计�
		// 头锟斤拷
		ImageListener listener = ImageLoader.getImageListener(holder.ivPic,
				R.drawable.welcom_icon, R.drawable.welcom_icon);
		imageLoader.get(item.getProfile_image(), listener,
				holder.ivPic.getWidth(), holder.ivPic.getHeight());

		//鑾峰彇瑙ｆ瀽鍒扮殑閾炬帴
		String path0 = item.getImage0();
		String path1 = item.getImage1();
		String path2 = item.getImage2();
		String path3 = item.getImage3();

		holder.webView.setHorizontalScrollBarEnabled(false);//水平不显示  
		holder.webView.setVerticalScrollBarEnabled(false); //垂直不显示 
		holder.webView.setBackgroundColor(Color.BLACK);
		
		WebSettings webSettings = holder.webView.getSettings();

		
		String url = null;
		if (path0 != null) {
			Log.i("de", "url-->1-->" + path0);
			url = path0;
		} else if (path1 != null) {
			Log.i("de", "url-->1-->" + path1);
			url = path1;
		} else if (path2 != null) {
			Log.i("de", "url-->2-->" + path2);
			url = path2;
		} else if (path3 != null) {
			Log.i("de", "url-->3-->" + path3);
			url = path3;
		}
		holder.webView.loadUrl(url);		
		holder.layout.setOnClickListener(new InteOnClickeListeners(position, url));
		return convertView;
	}

	class InteOnClickeListeners implements OnClickListener{

		private int position;
		private String url;

		public InteOnClickeListeners(int position, String url) {
			this.position = position;
			this.url = url;
		}

		@Override
		public void onClick(View v) {
			WebView webview = (WebView) listview.findViewWithTag("webview"
					+ position);
			Intent intent = new Intent(context, WebViewActivity.class);
			intent.putExtra("_url", url);
			context.startActivity(intent);
		}		
	}

	class ViewHolder {
		CircleImageView ivPic;
		TextView tvUserName;
		TextView tvTime;
		TextView tvTotal;
		MyWebView webView;
		TextView tvMsgInfo;
		TextView tvZan;
		TextView tvTucao;
		TextView tvFenxiang;
		RelativeLayout layout;
	}


	@Override
	public void getGifOrImageUrl(String url) {
		Log.i("demo", "adapter-->path"+url);
		
	}

}

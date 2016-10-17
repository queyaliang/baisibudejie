package demo.copy.baisi.adapter;

import java.io.File;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.defineview.MyWebView;
import demo.copy.baisi.defineview.XListView;
import demo.copy.baisi.entity.AllFather;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Pictures;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.presenter.impl.RadioImagePresenter;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.util.BitmapCache;
import demo.copy.baisi.view.IRadioImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter implements IRadioImageView{
	
	
	private List<AllFather> lists;
	private Context context;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	private RadioImagePresenter presenter;
	private XListView lvContent;

	public SearchAdapter(List<AllFather> lists,Context context, XListView lvContent){
		this.lvContent = lvContent;
		this.lists = lists;
		this.context = context;
		inflater = LayoutInflater.from(context);
		RequestQueue mqueue = BaisiApplication.getRequestQueue();
		imageLoader = new ImageLoader(mqueue, new BitmapCache());
		presenter = new RadioImagePresenter(this);
	}
	private static final int TYPE_COUNT = 4;
	private static final int TYPE_PICTURE = 0;
	private static final int TYPE_FUNNY = 1;
	private static final int TYPE_VOICE = 2;
	private static final int TYPE_RADIO =3;
	

	@Override
	public int getItemViewType(int position) {
		int type = lists.get(position).getType();
		if(10==type){
			return TYPE_PICTURE;
		}
		else if (29==type) {
			return TYPE_FUNNY;
		}
		else if (39==type) {
			return TYPE_VOICE;
		}
		else{
			return TYPE_RADIO;
		}
	}
	
	@Override
	public int getViewTypeCount() {
		return TYPE_COUNT;
	}
	
	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		PictureViewholder pHolder = null;
		FunnyViewHolder fHolder = null;
		VoiceViewHolder vHolder = null;
		RadioViewHolder rHolder = null;
		if (convertView==null) {
			switch (type) {
			case TYPE_PICTURE:
				convertView = inflater.inflate(R.layout.list_item_picture, null);
				pHolder = new PictureViewholder();
				pHolder.webView = (MyWebView) convertView.findViewById(R.id.ivMsg);
				pHolder.ivPic = (CircleImageView) convertView
						.findViewById(R.id.ivPic);
				pHolder.tvMsgInfo = (TextView) convertView
						.findViewById(R.id.tvMsgInfo);
				pHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
				pHolder.tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);
				pHolder.tvTucao = (TextView) convertView.findViewById(R.id.tvTuCao);
				pHolder.tvUserName = (TextView) convertView
						.findViewById(R.id.tvUserName);
				pHolder.tvZan = (TextView) convertView.findViewById(R.id.tvZan);
				pHolder.layout = (RelativeLayout) convertView.findViewById(R.id.rlContent);
				convertView.setTag(pHolder);
				break;
			case TYPE_FUNNY:
				fHolder=new FunnyViewHolder();
				convertView=inflater.inflate(R.layout.item_funny, null);
				fHolder.image=(CircleImageView) convertView.findViewById(R.id.ivCMPic);
				fHolder.tvname=(TextView) convertView.findViewById(R.id.name);
				fHolder.tvtime=(TextView) convertView.findViewById(R.id.time);
				fHolder.love=(TextView) convertView.findViewById(R.id.love);
				fHolder.hate=(TextView) convertView.findViewById(R.id.hate);
				fHolder.text=(TextView) convertView.findViewById(R.id.funny_text);
				fHolder.url=(TextView) convertView.findViewById(R.id.url);
				convertView.setTag(fHolder);
				break;
			case TYPE_VOICE:
				vHolder = new VoiceViewHolder();
				convertView = inflater.inflate(R.layout.voice_item_detail, null);
				vHolder.ivProfil_image = (CircleImageView) convertView.findViewById(R.id.iv_profil_image);
				vHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
				vHolder.tvPublishTime = (TextView) convertView.findViewById(R.id.tv_publishtime);
				vHolder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
				convertView.setTag(vHolder);
				break;
			case TYPE_RADIO:
				rHolder = new RadioViewHolder();
				convertView = inflater.inflate(R.layout.radio_list_item, null);
				rHolder.tvName = (TextView) convertView.findViewById(R.id.tv_user_name);
				rHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_user_bytime);
				rHolder.tvText = (TextView) convertView.findViewById(R.id.tv_user_send_info);
				rHolder.image = (CircleImageView) convertView.findViewById(R.id.iv_user_avatar);
				rHolder.ivRadio = (ImageView) convertView.findViewById(R.id.iv_radio);
				rHolder.ibtnPlay = (ImageButton) convertView.findViewById(R.id.ibtn_play);
				rHolder.tvZan = (TextView) convertView.findViewById(R.id.tv_zan);
				rHolder.tvRuo = (TextView) convertView.findViewById(R.id.tv_ruo);
				convertView.setTag(rHolder);
				break;

			}
		}else{
			switch (type) {
			case TYPE_PICTURE:
				pHolder = (PictureViewholder) convertView.getTag();
				break;
			case TYPE_FUNNY:
				fHolder = (FunnyViewHolder) convertView.getTag();
				break;
			case TYPE_VOICE:
				vHolder = (VoiceViewHolder) convertView.getTag();
				break;
			case TYPE_RADIO:
				rHolder = (RadioViewHolder) convertView.getTag();
				break;
			}
		}
		switch (type) {
		case TYPE_PICTURE:
			Pictures picture = (Pictures) lists.get(position);
			pHolder.tvUserName.setText(picture.getName()); 		
			pHolder.tvTime.setText(picture.getCreate_time()); 
			pHolder.tvZan.setText(picture.getLove() + ""); 		
			pHolder.tvTucao.setText(picture.getHate() + ""); 	
			pHolder.tvTotal.setText(picture.getText()); 		
			ImageListener listener = ImageLoader.getImageListener(pHolder.ivPic,
					R.drawable.welcom_icon, R.drawable.welcom_icon);
			imageLoader.get(picture.getProfile_image(), listener,
					pHolder.ivPic.getWidth(), pHolder.ivPic.getHeight());

			//鑾峰彇瑙ｆ瀽鍒扮殑閾炬帴
			String path0 = picture.getImage0();
			String path1 = picture.getImage1();
			String path2 = picture.getImage2();
			String path3 = picture.getImage3();

			pHolder.webView.setHorizontalScrollBarEnabled(false);//水平不显示  
			pHolder.webView.setVerticalScrollBarEnabled(false); //垂直不显示 
			pHolder.webView.setBackgroundColor(Color.BLACK);
			
			WebSettings webSettings = pHolder.webView.getSettings();

			
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
			pHolder.webView.loadUrl(url);
			break;
		case TYPE_FUNNY:
			Funny funny = (Funny) lists.get(position);
			fHolder.tvname.setText(funny.getName());
			fHolder.tvtime.setText(funny.getCreate_time());
			fHolder.love.setText(funny.getLove()+"");
			fHolder.hate.setText(funny.getHate()+"");
			String text=funny.getText();
			String content = text.replace("\n", "");
			fHolder.text.setText(content);
			String fUrl =funny.getWeixin_url();
			fHolder.url.setText(fUrl);
			fHolder.url.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
			fHolder.url.setTextColor(Color.BLUE);
			Log.i("YYYYY", ""+funny.getProfile_image());
			ImageListener flistener = ImageLoader.getImageListener(fHolder.image, R.drawable.welcom_icon, R.drawable.welcom_icon);
			imageLoader.get(funny.getProfile_image(), flistener,fHolder.image.getWidth(),fHolder.image.getHeight());
			break;
		case TYPE_VOICE:
			Voice voice = (Voice) lists.get(position);
			vHolder.tvName.setText(voice.getName());
			vHolder.tvPublishTime.setText(voice.getCreate_time());
			String vText = voice.getText().replace("\n", "").replace("\r", "").trim();
			
			vHolder.tvText.setText(vText);
			ImageListener vListener = ImageLoader.getImageListener(vHolder.ivProfil_image, R.drawable.ic_launcher, R.drawable.ic_launcher);
			imageLoader.get(voice.getProfile_image(),vListener);
			break;
		case TYPE_RADIO:
			Radio radio = (Radio) lists.get(position);
			rHolder.tvName.setText(radio.getName());
			rHolder.tvTime.setText(radio.getCreate_time());
			String rText = radio.getText().replace("\n", "");
			rHolder.tvText.setText(rText);
			rHolder.tvZan.setText(radio.getLove()+"");
			rHolder.tvRuo.setText(radio.getHate()+"");
			ImageListener rListener = ImageLoader.getImageListener(rHolder.image, R.drawable.a010, R.drawable.a010);
			imageLoader.get(radio.getProfile_image(), rListener,rHolder.image.getWidth(),rHolder.image.getHeight());
			rHolder.ivRadio.setImageResource(R.color.radio_color);
			rHolder.ivRadio.setTag("ivRadio"+position);
			String rUrl = radio.getVideo_uri();
			String radioname = radio.getCreate_time()+position;
			Log.i("demo", "radioname-->"+radioname);
			String name =context.getExternalCacheDir().getAbsolutePath() + "/" + radioname + ".jpg";
			File file  = new File(name);
			if (file.exists()) {
				Log.i("demo", "该图片存在直接从文件中读取");
				Bitmap bitmap = BitmapFactory.decodeFile(name);
				rHolder.ivRadio.setImageBitmap(bitmap);
			}else {
				Log.v("demo", "radioAdapter-->position出:"+position);
				presenter.getRadioImage(rUrl,name,position);
			}
			break;
		}
		
		
		return convertView;
	}
	
	//4种ViewHolder
	class PictureViewholder{
		CircleImageView ivPic;
		TextView tvUserName;
		TextView tvTime;
		TextView tvTotal;
		MyWebView webView;
		TextView tvMsgInfo;
		TextView tvZan;
		TextView tvTucao;
		RelativeLayout layout;
	}
	
	class FunnyViewHolder{
		CircleImageView image;
		TextView tvname;
		TextView tvtime;
		TextView love;
		TextView hate;
		TextView text;
		TextView url;
	}
	
	class VoiceViewHolder{
		CircleImageView ivProfil_image;
		TextView tvName;
		TextView tvPublishTime;
		TextView tvText;
	}
	
	class RadioViewHolder{
		CircleImageView image;
		TextView 		tvName;
		TextView 		tvTime;
		TextView 		tvText;
		ImageView		ivRadio;
		ImageButton 	ibtnPlay;
		TextView 		tvZan;
		TextView		tvRuo;
		
	}
	@Override
	public void getRadioImage(Bitmap bitmap,int position) {
		Log.v("demo", "radioAdapter-->position收:"+position);
		ImageView ivRadio = (ImageView) lvContent.findViewWithTag("ivRadio"+position);
		if (ivRadio==null) {
//			ivRadio.setImageResource(color.background_dark);
			return;
		}
		ivRadio.setImageBitmap(bitmap);
		
		
	}
	

}

package demo.copy.baisi.adapter;

import java.io.File;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import demo.copy.baisi.R;
import demo.copy.baisi.activity.RadioActivity;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.presenter.impl.RadioImagePresenter;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.util.BitmapCache;
import demo.copy.baisi.util.ShareThings;
import demo.copy.baisi.view.IRadioImageView;

public class RadioAdapter extends BaseAdapter<Radio> implements IRadioImageView{

	private ImageLoader imageLoader;
	private RadioImagePresenter presenter;
	private ListView view;
	private RequestQueue mqueue;
	
	

	public RadioAdapter(Activity context, List<Radio> data,ListView view) {
		super(context, data);
		mqueue = BaisiApplication.getRequestQueue();
		imageLoader = new ImageLoader(mqueue, new BitmapCache());

		presenter = new RadioImagePresenter(this);
		this.view = view;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Radio radio = getData().get(position);
		
		ViewHolder holder;
		if (convertView ==null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.radio_list_item, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_user_name);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_user_bytime);
			holder.tvText = (TextView) convertView.findViewById(R.id.tv_user_send_info);
			holder.image = (CircleImageView) convertView.findViewById(R.id.iv_user_avatar);
			holder.ivRadio = (ImageView) convertView.findViewById(R.id.iv_radio);
			holder.ibtnPlay = (ImageButton) convertView.findViewById(R.id.ibtn_play);
			holder.tvZan = (TextView) convertView.findViewById(R.id.tv_zan);
			holder.tvRuo = (TextView) convertView.findViewById(R.id.tv_ruo);
			holder.ivShare = (ImageView) convertView.findViewById(R.id.iv_share);
			convertView.setTag(holder);
		}
		else{
		holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(radio.getName());
		holder.tvTime.setText(radio.getCreate_time());
		String text = radio.getText().replace("\n", "");
		holder.tvText.setText(text);
		holder.tvZan.setText(radio.getLove()+"");
		holder.tvRuo.setText(radio.getHate()+"");

		
		ImageListener listener = ImageLoader.getImageListener(holder.image, R.drawable.a010, R.drawable.a010);
		imageLoader.get(radio.getProfile_image(), listener,holder.image.getWidth(),holder.image.getHeight());
		
		holder.ivRadio.setImageResource(R.color.radio_color);
		holder.ivRadio.setTag("ivRadio"+position);
		
		String url = radio.getVideo_uri();
		holder.ibtnPlay.setOnClickListener(new PlayRadioListener(url));
		
		String radioname = radio.getCreate_time()+position;
		Log.i("demo", "radioname-->"+radioname);
		String name =getContext().getExternalCacheDir().getAbsolutePath() + "/" + radioname + ".jpg";
		
		File file  = new File(name);
		if (file.exists()) {
			Log.i("demo", "��ͼƬ����ֱ�Ӵ��ļ��ж�ȡ");
			Bitmap bitmap = BitmapFactory.decodeFile(name);
			holder.ivRadio.setImageBitmap(bitmap);
		}else {
			Log.v("demo", "radioAdapter-->position��:"+position);
			presenter.getRadioImage(url,name,position);
		}
		holder.ivShare.setOnClickListener(new PlayRadioListener(url));
		
		return convertView;
	}
	
	class ViewHolder{
		CircleImageView image;
		TextView 		tvName;
		TextView 		tvTime;
		TextView 		tvText;
		ImageView		ivRadio;
		ImageButton 	ibtnPlay;
		TextView 		tvZan;
		TextView		tvRuo;
		ImageView 		ivShare;
	}
	
	class PlayRadioListener implements OnClickListener{
		private String url;
		
		public PlayRadioListener(String url) {
			this.url = url;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ibtn_play:
				Intent intent = new Intent(getContext(), RadioActivity.class);
				intent.putExtra("_url", url);
				getContext().startActivity(intent);
				break;

			case R.id.iv_share:
				ShareThings.showShare(getContext(), url);
				break;
			}
		}
	}
	@Override
	public void getRadioImage(Bitmap bitmap,int position) {
		Log.v("demo", "radioAdapter-->position��:"+position);
		ImageView ivRadio = (ImageView) view.findViewWithTag("ivRadio"+position);
		if (ivRadio==null) {
//			ivRadio.setImageResource(color.background_dark);
			return;
		}
		ivRadio.setImageBitmap(bitmap);
		
		
	}

}

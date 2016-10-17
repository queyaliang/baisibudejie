package demo.copy.baisi.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.util.BitmapCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VoiceAdapter extends BaseAdapter{
	private List<Voice> voices;
	private Context context;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	

	public VoiceAdapter(List<Voice> voices, Context context) {
		this.voices = voices;
		this.context = context;
		inflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader(BaisiApplication.getRequestQueue(), new BitmapCache());
	}
	
	public int getCount() {
		return voices.size();
	}

	@Override
	public Voice getItem(int position) {
		return voices.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.voice_item_detail, null);
			holder = new ViewHolder();
			holder.ivProfil_image = (CircleImageView) convertView.findViewById(R.id.iv_profil_image);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvPublishTime = (TextView) convertView.findViewById(R.id.tv_publishtime);
			holder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
			
			convertView.setTag(holder);
		}
		else{
		holder = (ViewHolder) convertView.getTag();
		}
		Voice voice = getItem(position);
		holder.tvName.setText(voice.getName());
		holder.tvPublishTime.setText(voice.getCreate_time());
		String text = voice.getText().replace("\n", "").replace("\r", "").trim();
		
		holder.tvText.setText(text);
		ImageListener listener = ImageLoader.getImageListener(holder.ivProfil_image, R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(voice.getProfile_image(),listener);
		return convertView;
	}
	class ViewHolder{
		CircleImageView ivProfil_image;
		TextView tvName;
		TextView tvPublishTime;
		TextView tvText;
	}

}

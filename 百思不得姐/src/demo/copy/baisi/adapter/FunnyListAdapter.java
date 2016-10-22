package demo.copy.baisi.adapter;

import java.util.List;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import demo.copy.baisi.R;
import demo.copy.baisi.activity.WebViewActivity;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.ui.CircleImageView;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.BitmapCache;
import demo.copy.baisi.util.ShareThings;

public class FunnyListAdapter extends BaseAdapter{
	private Activity context;
	private List<Funny> funnys;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;

	public FunnyListAdapter(Activity context, List<Funny> funnys){
		this.context=context;
		this.funnys=funnys;
		this.inflater=LayoutInflater.from(context);
		RequestQueue mqueue = BaisiApplication.getRequestQueue();
		imageLoader = new ImageLoader(mqueue, new BitmapCache());
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return funnys.size();
	}

	@Override
	public Funny getItem(int i) {
		// TODO Auto-generated method stub
		return funnys.get(i);
	}

	@Override
	public long getItemId(int i) {
		// TODO Auto-generated method stub
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder=null;
		if(view==null){
			view=inflater.inflate(R.layout.item_funny, null);
			holder=new ViewHolder();
			holder.image=(CircleImageView) view.findViewById(R.id.ivCMPic);
			holder.tvname=(TextView) view.findViewById(R.id.name);
			holder.tvtime=(TextView) view.findViewById(R.id.time);
			holder.love=(TextView) view.findViewById(R.id.love);
			holder.hate=(TextView) view.findViewById(R.id.hate);
			holder.text=(TextView) view.findViewById(R.id.funny_text);
			holder.url=(TextView) view.findViewById(R.id.url);
			holder.ivShare=(ImageView) view.findViewById(R.id.ibshare);
			view.setTag(holder);
		}
		else{
		holder=(ViewHolder) view.getTag();
		}
		Funny funny=getItem(i);
		holder.tvname.setText(funny.getName());
		holder.tvtime.setText(funny.getCreate_time());
		holder.love.setText(funny.getLove()+"");
		holder.hate.setText(funny.getHate()+"");
		String text=funny.getText();
		String content = text.replace("\n", "");
		holder.text.setText(content);
		holder.text.setTextSize(Consts.textSize);
		String url =funny.getWeixin_url();
		holder.url.setText(url);
		holder.url.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
		holder.url.setTextColor(Color.BLUE);
		holder.url.setTextSize(Consts.textSize);
		Log.i("YYYYY", ""+funny.getProfile_image());
		
		ImageListener listener = ImageLoader.getImageListener(holder.image, R.drawable.welcom_icon, R.drawable.welcom_icon);
		imageLoader.get(funny.getProfile_image(), listener,holder.image.getWidth(),holder.image.getHeight());
		
		holder.url.setTag("url"+i);
		holder.url.setOnClickListener(new ClickItemListener(i,url));
		holder.ivShare.setOnClickListener(new ClickItemListener(i,url));
		
		
		return view;
	}
	class ViewHolder{
		CircleImageView image;
		TextView tvname;
		TextView tvtime;
		TextView love;
		TextView hate;
		TextView text;
		TextView url;
		ImageView ivShare;
	}
	class ClickItemListener implements View.OnClickListener{
		private int position;
		private String url;
		public ClickItemListener(int position ,String url) {
			this.position = position;
			this.url = url;
		}

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.url:
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("_url", url);
				context.startActivity(intent);
				break;

			case R.id.ibshare:
				ShareThings.showShare(context, url);
				
				break;
			}
		}
	}
	
	
}

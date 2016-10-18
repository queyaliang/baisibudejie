package demo.copy.baisi.adapter;

import java.util.List;

import android.R.color;
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

public class FunnyListAdapter extends BaseAdapter{
	private Context context;
	private List<Funny> funnys;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;

	public FunnyListAdapter(Context context, List<Funny> funnys){
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
			view.setTag(holder);
		}
		holder=(ViewHolder) view.getTag();
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
//		x.image().bind(holder.cvuser,funny.getProfile_image());
		holder.image.measure(34, 34);
//		int width = holder.image.getMeasuredWidth();
//		int height = holder.image.getMeasuredHeight();
//		RotateAnimation anim = new RotateAnimation(0, 360, 26, 26);
//		anim.setDuration(10000);
//		//匀速旋转
//		anim.setInterpolator(new LinearInterpolator());
//		//无限重复
//		anim.setRepeatCount(Animation.INFINITE);
//		holder.image.startAnimation(anim);
		
		ImageListener listener = ImageLoader.getImageListener(holder.image, R.drawable.welcom_icon, R.drawable.welcom_icon);
		imageLoader.get(funny.getProfile_image(), listener,holder.image.getWidth(),holder.image.getHeight());
		
		holder.url.setTag("url"+i);
		holder.url.setOnClickListener(new ClickItemListener(i,url));
		
		
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
			Intent intent = new Intent(context, WebViewActivity.class);
			intent.putExtra("_url", url);
			context.startActivity(intent);
		}
	}
	
	
}

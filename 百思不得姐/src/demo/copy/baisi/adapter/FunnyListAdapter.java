package demo.copy.baisi.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.ui.CircleImageView;
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
		holder.url.setText(" "+funny.getWeixin_url());
		Log.i("YYYYY", ""+funny.getProfile_image());
//		x.image().bind(holder.cvuser,funny.getProfile_image());
		ImageListener listener = ImageLoader.getImageListener(holder.image, R.drawable.welcom_icon, R.drawable.welcom_icon);
		imageLoader.get(funny.getProfile_image(), listener,holder.image.getWidth(),holder.image.getHeight());
		
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
}

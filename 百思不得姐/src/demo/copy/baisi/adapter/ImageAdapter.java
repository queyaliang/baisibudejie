package demo.copy.baisi.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import demo.copy.baisi.R;


public class ImageAdapter extends BaseAdapter<Integer> {

	public ImageAdapter(Context context, List<Integer> data) {
		super(context, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Integer image=getData().get(position);
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=getLayoutInflater().inflate(R.layout.image_gridview_panel, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.iv_gridview);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
//		Options opts=new Options();
//		int h=opts.outHeight/80;
//		int w=opts.outWidth/80;
//		opts.inSampleSize=w>h?h:w;
//		BitmapFactory.decodeResource(getContext().getResources(), image, opts);
		holder.imageView.setImageResource(image);
		return convertView;
	}
	class ViewHolder{
		ImageView imageView;
	}

}

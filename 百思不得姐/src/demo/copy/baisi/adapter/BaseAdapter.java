package demo.copy.baisi.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter{
	
	private Context context;
	
	private List<T> data;
	
	private LayoutInflater inflater ;
	
	/**
	 * 构造方法，创建对象
	 * @param context
	 * @param data
	 */
	public BaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		inflater = LayoutInflater.from(context);
	}
	/**
	 * 返回Context
	 * @return
	 */
	protected final Context getContext(){
		return context;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	protected final List<T> getData(){
		
		return data;
	}
	
	/**
	 * 返回LayoutInflater
	 * @return
	 */
	protected final LayoutInflater getLayoutInflater(){
		return inflater;
	}
	
	/**
	 * 判断Context是否为null
	 * @param context
	 */
	private void setContext(Context context){
		if (context==null) {
			throw new IllegalArgumentException("context为null,请传入非空的context");
		}
		this.context = context;
	}
	/**
	 * 判断list集合是否为null
	 * @param data
	 */
	private void setData(List<T> data){
		if (data==null) {
			data = new ArrayList<T>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


}

package demo.copy.baisi.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter{
	
	private Activity context;
	
	private List<T> data;
	
	private LayoutInflater inflater ;
	
	/**
	 * ���췽������������
	 * @param context
	 * @param data
	 */
	public BaseAdapter(Activity context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		inflater = LayoutInflater.from(context);
	}
	/**
	 * ����Context
	 * @return
	 */
	protected final Activity getContext(){
		return context;
	}
	
	/**
	 * ���������Ϣ
	 * @return
	 */
	protected final List<T> getData(){
		
		return data;
	}
	
	/**
	 * ����LayoutInflater
	 * @return
	 */
	protected final LayoutInflater getLayoutInflater(){
		return inflater;
	}
	
	/**
	 * �ж�Context�Ƿ�Ϊnull
	 * @param context
	 */
	private void setContext(Activity context){
		if (context==null) {
			throw new IllegalArgumentException("contextΪnull,�봫��ǿյ�context");
		}
		this.context = context;
	}
	/**
	 * �ж�list�����Ƿ�Ϊnull
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

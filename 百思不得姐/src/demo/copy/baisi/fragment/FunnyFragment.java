package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;
import demo.copy.baisi.adapter.FunnyListAdapter;
import demo.copy.baisi.defineview.XListView;
import demo.copy.baisi.defineview.XListView.IXListViewListener;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.presenter.IFunnyPresenter;
import demo.copy.baisi.presenter.impl.FunnyPresenter;
import demo.copy.baisi.view.IFunnyView;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FunnyFragment extends Fragment implements IFunnyView,IXListViewListener{
	@ViewInject(R.id.tv_app_header)
	private TextView tvHeader;
	@ViewInject(R.id.lv_funny_fragment)
	private XListView listView;	
	private IFunnyPresenter funnyPresenter;
	//private List<Funny> funnies;
	private FunnyListAdapter funnyAdapter;
	private int page=1;
	private Handler mHandler;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
		view = inflater.inflate(R.layout.funny_fragment, null);
		x.view().inject(this, view);
		funnyPresenter=new FunnyPresenter(this,page);
		funnyPresenter.loadFunny(page);
		listView.setPullLoadEnable(true);// 璁剧疆璁╁畠涓婃媺锛孎ALSE涓轰笉璁╀笂鎷夛紝渚夸笉鍔犺浇鏇村鏁版嵁
		setHeaderName();
		setListeners();
		}else{
			((ViewGroup) view.getParent()).removeView(view);
		}
		return view;
	}
	private void setListeners() {
		listView.setXListViewListener(this);
		mHandler = new Handler();

	}
	/**
	 * 锟斤拷锟斤拷抬头锟斤拷锟�
	 */
	private void setHeaderName() {
		tvHeader.setText("段子");

	}
	@Override
	public void updateFunnyList(List<Funny> funnys) {
		Log.i("aaaa", ""+funnys.toString());
		//		this.funnies=funnys;
		funnyAdapter=new FunnyListAdapter(getActivity(),funnys);
		listView.setAdapter(funnyAdapter);
		onLoad();
	}
	/** 鍋滄鍒锋柊锛� */
	private void onLoad() {
		Log.i("demo", "onload()-->page="+page);
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}
	@Override
	public void onRefresh() {
		if (page==1) {
			listView.setStopFrash(false);
			Toast.makeText(getActivity(), "已经为第一页", Toast.LENGTH_SHORT).show();
			return;
		}
		page--;
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {

				getData();
			}
		}, 2000);
	}
	@Override
	public void onLoadMore() {
		Log.i("demo", "loadMore-->page="+page);
		if (page==20) {
			listView.setStopLoading(false);
			Toast.makeText(getActivity(), "已经全部加载完成", Toast.LENGTH_SHORT).show();
			return;
		}
		page++;
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {

				getData();
			}
		}, 2000);
	}
	private void getData(){
		funnyPresenter.loadFunny(page);
	}
	@Override
	public void onResume() {
		super.onResume();
		if (funnyAdapter!=null) {
			funnyAdapter.notifyDataSetChanged();
		}
	}

}

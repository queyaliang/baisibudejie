package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;
import demo.copy.baisi.adapter.SearchAdapter;
import demo.copy.baisi.defineview.XListView;
import demo.copy.baisi.defineview.XListView.IXListViewListener;
import demo.copy.baisi.entity.AllFather;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.presenter.impl.SearchPresenter;
import demo.copy.baisi.view.ISearchView;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment implements ISearchView,IXListViewListener{
	@ViewInject(R.id.tv_search_button)
	private TextView tvSearch;
	@ViewInject(R.id.et_search)
	private EditText etSearch;
	@ViewInject(R.id.lv_content)
	private XListView lvContent;
	@ViewInject(R.id.ibtn_clear_content)
	private ImageButton ibtnClear;


	private SearchPresenter presenter;

	private int page = 1;
	protected String content;
	private Handler mHandler;
	private SearchAdapter adapter;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
			view = inflater.inflate(R.layout.search_fragment, null);
			x.view().inject(this, view);
			content = etSearch.getText().toString().trim();
			mHandler = new Handler();
			lvContent.setPullLoadEnable(true);
			presenter = new SearchPresenter(this);

			setListener();
		}else{
			((ViewGroup) view.getParent()).removeView(view);
		}
		return view;
	}
	@Override
	public void onStart() {
		Log.d("demo", "searchfragment-->onStart()1");
		super.onStart();
//		if (!"".equals(content)) {
//			Log.d("demo", "searchfragment-->onStart()2");
//			presenter.getSearchInfo(content, page);
//		}
	}

	private void setListener() {

		lvContent.setXListViewListener(this);

		tvSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				presenter.getSearchInfo(content, page);
			}
		});
		ibtnClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etSearch.setText("");
			}
		});
	}

	@Override
	public void getSearchContent(List<AllFather> objects) {
		Log.d("demo", "searchfragment-->getSearchContent()");
		Log.d("demo", "searchfragment-->objects.size():"+objects.size());
		adapter = new SearchAdapter(objects, getActivity(),lvContent);
		lvContent.setAdapter(adapter);
		onLoad();
	}

	private void onLoad() {
		Log.i("demo", "onload()-->page="+page);
		lvContent.stopRefresh();
		lvContent.stopLoadMore();
		lvContent.setRefreshTime("刚刚");
	}

	private void getData(){
		presenter.getSearchInfo(content, page);
	}

	@Override
	public void onRefresh() {
		Log.i("demo", "onRefresh-->page="+page);
		if (page==1) {
			lvContent.setStopFrash(false);
			Toast.makeText(getActivity(), "已经为第一页", Toast.LENGTH_SHORT).show();
			return;
		}
		page--;
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {

				getData();
			}
		}, 20000);

	}

	@Override
	public void onLoadMore() {
		Log.i("demo", "loadMore-->page="+page);
		if (page==20) {
			lvContent.setStopLoading(false);
			Toast.makeText(getActivity(), "已经为最后一页", Toast.LENGTH_SHORT).show();
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
	@Override
	public void onResume() {
		super.onResume();
		if (adapter!=null) {
			adapter.notifyDataSetChanged();
		}
	}

}

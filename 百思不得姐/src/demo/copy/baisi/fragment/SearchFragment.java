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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment implements ISearchView,IXListViewListener{
	@ViewInject(R.id.tv_search_button)
	private TextView tvSearch;
	@ViewInject(R.id.tv_no_content)
	private TextView tvNoContent;
	@ViewInject(R.id.et_search)
	private AutoCompleteTextView etSearch;
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
	}

	private void setListener() {

		lvContent.setXListViewListener(this);

		tvSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveHistory("history", etSearch);
				presenter.getSearchInfo(content, page);
			}
		});
		ibtnClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				etSearch.setText("");
				lvContent.setAdapter(null);
				if (etSearch.getText().toString()==null||"".equals(etSearch.getText().toString())) {
					tvNoContent.setVisibility(View.VISIBLE);
					lvContent.setVisibility(View.GONE);
				}
			}
		});
//		etSearch.setFocusable(false);
		etSearch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				initAutoComplete("history", etSearch);
				return true;
			}
		});
	}
	private void saveHistory(String field,  
			AutoCompleteTextView autoCompleteTextView) {  
		String text = autoCompleteTextView.getText().toString(); 
		if (text==null||"".equals(text)) {
			Toast.makeText(getActivity(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
			return;
		}
		tvNoContent.setVisibility(View.GONE);
		lvContent.setVisibility(View.VISIBLE);
		SharedPreferences sp = getActivity().getSharedPreferences("network_url", 0);  
		String longhistory = sp.getString(field, "nothing");  
		if (!longhistory.contains(text + ",")) {  
			StringBuilder sb = new StringBuilder(longhistory);  
			sb.insert(0, text + ",");  
			sp.edit().putString("history", sb.toString()).commit();  
		}  
	}  
	
	private void initAutoComplete(String field,  
			AutoCompleteTextView autoCompleteTextView) {  
		Log.i("demo", "initAutoComplete()");
		SharedPreferences sp = getActivity().getSharedPreferences("network_url", 0);  
		String longhistory = sp.getString("history", "nothing");  
		String[] histories = longhistory.split(",");  
		ArrayAdapter adapter = new ArrayAdapter(getActivity(),  
				android.R.layout.simple_dropdown_item_1line, histories);  
		// 只保留最近的50条的记录  
		if (histories.length > 50) {  
			String[] newHistories = new String[50];  
			System.arraycopy(histories, 0, newHistories, 0, 50);  
			adapter = new ArrayAdapter(getActivity(),  
					android.R.layout.simple_dropdown_item_1line, newHistories);  
		}  
		autoCompleteTextView.setAdapter(adapter);  
//		etSearch.setOnFocusChangeListener(new OnFocusChangeListener() {  
//			@Override  
//			public void onFocusChange(View v, boolean hasFocus) { 
//				Log.i("demo", "setOnFocusChangeListener()");
//				AutoCompleteTextView view = (AutoCompleteTextView) v;
//				tvNoContent.setVisibility(View.GONE);
//					initAutoComplete("history", etSearch); 
//					view.showDropDown();  
//				 
//			}  
//		});  
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

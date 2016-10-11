package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;

import demo.copy.baisi.activity.VoiceDetailActivity;
import demo.copy.baisi.adapter.VoiceAdapter;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.presenter.IVoicePresenter;
import demo.copy.baisi.presenter.impl.Voicepresenter;
import demo.copy.baisi.view.IVoiceView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VoiceFragment extends Fragment implements IVoiceView{
	@ViewInject(R.id.tv_app_header)
	private TextView tvHeader;
	@ViewInject(R.id.lv_load_voice)
	private ListView lvLoadVoice;
	private List<Voice> voices ;
	private IVoicePresenter VoicePresenter;
	private VoiceAdapter adapter;
	public VoiceFragment() {
		VoicePresenter = new Voicepresenter(this);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.voice_fragment , null);
		x.view().inject(this, view);

		//设置抬头名称
		setHeaderName();
		VoicePresenter.LoadVoiceList(1);
		setListeners();
		return view;
	}

	/**
	 * 设置抬头名称
	 */
	private void setHeaderName() {
		tvHeader.setText("声音");
		
	}
	private void setListeners() {
		//滚动listView时执行，更新下一页voice
		lvLoadVoice.setOnScrollListener(new OnScrollListener() {
			boolean isBottom = false;
			boolean requesting = false;
			int page = 2;
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:
					//判断是否到底了
					if(isBottom && !requesting){//加载下一页
						requesting = true;
						VoicePresenter.LoadVoiceList(page++);
						if(voices==null || voices.isEmpty()){//服务端返回的是空集合
							Toast.makeText(getActivity(), "木有数据了", Toast.LENGTH_SHORT).show();
							requesting = false;
							return;
						}
						//把新得到的voice数据全部添加到就voices列表中
						adapter.notifyDataSetChanged();
						//列表更新完毕 把requesting改成false
						requesting = false;
					}
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					break;
				case OnScrollListener.SCROLL_STATE_FLING:
					break;
				}
			}
			//当滚动时执行  该方法的执行频率非常高
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				if(firstVisibleItem + visibleItemCount == totalItemCount){
					isBottom = true;
				}else{
					isBottom = false;
				}
			
			}
		});
		//点击item跳转activity监听
		lvLoadVoice.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				Voice voice = voices.get(position);
				Intent intent = new Intent(getActivity(),VoiceDetailActivity.class);
				intent.putExtra("voice", voice);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * 异步任务结束后，得到voice列表后，更新Adapter
	 */
	public void updateList(List<Voice> voices) {
		this.voices = voices;
		 adapter = new VoiceAdapter(this.voices,getActivity());
		lvLoadVoice.setAdapter(adapter);
	}
	
}

package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;

import demo.copy.baisi.activity.VoiceDetailActivity;
import demo.copy.baisi.adapter.VoiceAdapter;
import demo.copy.baisi.defineview.XListView;
import demo.copy.baisi.defineview.XListView.IXListViewListener;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.presenter.IVoicePresenter;
import demo.copy.baisi.presenter.impl.Voicepresenter;
import demo.copy.baisi.view.IVoiceView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class VoiceFragment extends Fragment implements IVoiceView,IXListViewListener{
	@ViewInject(R.id.tv_app_header)
	private TextView tvHeader;
	@ViewInject(R.id.lv_voice_fragment)
	private XListView listView;	
	private IVoicePresenter voicePresenter;
	private List<Voice> voices;
	private VoiceAdapter voiceAdapter;
	private int page=1;
	private Handler mHandler;
		public VoiceFragment(){
			voicePresenter=new Voicepresenter(this);
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.voice_fragment, null);
			x.view().inject(this, view);
			voicePresenter.LoadVoiceList(page);
			listView.setPullLoadEnable(true);// 璁剧疆璁╁畠涓婃媺锛孎ALSE涓轰笉璁╀笂鎷夛紝渚夸笉鍔犺浇鏇村鏁版嵁
			
			setHeaderName();
			setListeners();
			return view;
		}
		private void setListeners() {
			listView.setXListViewListener(this);
			mHandler = new Handler();
			
			//点击item跳转activity监听
			listView.setOnItemClickListener(new OnItemClickListener() {
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
		 * 锟斤拷锟斤拷抬头锟斤拷锟�
		 */
		private void setHeaderName() {
			tvHeader.setText("段子");
			
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
			if (page==200) {
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
			voicePresenter.LoadVoiceList(page);
		}
		@Override
		public void updateList(final List<Voice> voices) {

			Log.i("aaaa", ""+voices.toString());
			this.voices=voices;
			voiceAdapter=new VoiceAdapter(voices, getActivity());
			listView.setAdapter(voiceAdapter);
			onLoad();
		}
		
}

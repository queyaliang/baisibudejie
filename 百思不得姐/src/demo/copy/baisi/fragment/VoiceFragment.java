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

		//����̧ͷ����
		setHeaderName();
		VoicePresenter.LoadVoiceList(1);
		setListeners();
		return view;
	}

	/**
	 * ����̧ͷ����
	 */
	private void setHeaderName() {
		tvHeader.setText("����");
		
	}
	private void setListeners() {
		//����listViewʱִ�У�������һҳvoice
		lvLoadVoice.setOnScrollListener(new OnScrollListener() {
			boolean isBottom = false;
			boolean requesting = false;
			int page = 2;
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:
					//�ж��Ƿ񵽵���
					if(isBottom && !requesting){//������һҳ
						requesting = true;
						VoicePresenter.LoadVoiceList(page++);
						if(voices==null || voices.isEmpty()){//����˷��ص��ǿռ���
							Toast.makeText(getActivity(), "ľ��������", Toast.LENGTH_SHORT).show();
							requesting = false;
							return;
						}
						//���µõ���voice����ȫ����ӵ���voices�б���
						adapter.notifyDataSetChanged();
						//�б������� ��requesting�ĳ�false
						requesting = false;
					}
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					break;
				case OnScrollListener.SCROLL_STATE_FLING:
					break;
				}
			}
			//������ʱִ��  �÷�����ִ��Ƶ�ʷǳ���
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				if(firstVisibleItem + visibleItemCount == totalItemCount){
					isBottom = true;
				}else{
					isBottom = false;
				}
			
			}
		});
		//���item��תactivity����
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
	 * �첽��������󣬵õ�voice�б�󣬸���Adapter
	 */
	public void updateList(List<Voice> voices) {
		this.voices = voices;
		 adapter = new VoiceAdapter(this.voices,getActivity());
		lvLoadVoice.setAdapter(adapter);
	}
	
}

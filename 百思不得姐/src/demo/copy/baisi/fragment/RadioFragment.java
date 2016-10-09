package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;
import demo.copy.baisi.adapter.RadioAdapter;
import demo.copy.baisi.defineview.XListView;
import demo.copy.baisi.defineview.XListView.IXListViewListener;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.presenter.impl.RadioPresenter;
import demo.copy.baisi.view.IRadioView;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class RadioFragment extends Fragment implements IRadioView,IXListViewListener{
	@ViewInject(R.id.lv_content)
	private XListView lvContent;
	
	private RadioPresenter presenter;
	private int page =1;

	private Handler mHandler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.radio_fragment, null);
		x.view().inject(this, view);
		lvContent.setPullLoadEnable(true);// ��������������FALSEΪ�����������㲻���ظ�������
		presenter = new RadioPresenter(this);
		presenter.getRadio(page);
		setListener();
		return view;
	}

	private void setListener() {
		lvContent.setXListViewListener(this);
		mHandler = new Handler();
	}
	
	private void getData(){
		presenter.getRadio(page);
	}

	@Override
	public void getRadio(List<Radio> radios) {
		Log.i("demo", "getRadio()-->page="+page);
		RadioAdapter adapter = new RadioAdapter(getActivity(), radios,lvContent);
		lvContent.setAdapter(adapter);
		onLoad();
		
	}
	/** ֹͣˢ�£� */
	private void onLoad() {
		Log.i("demo", "onload()-->page="+page);
		lvContent.stopRefresh();
		lvContent.stopLoadMore();
		lvContent.setRefreshTime("�ո�");
	}
	// ˢ��
	@Override
	public void onRefresh() {
		Log.i("demo", "onRefresh-->page="+page);
		if (page==1) {
			lvContent.setStopFrash(false);
			Toast.makeText(getActivity(), "�Ѿ�Ϊ��һҳ", Toast.LENGTH_SHORT).show();
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
	// ����
	@Override
	public void onLoadMore() {
		Log.i("demo", "loadMore-->page="+page);
		if (page==20) {
			lvContent.setStopLoading(false);
			Toast.makeText(getActivity(), "�Ѿ�ȫ���������", Toast.LENGTH_SHORT).show();
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
}

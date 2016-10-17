package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import demo.copy.baisi.R;
import demo.copy.baisi.adapter.PictureAdapter;
import demo.copy.baisi.defineview.XListView;
import demo.copy.baisi.defineview.XListView.IXListViewListener;
import demo.copy.baisi.entity.Pictures;
import demo.copy.baisi.presenter.IPicturepresenter;
import demo.copy.baisi.presenter.impl.PicturePresenter;
import demo.copy.baisi.view.IPictureView;

public class PictureFragment extends Fragment implements IPictureView,
		IXListViewListener {

	@ViewInject(R.id.tv_app_header)
	private TextView tvHeader;
	@ViewInject(R.id.lv_picture_fragment)
	private XListView listView;
	// ͼƬtextview��Ϣ
	@ViewInject(R.id.tvMsgInfo)
	private TextView tvMsgInfo;
	// ͼƬ
	// @ViewInject(R.id.ivMsg)
	private ImageView ivMsg;
	private int page = 1;
	private List<Pictures> list;
	private PictureAdapter adapter;
	private IPicturepresenter picturePresenter;
	private Handler mHandler;

	
	@ViewInject(R.id.webView)
	private WebView webview;
	
	
	public PictureFragment() {
		picturePresenter = new PicturePresenter(this, page);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.picture_fragment, null);
		x.view().inject(this, view);
		listView.setPullLoadEnable(true);
		picturePresenter.loadPicture(page);
		// ����̧ͷ���
		setHeaderName();
		// ������
		setLisenners();
		return view;
	}

	// ������
	private void setLisenners() {

		// webview.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// tvMsgInfo.setVisibility(View.VISIBLE);
		// }
		// });
		listView.setXListViewListener(this);
		mHandler = new Handler();
	}

	/**
	 * ����̧ͷ���
	 */
	private void setHeaderName() {
		tvHeader.setText("图片");

	}

	@Override
	public void upadatePictureList(List<Pictures> list) {
		this.list = list;
		adapter = new PictureAdapter(this.list, getActivity(), listView);
		listView.setAdapter(adapter);
		onLoad();
	}

	private void onLoad() {
		Log.i("demo", "onload()-->page=" + page);
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh() {
		if (page == 1) {
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

	protected void getData() {
		picturePresenter.loadPicture(page);
	}

	@Override
	public void onLoadMore() {
		Log.i("demo", "loadMore-->page=" + page);
		if (page == 20) {
			listView.setStopLoading(false);
			Toast.makeText(getActivity(), "已经全部加载完成", Toast.LENGTH_SHORT)
					.show();
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

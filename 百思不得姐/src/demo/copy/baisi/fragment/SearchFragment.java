package demo.copy.baisi.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import demo.copy.baisi.R;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.presenter.impl.SearchPresenter;
import demo.copy.baisi.view.ISearchView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SearchFragment extends Fragment implements ISearchView{
	@ViewInject(R.id.tv_search_button)
	private TextView tvSearch;
	@ViewInject(R.id.et_search)
	private EditText etSearch;
	
	
	private SearchPresenter presenter;
	
	private int page = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search_fragment, null);
		x.view().inject(this, view);
		presenter = new SearchPresenter(this);
		
		setListener();
		return view;
	}

	private void setListener() {
		tvSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String content = etSearch.getText().toString().trim();
				presenter.getSearchInfo(content, page);
			}
		});
	}

	@Override
	public void getSearchContent(List<Object> objects) {
		
		
	}
	
}

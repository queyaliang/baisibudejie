package demo.copy.baisi.presenter.impl;

import java.util.List;

import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.model.ISearchModelCallBack;
import demo.copy.baisi.model.ipml.SearchModel;
import demo.copy.baisi.presenter.ISearchPresenter;
import demo.copy.baisi.view.ISearchView;

public class SearchPresenter implements ISearchPresenter{
	
	private ISearchView view;
	private SearchModel model;

	public SearchPresenter(ISearchView view) {
		this.view = view;
		model = new SearchModel();
	}

	@Override
	public void getSearchInfo(String content, int page) {
		model.loadSearchInfo(content, page, new ISearchModelCallBack() {
			
			@Override
			public void loadPicture(List<Object> objects) {
				
				view.getSearchContent(objects);
				
			}
		});
	}

}

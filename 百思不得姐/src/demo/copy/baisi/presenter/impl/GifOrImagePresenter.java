package demo.copy.baisi.presenter.impl;

import demo.copy.baisi.model.IGifOrImageCallBack;
import demo.copy.baisi.model.ipml.GifOrImageModel;
import demo.copy.baisi.presenter.IGifOrImagePresenter;
import demo.copy.baisi.view.IGifOrImageView;

public class GifOrImagePresenter implements IGifOrImagePresenter{

	private IGifOrImageView view;
	private GifOrImageModel model;

	public GifOrImagePresenter(IGifOrImageView view) {
		
		this.view = view;
		model = new GifOrImageModel();
		
		
	}

	@Override
	public void getGifOrImageUrl(String url) {
		model.getGifOrImageUrl(url, new IGifOrImageCallBack() {
			
			@Override
			public void getGifOrImageUrl(String url) {
				view.getGifOrImageUrl(url);
			}
		});
	}
	

}

package demo.copy.baisi.presenter.impl;

import java.util.List;

import demo.copy.baisi.entity.Pictures;
import demo.copy.baisi.model.IPictureModer;
import demo.copy.baisi.model.IPictureModerCallback;
import demo.copy.baisi.model.ipml.PictureModel;
import demo.copy.baisi.presenter.IPicturepresenter;
import demo.copy.baisi.view.IPictureView;

public class PicturePresenter implements IPicturepresenter{
	private IPictureModer pictureModer;
	private IPictureView pictureView;
	private int page;

	public PicturePresenter(IPictureView pictureView,int page) {
		this.pictureModer=new PictureModel();
		this.pictureView = pictureView;
	}

	@Override
	public void loadPicture(int page) {
		pictureModer.getPictureList(page,new IPictureModerCallback.AsyncCallback() {
			
			@Override
			public void onSuccess(Object success) {
				List<Pictures> lists=(List<Pictures>) success;
				pictureView.upadatePictureList(lists);
			}
			
			@Override
			public void onError(Object error) {
				
			}
		});
	}
}

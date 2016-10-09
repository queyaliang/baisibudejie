package demo.copy.baisi.presenter.impl;

import android.graphics.Bitmap;
import android.util.Log;
import demo.copy.baisi.model.IRadioImageModelCallBack;
import demo.copy.baisi.model.ipml.RadioModel;
import demo.copy.baisi.presenter.IRadioImagePresenter;
import demo.copy.baisi.view.IRadioImageView;

public class RadioImagePresenter implements IRadioImagePresenter{

	private IRadioImageView view;
	private RadioModel model;

	public RadioImagePresenter(IRadioImageView view ) {
		this.view = view;
		model = new RadioModel();
	}

	@Override
	public void getRadioImage(String url ,String name,int position) {
		model.loadRadioImage(url, name,position,new IRadioImageModelCallBack() {
			
			@Override
			public void getRadioImageBitmap(Bitmap bitmap,int position) {
				Log.v("demo", "RadioImagePresenter-->position:"+position);
				view.getRadioImage(bitmap,position);
				
			}
		});
		
	}
	
	

}

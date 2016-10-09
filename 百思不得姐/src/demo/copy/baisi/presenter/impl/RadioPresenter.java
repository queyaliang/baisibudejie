package demo.copy.baisi.presenter.impl;

import java.util.List;

import android.graphics.Bitmap;
import android.util.Log;

import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.model.IRadioImageModelCallBack;
import demo.copy.baisi.model.IRadioModelCallBack;
import demo.copy.baisi.model.ipml.RadioModel;
import demo.copy.baisi.presenter.IRadioPresenter;
import demo.copy.baisi.view.IRadioView;

public class RadioPresenter implements IRadioPresenter{

	private IRadioView view;
	
	private RadioModel model;

	public RadioPresenter(IRadioView view) {
		this.view = view;
		model = new RadioModel();
	
	}

	@Override
	public void getRadio(int page) {
		Log.i("demo", "进入presenter层-->执行其方法");
		model.loadRadio(page, new IRadioModelCallBack() {
			@Override
			public void getRadio(List<Radio> radios) {
				Log.i("demo", "获取model回掉数据,并执行view回掉方法");
				
				view.getRadio(radios);
			}
		});
	}

}

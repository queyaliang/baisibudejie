package demo.copy.baisi.presenter.impl;

import demo.copy.baisi.entity.User;
import demo.copy.baisi.model.IModel;
import demo.copy.baisi.model.IUserModel;
import demo.copy.baisi.model.ipml.UserModel;
import demo.copy.baisi.presenter.IRegistPresenter;
import demo.copy.baisi.view.IRegistView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * Created by hanamingming on 16/3/11.
 */
public class RegistPresenter implements IRegistPresenter {
	private IRegistView view;
	private IUserModel model;

	public RegistPresenter(IRegistView view) {
		this.view = view;
		model = new UserModel();
	}

	@Override
	public void loadImage() {
		model.getImageCode(new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.showCodeImage((Bitmap)success);
			}
			public void onError(Object error) {
			}
		});
	}

	@Override
	public void regist(User user, String code) {
		model.regist(user, code, new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.registSuccess();
			}
			public void onError(Object error) {
				view.registError(error.toString());
			}
		});
	}
}

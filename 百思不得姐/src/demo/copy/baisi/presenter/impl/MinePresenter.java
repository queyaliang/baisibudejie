package demo.copy.baisi.presenter.impl;

import demo.copy.baisi.model.IModel;
import demo.copy.baisi.model.IUserModel;
import demo.copy.baisi.model.ipml.UserModel;
import demo.copy.baisi.presenter.IMinePresenter;
import demo.copy.baisi.view.IMineView;


/**
 * Created by hanamingming on 16/3/6.
 */
public class MinePresenter implements IMinePresenter {
	private IUserModel userModel;
	private IMineView view;

	public MinePresenter(IMineView view) {
		this.view = view;
		this.userModel = new UserModel();
	}

	@Override
	public void loginWithoutPwd(String token) {
		userModel.loginWithoutPwd(token, new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				view.updateUserInfo();
			}
			public void onError(Object error) {
			}
		});
	}
}

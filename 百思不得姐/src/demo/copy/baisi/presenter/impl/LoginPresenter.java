package demo.copy.baisi.presenter.impl;

import demo.copy.baisi.model.IModel;
import demo.copy.baisi.model.IUserModel;
import demo.copy.baisi.model.ipml.UserModel;
import demo.copy.baisi.presenter.ILoginPresenter;
import demo.copy.baisi.view.ILoginView;


/**
 * Created by hanamingming on 16/3/4.
 */
public class LoginPresenter implements ILoginPresenter {

	private ILoginView view;
	private IUserModel model;

	public LoginPresenter(ILoginView view) {
		this.view = view;
		this.model = new UserModel();
	}

	@Override
	public void login(String loginname, String password) {
		model.login(loginname,password,new IModel.AsyncCallback() {
			public void onSuccess(Object success) {
				//³É¹¦
				view.loginSuccess();
			}
			public void onError(Object error) {
				view.loginFailed(error.toString());
			}
		});
	}

}

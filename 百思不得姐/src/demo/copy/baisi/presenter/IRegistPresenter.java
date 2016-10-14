package demo.copy.baisi.presenter;

import demo.copy.baisi.entity.User;


/**
 * Created by hanamingming on 16/3/10.
 */
public interface IRegistPresenter extends Ipresenter{
	/**
	 *加载验证�?	 */
	public void loadImage();

	/**
	 *注册
	 */
	public void regist(User user, String code);

}

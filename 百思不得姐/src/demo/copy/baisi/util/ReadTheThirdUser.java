package demo.copy.baisi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.TheThirdUserInfo;
import demo.copy.baisi.ui.Consts;

public class ReadTheThirdUser {
	public static TheThirdUserInfo read(){
		try {
			File file = new File(BaisiApplication.getApplication().getCacheDir(), Consts.THE_THIRD_USER);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			TheThirdUserInfo user = (TheThirdUserInfo) ois.readObject();
			if(user == null){
				return new TheThirdUserInfo();
			}
			return user;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new TheThirdUserInfo();
	}
}

package demo.copy.baisi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.TheThirdUserInfo;
import demo.copy.baisi.ui.Consts;

public abstract class SaveTheThirdUser {
	public static void save(TheThirdUserInfo user){
		try {
			File file = new File(BaisiApplication.getApplication().getCacheDir(),Consts.THE_THIRD_USER);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(user);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

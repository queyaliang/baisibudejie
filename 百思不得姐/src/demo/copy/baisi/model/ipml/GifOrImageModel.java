package demo.copy.baisi.model.ipml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.model.IGifOrImageCallBack;
import demo.copy.baisi.model.IGifOrImageModel;
import demo.copy.baisi.util.HttpUtils;

public class GifOrImageModel implements IGifOrImageModel{

	@Override
	public void getGifOrImageUrl(final String url,final IGifOrImageCallBack callback) {
		AsyncTask<String, String, String> task = new AsyncTask<String, String, String>(){

			@Override
			protected String doInBackground(String... params) {
				if (url!=null) {
					try {
						InputStream input = HttpUtils.getInputStream(url);
						Bitmap bitmap = BitmapFactory.decodeStream(input);
						
						
						File file = new File(BaisiApplication.getApplication().getCacheDir(),"11.gif");
						if (!file.exists()) {
							file.createNewFile();
							Log.i("demo", "model-->create new file:"+file.exists());
						}
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
						out.writeObject(bitmap);
						out.flush();
						out.close();
						Log.i("demo", "model--->file path---->"+file.getAbsolutePath());
						return file.getAbsolutePath();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			}
			@Override
			protected void onPostExecute(String result) {
				callback.getGifOrImageUrl(result);
			}
			
		};
		task.execute();
		
	}

}

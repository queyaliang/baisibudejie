package demo.copy.baisi.model.ipml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.model.IRadioImageModelCallBack;
import demo.copy.baisi.model.IRadioModel;
import demo.copy.baisi.model.IRadioModelCallBack;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.HttpUtils;
import demo.copy.baisi.util.JsonParse;
import demo.copy.baisi.util.UrlFactory;

public class RadioModel implements IRadioModel{

	@Override
	public void loadRadio(final int page,final IRadioModelCallBack callBack) {
		AsyncTask<String, String, List<Radio>> task = new AsyncTask<String, String, List<Radio>>(){

			@Override
			protected List<Radio> doInBackground(String... params) {
				String url = UrlFactory.getRadioUrl(page);
				
				try {
					InputStream input = HttpUtils.getInputStream(url);
					String json = HttpUtils.isToString(input);
					JSONObject obj =new JSONObject(json);
					if (obj.getInt("showapi_res_code")==Consts.RESPONSE_BACK_CODE) {
						JSONArray array = obj.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
						List<Radio> radios = JsonParse.loadRadio(array);
						Log.i("demo", "成功解析");
						return radios;
					}
				} catch (Exception e) {
					Log.i("demo", "解析失败");
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(List<Radio> result) {
				Log.i("demo", "成功返回数据");
				callBack.getRadio(result);
			}
			
		};
		task.execute();
		
	}

	@Override
	public void loadRadioImage(final String url,final String path,final int position,final IRadioImageModelCallBack callback) {
		AsyncTask<String, String, Bitmap> task = new AsyncTask<String, String, Bitmap>(){

			@Override
			protected Bitmap doInBackground(String... params) {
				try {
					MediaMetadataRetriever retriever = new MediaMetadataRetriever();
					//获取网络视频
					retriever.setDataSource(url, new HashMap<String, String>());
					//获取本地视频
					//retriever.setDataSource(url);
					Bitmap bitmap = retriever.getFrameAtTime(1500,MediaMetadataRetriever.OPTION_CLOSEST);
					FileOutputStream outStream = null;
					outStream = new FileOutputStream(new File(path));
					bitmap.compress(Bitmap.CompressFormat.JPEG, 10, outStream);
					outStream.close();
					retriever.release();
					return bitmap;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(Bitmap bitmap) {
				Log.v("demo", "RadioModel-->loadRadioImage（）--》position:"+position);
				callback.getRadioImageBitmap(bitmap,position);
			}
		};
		task.execute();
		
	}
	
	
	
	
	
	
	

//	RequestQueue mqueue = BaisiApplication.getRequestQueue();
//	@Override
//	public void loadRadio(final int page,final IRadioModelCallBack callBack) {
//		String url = Consts.BAISI_URL;
//		JsonObjectRequest request  =new JsonObjectRequest(url, null,
//				
//				new Listener<JSONObject>() {
//					@Override
//					public void onResponse(JSONObject response) {
//						Log.i("demo", "获取信息--》开始解析"+response.toString());
//						try {
//							if (response.getInt("showapi_res_code")==Consts.RESPONSE_BACK_CODE) {
//								JSONArray array = response.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
//								List<Radio> radios = JsonParse.loadRadio(array);
//								Log.i("demo", "获取信息--》成功");
//								callBack.getRadio(radios);
//							
//							}
//						} catch (JSONException e) {
//							Log.i("demo", "获取信息，解析失败");
//							e.printStackTrace();
//						}
//					}
//				},
//				new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						Log.i("demo", "获取失败");
//						
//					}
//				}){
//			@Override
//			protected Map<String, String> getParams()
//					throws AuthFailureError {
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("type", 41+"");
//				map.put("page", page+"");
//				
//				return map;
//				
//			}
//		};
//		mqueue.add(request);
//	
//	}

}

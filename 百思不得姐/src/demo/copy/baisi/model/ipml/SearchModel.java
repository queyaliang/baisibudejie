package demo.copy.baisi.model.ipml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;

import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.AllFather;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.model.ISearchModel;
import demo.copy.baisi.model.ISearchModelCallBack;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.HttpUtils;
import demo.copy.baisi.util.JsonParse;
import demo.copy.baisi.util.UrlFactory;

public class SearchModel implements ISearchModel{
	
	RequestQueue mqueue = BaisiApplication.getRequestQueue();

	@Override
	public void loadSearchInfo(final String search,final int page, final ISearchModelCallBack callback) {
		AsyncTask<String, String, List<AllFather>> task = new AsyncTask<String, String, List<AllFather>>(){
			@Override
			protected List<AllFather> doInBackground(String... params) {
				try {
					String url = UrlFactory.getSearchContent(search, page);
					InputStream input =HttpUtils.getInputStream(url);
					String json = HttpUtils.isToString(input);
					JSONObject obj = new JSONObject(json);
					if (obj.getInt("showapi_res_code")==Consts.RESPONSE_BACK_CODE) {
						List<AllFather> objects = new ArrayList<AllFather>();
						Log.i("demo", "获取搜索数据内容---》"+json);
						JSONArray array = obj.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
						objects = JsonParse.loadSearch(array);
						return objects;						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(List<AllFather> result) {
				if (result==null) {
					Log.i("demo", "result-->获取数据为空");
					return;
				}
				callback.loadPicture(result);
			}
		};
		task.execute();
	}

}

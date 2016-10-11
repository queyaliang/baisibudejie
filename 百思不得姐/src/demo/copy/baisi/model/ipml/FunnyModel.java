package demo.copy.baisi.model.ipml;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.model.IFunnyModel;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.JsonParse;

public class FunnyModel implements IFunnyModel{
	private RequestQueue queue;

	public FunnyModel() {
		queue = Volley.newRequestQueue(BaisiApplication.getApplication());
	}
	@Override
	public void getFunnyList(final int page,final AsyncCallback callback) {
		// TODO Auto-generated method stub
		String url=Consts.FUNNY_URL+page;
		StringRequest request=new StringRequest(StringRequest.Method.GET, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("aaaaa",response);
				try {
					JSONObject object=new JSONObject(response);
					int code=object.getInt("showapi_res_code");
					if(code==0){
						
						JSONArray array = object.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
						List<Funny> funnys=JsonParse.parseFunnyList(array);
						callback.onSuccess(funnys);
						
					}
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				Log.e("error", "response:"+error.getMessage());
			}
		});
		queue.add(request);
	}

}

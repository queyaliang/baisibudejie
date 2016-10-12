package demo.copy.baisi.model.ipml;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.FirstFlooor;
import demo.copy.baisi.model.VoiceModelCallback;
import demo.copy.baisi.model.IVoiceModel;

public class VoiceModel implements IVoiceModel {
	private RequestQueue queue;
	
	public VoiceModel() {
		queue = Volley.newRequestQueue(BaisiApplication.getApplication());
	}

	public void LoadVoiceList(int i,final VoiceModelCallback callback) {
		String url = "http://route.showapi.com/255-1?showapi_appid=24726&showapi_sign=be678ef28e294c15813cf36ccaf0b3e8&type=31&page="+i;
		StringRequest request = new StringRequest(url, new Response.Listener<String>() {
			public void onResponse(String response) {
				Gson gson = new Gson();
				FirstFlooor resp = gson.fromJson(response, FirstFlooor.class);
				callback.onSuccess(resp.getShowapi_res_body().getPagebean().getContentlist());
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
			}
		});
		queue.add(request);
	}
}

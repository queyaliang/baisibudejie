package demo.copy.baisi.model.ipml;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.User;
import demo.copy.baisi.model.IUserModel;
import demo.copy.baisi.ui.Consts;
import demo.copy.baisi.util.CommonRequest;
import demo.copy.baisi.util.JsonParse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanamingming on 16/3/4.
 */
public class UserModel implements IUserModel {

	private RequestQueue queue;

	public UserModel() {
		queue = Volley.newRequestQueue(BaisiApplication.getApplication());
	}

	@Override
	public void login(final String loginname, final String password, final AsyncCallback callback) {
		String url = Consts.URL_USER_LOGIN;
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Response.Listener<String>() {
			public void onResponse(String response) {
				Log.i("info", response);
				//解析resp
				try {
					JSONObject jsonObject = new JSONObject(response);
					if (jsonObject.getInt("code") == Consts.RESPONSE_CODE_SUCCESS) {
						JSONObject userObj = jsonObject.getJSONObject("user");
						BaisiApplication app = BaisiApplication.getApplication();
						app.saveCurrentUser(JsonParse.parseUser(userObj));
						String token = jsonObject.getString("token");
						app.saveToken(token);
						callback.onSuccess(null);
					} else {
						callback.onError(jsonObject.getString("error_msg"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onError("��¼ʧ��");
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("email", loginname);
				params.put("password", password);
				return params;
			}
		};
		queue.add(request);
	}

	@Override
	public void regist(final User user, final String code, final AsyncCallback callback) {
		String url = Consts.URL_USER_REGIST;
		CommonRequest request = new CommonRequest(Request.Method.POST, url, new Response.Listener<String>() {
			public void onResponse(String response) {
				Log.i("demo", "ע��");
				//解析resp
				try {
					JSONObject jsonObject = new JSONObject(response);
					if (jsonObject.getInt("code") ==Consts.RESPONSE_CODE_SUCCESS) {
						callback.onSuccess(null);
					} else {
						callback.onError(jsonObject.getString("error_msg"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("user.email", user.getEmail());
				map.put("user.nickname", user.getNickname());
				map.put("user.password", user.getPassword());
				map.put("number", code);
				return map;
			}
		};
		queue.add(request);
	}
	
	@Override
	public void getImageCode(final AsyncCallback callback) {
		String uri = Consts.URL_GET_IMAGE_CODE;
		ImageRequest request = new ImageRequest(uri, new Response.Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				callback.onSuccess(response);
			}
		}, 130, 50, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}){
			@Override
			protected Response<Bitmap> parseNetworkResponse(
					NetworkResponse response) {
				Map<String, String> headers = response.headers;
				String sessionid = headers.get("Set-Cookie");
				if (sessionid != null) {
					CommonRequest.JSESSIONID = sessionid.split(";")[0];
				}
				return super.parseNetworkResponse(response);
			}
		};
		queue.add(request);
	}
	
	@Override
	public void loginWithoutPwd(String token, final AsyncCallback callback) {
		String url = Consts.URL_USER_LOGIN_WITHOUT_PWD + "?token=" + token;
		CommonRequest request = new CommonRequest(url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if (jsonObject.getInt("code") == Consts.RESPONSE_CODE_SUCCESS) {
						JSONObject userObj = jsonObject.getJSONObject("user");
						BaisiApplication app = BaisiApplication.getApplication();
						app.saveCurrentUser(JsonParse.parseUser(userObj));
						callback.onSuccess(response);
					} else {
						callback.onError(response);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					callback.onError(response);
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			}
		});
		queue.add(request);

	}

}

package demo.copy.baisi.app;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;

import demo.copy.baisi.entity.ImageResources;
import demo.copy.baisi.entity.User;
import demo.copy.baisi.ui.Consts;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BaisiApplication extends Application{
	
	private static BaisiApplication app;
	private static RequestQueue mqueue;
	
	private User user;
	private String token;
	private List<Integer>images;
	
	private int currentTextSize = 1;
	
	private static List<Activity> activitys = new ArrayList<Activity>();
	
	
	public static void exit(){
		for (int i = 0; i < activitys.size(); i++) {
			activitys.get(i).finish();
		}
		System.exit(CONTEXT_IGNORE_SECURITY);
	}
	public void addActivity(Activity activity){
		activitys.add(activity);
	}
	public void removeActivity(Activity activity){
		activitys.remove(activity);
	}
	

	public int getCurrentTextSize() {
		return currentTextSize;
	}
	public void setCurrentTextSize(int currentTextSize) {
		this.currentTextSize = currentTextSize;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		//初始化地图
		SDKInitializer.initialize(this);
		app = this;
		mqueue = Volley.newRequestQueue(this);
		images=ImageResources.getImage();
	}
	
	
	
	public static  RequestQueue getRequestQueue(){
		
		return mqueue;
		
	}
	public List<Integer> getImages() {
		return images;
	}
	
	public static BaisiApplication getApplication(){
		
		return app;
	}
	
	/**
	 * 保存当前用户
	 * @param user
	 */
	public void saveCurrentUser(User user){
		this.user = user;
	}

	public User getCurrentUser(){
		return this.user;
	}

	public void saveToken(String token){
		this.token = token;
		SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("token",token);
		editor.commit();
	}

	public String getToken(){
		SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
		String token=pref.getString("token","");
		return token;
	}
	

}

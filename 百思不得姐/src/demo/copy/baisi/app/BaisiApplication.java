package demo.copy.baisi.app;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class BaisiApplication extends Application{
	
	private static BaisiApplication app;
	private static RequestQueue mqueue;

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		app = this;
		mqueue = Volley.newRequestQueue(this);
	}
	
	
	
	public static  RequestQueue getRequestQueue(){
		
		return mqueue;
		
	}
	
	public static Application getApplication(){
		
		return app;
		
	}
	

}

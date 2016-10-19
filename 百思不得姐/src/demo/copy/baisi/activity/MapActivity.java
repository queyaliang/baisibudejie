package demo.copy.baisi.activity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.model.LatLng;

import demo.copy.baisi.R;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MapActivity extends Activity {
	MapView mapView;
	//管理地图
	BaiduMap baiduMap;
	//定位
		LocationClient locationClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
			try {
				initViews();
				addListener();
				
				//让框架中的接口指向实现类
				MyBdlocationListener myBdlocationListener=new MyBdlocationListener();
				locationClient=new LocationClient(this);
				locationClient.registerLocationListener(myBdlocationListener);
				
				LocationClientOption option=new LocationClientOption();
				option.setOpenGps(true);
				//设置坐标类型
				option.setCoorType("bd09ll");
				//设置每隔2秒得1下坐标,少于1000,只得一次
				option.setScanSpan(1);
				locationClient.setLocOption(option);
				
				locationClient.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	private void addListener() {
baiduMap.setOnMapClickListener(new OnMapClickListener() {
	
	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		return false;
	}
	
	@Override
	public void onMapClick(LatLng clickPosition) {
Log.i("单击位置", ""+clickPosition.latitude);		
Log.i("单击位置", ""+clickPosition.longitude);		
	}
});
	}

	private void initViews() {
     mapView= (MapView)findViewById(R.id.mapView);
     baiduMap=mapView.getMap();
	}
	//2.3写实现类
	class MyBdlocationListener implements BDLocationListener
	{
		@Override
		public void onReceiveLocation(BDLocation bdLocation) {
			//4.9E-324
			//纬度
			double latitude=bdLocation.getLatitude();
			//经度
			double longitude=bdLocation.getLongitude();
			Log.i("定位成功", "纬度="+latitude+",经度="+longitude);
			if (latitude==4.9E-324)
			{
				//信号不好，没有得到坐标
				//模拟一个坐标
				latitude=39.916322;
				longitude=116.390965;
			}
			//移动地图
			//Status状态
			//坐标点
			LatLng currentLocation=new LatLng(latitude, longitude);
			//地图缩放的级别4-17 17地图显示的详细
			int zoom=17;
			MapStatusUpdate mapStatusUpdate=MapStatusUpdateFactory.newLatLngZoom(currentLocation, zoom);
			//以动画的方式移动地图
			baiduMap.animateMapStatus(mapStatusUpdate);

			//添加图片			
			MarkerOptions markerOptions=new MarkerOptions();
			markerOptions.position(currentLocation);
			
			BitmapDescriptor bitmap=BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
			markerOptions.icon(bitmap);
			baiduMap.addOverlay(markerOptions);
			
			
		}
		
	}
//	 private void initLocation(){  
//	        LocationClientOption option = new LocationClientOption();  
//	        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系  
//	        int span=1000;  
//	        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的  
//	        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要  
//	        option.setOpenGps(true);//可选，默认false,设置是否使用gps  
//	        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果  
//	        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果  
//	        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果  
//	        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死  
//	        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集  
//	        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要  
//	        locationClient.setLocOption(option);  
//	        locationClient.start();  
//	    }  

}

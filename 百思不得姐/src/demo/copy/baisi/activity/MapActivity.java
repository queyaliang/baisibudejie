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
	//�����ͼ
	BaiduMap baiduMap;
	//��λ
		LocationClient locationClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
			try {
				initViews();
				addListener();
				
				//�ÿ���еĽӿ�ָ��ʵ����
				MyBdlocationListener myBdlocationListener=new MyBdlocationListener();
				locationClient=new LocationClient(this);
				locationClient.registerLocationListener(myBdlocationListener);
				
				LocationClientOption option=new LocationClientOption();
				option.setOpenGps(true);
				//������������
				option.setCoorType("bd09ll");
				//����ÿ��2���1������,����1000,ֻ��һ��
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
Log.i("����λ��", ""+clickPosition.latitude);		
Log.i("����λ��", ""+clickPosition.longitude);		
	}
});
	}

	private void initViews() {
     mapView= (MapView)findViewById(R.id.mapView);
     baiduMap=mapView.getMap();
	}
	//2.3дʵ����
	class MyBdlocationListener implements BDLocationListener
	{
		@Override
		public void onReceiveLocation(BDLocation bdLocation) {
			//4.9E-324
			//γ��
			double latitude=bdLocation.getLatitude();
			//����
			double longitude=bdLocation.getLongitude();
			Log.i("��λ�ɹ�", "γ��="+latitude+",����="+longitude);
			if (latitude==4.9E-324)
			{
				//�źŲ��ã�û�еõ�����
				//ģ��һ������
				latitude=39.916322;
				longitude=116.390965;
			}
			//�ƶ���ͼ
			//Status״̬
			//�����
			LatLng currentLocation=new LatLng(latitude, longitude);
			//��ͼ���ŵļ���4-17 17��ͼ��ʾ����ϸ
			int zoom=17;
			MapStatusUpdate mapStatusUpdate=MapStatusUpdateFactory.newLatLngZoom(currentLocation, zoom);
			//�Զ����ķ�ʽ�ƶ���ͼ
			baiduMap.animateMapStatus(mapStatusUpdate);

			//���ͼƬ			
			MarkerOptions markerOptions=new MarkerOptions();
			markerOptions.position(currentLocation);
			
			BitmapDescriptor bitmap=BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
			markerOptions.icon(bitmap);
			baiduMap.addOverlay(markerOptions);
			
			
		}
		
	}
//	 private void initLocation(){  
//	        LocationClientOption option = new LocationClientOption();  
//	        option.setCoorType("bd09ll");//��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ  
//	        int span=1000;  
//	        option.setScanSpan(span);//��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��  
//	        option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ  
//	        option.setOpenGps(true);//��ѡ��Ĭ��false,�����Ƿ�ʹ��gps  
//	        option.setLocationNotify(true);//��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���  
//	        option.setIsNeedLocationDescribe(true);//��ѡ��Ĭ��false�������Ƿ���Ҫλ�����廯���  
//	        option.setIsNeedLocationPoiList(true);//��ѡ��Ĭ��false�������Ƿ���ҪPOI���  
//	        option.setIgnoreKillProcess(false);//��ѡ��Ĭ��false����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ��ɱ��  
//	        option.SetIgnoreCacheException(false);//��ѡ��Ĭ��false�������Ƿ��ռ�CRASH��Ϣ��Ĭ���ռ�  
//	        option.setEnableSimulateGps(false);//��ѡ��Ĭ��false�������Ƿ���Ҫ����gps��������Ĭ����Ҫ  
//	        locationClient.setLocOption(option);  
//	        locationClient.start();  
//	    }  

}

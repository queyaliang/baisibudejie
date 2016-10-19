package demo.copy.baisi.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import demo.copy.baisi.activity.MainActivity;

public class NetworkUtil {	/**
	 * 原生的模拟器，设置成飞行模式，eclipse连不上，最好用真机测
	 * 检测网络状态，没网打开dialog
	 * 
	 * @param activity
	 */
	public static void checkNetworkState(final Activity activity) {
		try {
			// 判断有没有网
			ConnectivityManager connectivityManager = (ConnectivityManager) activity
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			// 得到活动的网络信息
			NetworkInfo activeNetworkInfo = connectivityManager
					.getActiveNetworkInfo();
			if (activeNetworkInfo == null) {
				// 没网显示dialog
				AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
				dialog.setMessage("亲，现在没网");

				dialog.setPositiveButton("打开网络", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							//代码自适配，不同厂商的手机，不同的版本，代码不一样。
							//得手机厂商,
							//有的手机能得手机号，有的得不到
							
							//不同手机厂商 的手机代码不一样
							
							// 得到当前手机的android版本号
							int sdk_int = Build.VERSION.SDK_INT;
							if (sdk_int > 10) {
								// 打开设置中的网络设置界面
								//设置中的所有设置功能，都能打开
								Intent intent = new Intent(
										Settings.ACTION_WIRELESS_SETTINGS);
								
								activity.startActivity(intent);
							} else {
								// android 3.0以前的版本
								Intent intent = new Intent();
								ComponentName component = new ComponentName(
										"com.android.settings",
										"com.android.settings.WirelessSettings");
								intent.setComponent(component);
								intent.setAction("android.intent.action.VIEW");
								activity.startActivity(intent);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				dialog.setNeutralButton("取消", null);
				dialog.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

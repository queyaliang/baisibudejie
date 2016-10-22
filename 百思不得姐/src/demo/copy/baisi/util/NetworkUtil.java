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
	 * ԭ����ģ���������óɷ���ģʽ��eclipse�����ϣ�����������
	 * �������״̬��û����dialog
	 * 
	 * @param activity
	 */
	public static void checkNetworkState(final Activity activity) {
		try {
			// �ж���û����
			ConnectivityManager connectivityManager = (ConnectivityManager) activity
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			// �õ����������Ϣ
			NetworkInfo activeNetworkInfo = connectivityManager
					.getActiveNetworkInfo();
			if (activeNetworkInfo == null) {
				// û����ʾdialog
				AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
				dialog.setMessage("�ף�����û��");

				dialog.setPositiveButton("������", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							//���������䣬��ͬ���̵��ֻ�����ͬ�İ汾�����벻һ����
							//���ֻ�����,
							//�е��ֻ��ܵ��ֻ��ţ��еĵò���
							
							//��ͬ�ֻ����� ���ֻ����벻һ��
							
							// �õ���ǰ�ֻ���android�汾��
							int sdk_int = Build.VERSION.SDK_INT;
							if (sdk_int > 10) {
								// �������е��������ý���
								//�����е��������ù��ܣ����ܴ�
								Intent intent = new Intent(
										Settings.ACTION_WIRELESS_SETTINGS);
								
								activity.startActivity(intent);
							} else {
								// android 3.0��ǰ�İ汾
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

				dialog.setNeutralButton("ȡ��", null);
				dialog.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

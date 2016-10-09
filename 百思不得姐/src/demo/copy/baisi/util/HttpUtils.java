package demo.copy.baisi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.util.Log;

public class HttpUtils {

	public static InputStream getInputStream(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		InputStream input = connect.getInputStream();
		Log.i("demo", "getInputStream()");
		return input;
	}

	/**
	 * 读取输入流  以utf-8的编码解析成String 
	 * @param is
	 * @return
	 */
	public static String isToString(InputStream is)throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line=reader.readLine())!=null){
			sb.append(line);
		}
		String respText = sb.toString();
		return respText;
	}
	

}

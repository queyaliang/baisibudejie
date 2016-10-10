package demo.copy.baisi.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import android.util.Log;

public class UrlFactory {

	public static String getRadioUrl(int page) {

		String url = "http://route.showapi.com/255-1?showapi_appid=24726&showapi_sign=be678ef28e294c15813cf36ccaf0b3e8&type=41&page="+page;
		Log.i("demo", "getRadioUrl()-->url:"+url);
		return url;
	}

	public static String getSearchContent(String content,int page) {
		try {
			content = URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "http://route.showapi.com/255-1?showapi_appid=24726&showapi_sign=be678ef28e294c15813cf36ccaf0b3e8&title"+content+"&page="+page;
		return url;
	}

}

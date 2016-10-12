package demo.copy.baisi.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.User;
import demo.copy.baisi.entity.Voice;

public class JsonParse {
	
	
	public static List<Radio> loadRadio(JSONArray array) throws JSONException{
		List<Radio> radios = new ArrayList<Radio>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			Radio radio = new Radio();
			radio.setText(obj.getString("text"));
			Log.i("demo", "text");
			radio.setHate(obj.getString("hate"));
			Log.i("demo", "hate");
			radio.setVideotime(obj.getString("videotime"));
			Log.i("demo", "videotime");
			radio.setVoicetime(obj.getString("voicetime"));
			Log.i("demo", "texvoicetimet");
			radio.setWeixin_url(obj.getString("weixin_url"));
			Log.i("demo", "weixin_url");
			radio.setProfile_image(obj.getString("profile_image"));
			Log.i("demo", "profile_image");
			radio.setWidth(obj.getString("width"));
			Log.i("demo", "width");
			radio.setVoiceuri(obj.getString("voiceuri"));
			Log.i("demo", "voiceuri");
			radio.setType(obj.getString("type"));
			Log.i("demo", "type");
			radio.setId(obj.getString("id"));
			Log.i("demo", "id");
			radio.setLove(obj.getString("love"));
			Log.i("demo", "love");
			radio.setHeight(obj.getString("height"));
			Log.i("demo", "height");
			radio.setVideo_uri(obj.getString("video_uri"));
			Log.i("demo", "video_uri");
			radio.setVoicelength(obj.getString("voicelength"));
			Log.i("demo", "voicelength");
			radio.setName(obj.getString("name"));
			Log.i("demo", "name");
			radio.setCreate_time(obj.getString("create_time"));
			Log.i("demo", "create_time");
			
			radios.add(radio);
		}
		return radios;
		
	}

	public static List<Object> loadSearch(JSONArray array) {
		List<Object> objects = new ArrayList<Object>();
		
		
		
		
		
		return objects;
	}
	public static User parseUser(JSONObject obj) throws JSONException{
		User user = new User();
		user.setEmail(obj.getString("email"));
		user.setEmailVerify(obj.getBoolean("emailVerify"));
		user.setEmailVerifyCode(obj.getString("emailVerifyCode"));
		user.setId(obj.getInt("id"));
		user.setLastLoginIp(obj.getString("lastLoginIp"));
		user.setLastLoginTime(obj.getLong("lastLoginTime"));
		user.setNickname(obj.getString("nickname"));
		user.setPassword(obj.getString("password"));
		return user;
	}
	
	public static List<Funny> parseFunnyList(JSONArray array)throws JSONException{
		List<Funny> funnys=new ArrayList<Funny>();
		for(int i=0; i<array.length(); i++){
			JSONObject object=array.getJSONObject(i);
			Funny funny=new Funny();
			funny.setText(object.getString("text"));
			funny.setHate(object.getInt("hate"));
			funny.setWeixin_url(object.getString("weixin_url"));
			funny.setType(object.getInt("type"));
			funny.setId(object.getInt("id"));
			funny.setLove(object.getInt("love"));
			funny.setName(object.getString("name"));
			funny.setCreate_time(object.getString("create_time"));
			funny.setProfile_image(object.getString("profile_image"));
			funnys.add(funny);
		}
		return funnys;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package demo.copy.baisi.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

import demo.copy.baisi.entity.AllFather;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Pictures;
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
			radio.setType(obj.getInt("type"));
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

	public static List<AllFather> loadSearch(JSONArray array) throws JSONException {
		List<AllFather> objects = new ArrayList<AllFather>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject json = array.getJSONObject(i);
			int type = json.getInt("type");
			switch (type) {
			case 10:		//Í¼Æ¬
				Pictures picture=new Pictures();
				picture.setText(json.getString("text"));
				picture.setType(json.getInt("type"));
				picture.setName(json.getString("name"));
				picture.setCreate_time(json.getString("create_time"));
				picture.setProfile_image(json.getString("profile_image"));
				picture.setImage0(json.getString("image0"));
				picture.setImage1(json.getString("image1"));
				picture.setImage2(json.getString("image2"));
				picture.setImage3(json.getString("image3"));
				picture.setLove(json.getInt("love"));
				picture.setHate(json.getInt("hate"));
				objects.add(picture);
				break;
			case 29:		//¶Î×Ó
				Funny funny=new Funny();
				funny.setText(json.getString("text"));
				funny.setHate(json.getInt("hate"));
				funny.setWeixin_url(json.getString("weixin_url"));
				funny.setType(json.getInt("type"));
				funny.setId(json.getInt("id"));
				funny.setLove(json.getInt("love"));
				funny.setName(json.getString("name"));
				funny.setCreate_time(json.getString("create_time"));
				funny.setProfile_image(json.getString("profile_image"));
				objects.add(funny);
				break;
			case 31:		//ÉùÒô
				Voice voice = new Voice();
				voice.setText(json.getString("text"));
				voice.setHate(json.getInt("hate"));
				voice.setVideotime(json.getInt("videotime"));
				voice.setVoicetime(json.getInt("voicetime"));
				voice.setWeixin_url(json.getString("weixin_url"));
				voice.setProfile_image(json.getString("profile_image"));
				voice.setWidth(json.getInt("width"));
				voice.setVoiceuri(json.getString("voiceuri"));
				voice.setType(json.getInt("type"));
				voice.setId(json.getDouble("id"));
				voice.setLove(json.getInt("love"));
				voice.setHeight(json.getInt("height"));
				voice.setVoice_uri(json.getString("voice_uri"));
				voice.setVoicelength(json.getInt("voicelength"));
				voice.setName(json.getString("name"));
				voice.setCreate_time(json.getString("create_time"));
				voice.setImage3(json.getString("image3"));
				objects.add(voice);
				break;
			case 41:
				Radio radio = new Radio();
				radio.setText(json.getString("text"));
				Log.i("demo", "text");
				radio.setHate(json.getString("hate"));
				Log.i("demo", "hate");
				radio.setVideotime(json.getString("videotime"));
				Log.i("demo", "videotime");
				radio.setVoicetime(json.getString("voicetime"));
				Log.i("demo", "texvoicetimet");
				radio.setWeixin_url(json.getString("weixin_url"));
				Log.i("demo", "weixin_url");
				radio.setProfile_image(json.getString("profile_image"));
				Log.i("demo", "profile_image");
				radio.setWidth(json.getString("width"));
				Log.i("demo", "width");
				radio.setVoiceuri(json.getString("voiceuri"));
				Log.i("demo", "voiceuri");
				radio.setType(json.getInt("type"));
				Log.i("demo", "type");
				radio.setId(json.getString("id"));
				Log.i("demo", "id");
				radio.setLove(json.getString("love"));
				Log.i("demo", "love");
				radio.setHeight(json.getString("height"));
				Log.i("demo", "height");
				radio.setVideo_uri(json.getString("video_uri"));
				Log.i("demo", "video_uri");
				radio.setVoicelength(json.getString("voicelength"));
				Log.i("demo", "voicelength");
				radio.setName(json.getString("name"));
				Log.i("demo", "name");
				radio.setCreate_time(json.getString("create_time"));
				Log.i("demo", "create_time");
				objects.add(radio);
				break;
			}
		}
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
	
	public static List<Pictures> parsePictureList(JSONArray array)throws JSONException{
		List<Pictures> funnys=new ArrayList<Pictures>();
		for(int i=0; i<array.length(); i++){
			JSONObject object=array.getJSONObject(i);
			Pictures picture=new Pictures();

			picture.setText(object.getString("text"));
			picture.setHate(object.getInt("hate"));
			picture.setWeixin_url(object.getString("weixin_url"));
			picture.setType(object.getInt("type"));
			picture.setId(object.getInt("id"));
			picture.setLove(object.getInt("love"));
			picture.setName(object.getString("name"));
			picture.setCreate_time(object.getString("create_time"));
			picture.setProfile_image(object.getString("profile_image"));
			picture.setImage0(object.getString("image0"));
			picture.setImage1(object.getString("image1"));
			picture.setImage2(object.getString("image2"));
			picture.setImage3(object.getString("image3"));			
			funnys.add(picture);
		}
		return funnys;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

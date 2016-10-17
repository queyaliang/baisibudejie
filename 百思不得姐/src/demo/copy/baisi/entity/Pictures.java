package demo.copy.baisi.entity;

import java.io.Serializable;

public class Pictures  implements Serializable ,AllFather{

	private int id;
	private int love;
	private int hate;
	private int videotime;
	private int voicetime;
	private int type;
	private int width;
	private int height;
	private String create_time;
	private String text;
	private String weixin_url;
	private String profile_image;
	private String voiceuri;
	private String image0;
	private String image2;
	private String image1;
	private String name;
	private String image3;

	public Pictures() {
		super();
	}



	public Pictures(int id, int love, int hate, int videotime, int voicetime,
			int type, int width, int height, String create_time, String text,
			String weixin_url, String profile_image, String voiceuri,
			String image0, String image2, String image1, String name,
			String image3) {
		super();
		this.id = id;
		this.love = love;
		this.hate = hate;
		this.videotime = videotime;
		this.voicetime = voicetime;
		this.type = type;
		this.width = width;
		this.height = height;
		this.create_time = create_time;
		this.text = text;
		this.weixin_url = weixin_url;
		this.profile_image = profile_image;
		this.voiceuri = voiceuri;
		this.image0 = image0;
		this.image2 = image2;
		this.image1 = image1;
		this.name = name;
		this.image3 = image3;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	public int getVideotime() {
		return videotime;
	}

	public void setVideotime(int videotime) {
		this.videotime = videotime;
	}

	public int getVoicetime() {
		return voicetime;
	}

	public void setVoicetime(int voicetime) {
		this.voicetime = voicetime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}



	public String getCreate_time() {
		return create_time;
	}



	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWeixin_url() {
		return weixin_url;
	}

	public void setWeixin_url(String weixin_url) {
		this.weixin_url = weixin_url;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getVoiceuri() {
		return voiceuri;
	}

	public void setVoiceuri(String voiceuri) {
		this.voiceuri = voiceuri;
	}

	public String getImage0() {
		return image0;
	}

	public void setImage0(String image0) {
		this.image0 = image0;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

}

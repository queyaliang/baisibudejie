package demo.copy.baisi.entity;

import java.io.Serializable;

public class Voice implements Serializable{
	private String text;
	private int hate;
	private int videotime;
	private int voicetime;
	private String weixin_url;
	private String profile_image;
	private int width;
	private String voiceuri;
	private int type;
	private double id;
	private int love;
	private int height;
	private String voice_uri;
	private int voicelength;
	private String name;
	private String create_time;
	private String image3;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getVoiceuri() {
		return voiceuri;
	}
	public void setVoiceuri(String voiceuri) {
		this.voiceuri = voiceuri;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getVoice_uri() {
		return voice_uri;
	}
	public void setVoice_uri(String voice_uri) {
		this.voice_uri = voice_uri;
	}
	public int getVoicelength() {
		return voicelength;
	}
	public void setVoicelength(int voicelength) {
		this.voicelength = voicelength;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	@Override
	public String toString() {
		return "Voice [text=" + text + ", hate=" + hate + ", videotime="
				+ videotime + ", voicetime=" + voicetime + ", weixin_url="
				+ weixin_url + ", profile_image=" + profile_image + ", width="
				+ width + ", voiceuri=" + voiceuri + ", type=" + type + ", id="
				+ id + ", love=" + love + ", height=" + height + ", voice_uri="
				+ voice_uri + ", voicelength=" + voicelength + ", name=" + name
				+ ", create_time=" + create_time + ", image3=" + image3 + "]";
	}
}

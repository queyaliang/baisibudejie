package demo.copy.baisi.entity;

public class Radio implements AllFather{

	private String text ;
	private String hate;
	private String videotime;
	private String voicetime;
	private String weixin_url;
	private String profile_image;
	private String width;
	private String voiceuri;
	private int type;
	private String id;
	private String love;
	private String height;
	private String video_uri;
	private String voicelength;
	private String name;
	private String create_time;
	
	public Radio(String text, String hate, String videotime, String voicetime,
			String weixin_url, String profile_image, String width,
			String voiceuri, int type, String id, String love,
			String height, String video_uri, String voicelength, String name,
			String create_time) {
		super();
		this.text = text;
		this.hate = hate;
		this.videotime = videotime;
		this.voicetime = voicetime;
		this.weixin_url = weixin_url;
		this.profile_image = profile_image;
		this.width = width;
		this.voiceuri = voiceuri;
		this.type = type;
		this.id = id;
		this.love = love;
		this.height = height;
		this.video_uri = video_uri;
		this.voicelength = voicelength;
		this.name = name;
		this.create_time = create_time;
	}

	public Radio() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHate() {
		return hate;
	}

	public void setHate(String hate) {
		this.hate = hate;
	}

	public String getVideotime() {
		return videotime;
	}

	public void setVideotime(String videotime) {
		this.videotime = videotime;
	}

	public String getVoicetime() {
		return voicetime;
	}

	public void setVoicetime(String voicetime) {
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

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getVideo_uri() {
		return video_uri;
	}

	public void setVideo_uri(String video_uri) {
		this.video_uri = video_uri;
	}

	public String getVoicelength() {
		return voicelength;
	}

	public void setVoicelength(String voicelength) {
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

	@Override
	public String toString() {
		return "Radio [text=" + text + ", hate=" + hate + ", videotime="
				+ videotime + ", voicetime=" + voicetime + ", weixin_url="
				+ weixin_url + ", profile_image=" + profile_image + ", width="
				+ width + ", voiceuri=" + voiceuri + ", type=" + type + ", id="
				+ id + ", love=" + love + ", height=" + height + ", video_uri="
				+ video_uri + ", voicelength=" + voicelength + ", name=" + name
				+ ", create_time=" + create_time + "]";
	}
	
	

}

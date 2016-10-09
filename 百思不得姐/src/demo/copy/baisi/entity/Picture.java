package demo.copy.baisi.entity;

public class Picture {
	private String text;
    private String hate;
    private String videotime;
    private String voicetime;
    private String weixin_url;
    private String profile_image; 
    private String width;
    private String voiceuri;
    private String type;
    private String image0;
    private String id;
    private String love;
    private String image2;
    private String image1;
    private String height;
    private String name;
    private String create_time;
    private String image3;
    
	public Picture() {
		super();
	}
	
	public Picture(String text, String hate, String videotime,
			String voicetime, String weixin_url, String profile_image,
			String width, String voiceuri, String type, String image0,
			String id, String love, String image2, String image1,
			String height, String name, String create_time, String image3) {
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
		this.image0 = image0;
		this.id = id;
		this.love = love;
		this.image2 = image2;
		this.image1 = image1;
		this.height = height;
		this.name = name;
		this.create_time = create_time;
		this.image3 = image3;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage0() {
		return image0;
	}
	public void setImage0(String image0) {
		this.image0 = image0;
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
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
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
		return "Picture [text=" + text + ", hate=" + hate + ", videotime="
				+ videotime + ", voicetime=" + voicetime + ", weixin_url="
				+ weixin_url + ", profile_image=" + profile_image + ", width="
				+ width + ", voiceuri=" + voiceuri + ", type=" + type
				+ ", image0=" + image0 + ", id=" + id + ", love=" + love
				+ ", image2=" + image2 + ", image1=" + image1 + ", height="
				+ height + ", name=" + name + ", create_time=" + create_time
				+ ", image3=" + image3 + "]";
	}
    
    
    

}

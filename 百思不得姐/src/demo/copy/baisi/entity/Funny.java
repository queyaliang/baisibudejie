package demo.copy.baisi.entity;

import java.io.Serializable;

public class Funny   implements Serializable,AllFather{
	private String text;
	private int hate;
	private String weixin_url;
	private int type;
	private int id;
	private int love;
	private int share;
	private String name;
	private String create_time;
	private String profile_image;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
	public String getWeixin_url() {
		return weixin_url;
	}
	public void setWeixin_url(String weixin_url) {
		this.weixin_url = weixin_url;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	@Override
	public String toString() {
		return "Funny [text=" + text + ", hate=" + hate + ", weixin_url="
				+ weixin_url + ", type=" + type + ", id=" + id + ", love="
				+ love + ", name=" + name + ", create_time=" + create_time
				+ ", profile_image=" + profile_image + "]";
	}
	public Funny(String text,int share, int hate, String weixin_url, int type, int id,
			int love, String name, String create_time, String profile_image) {
		this.text = text;
		this.share = share;
		this.hate = hate;
		this.weixin_url = weixin_url;
		this.type = type;
		this.id = id;
		this.love = love;
		this.name = name;
		this.create_time = create_time;
		this.profile_image = profile_image;
	}
	public Funny(){

	}
}


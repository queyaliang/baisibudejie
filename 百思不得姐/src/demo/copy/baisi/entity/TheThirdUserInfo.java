package demo.copy.baisi.entity;

import java.io.Serializable;

public class TheThirdUserInfo implements Serializable{
	private boolean isValid;
	private String name;
	private String url;
	
	
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}

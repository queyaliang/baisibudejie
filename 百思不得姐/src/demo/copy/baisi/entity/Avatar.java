package demo.copy.baisi.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Avatar implements Serializable{
	
	String name;
	Bitmap bitmap;
	
	public Avatar() {
		super();
	}
	public Avatar(String name, Bitmap bitmap) {
		super();
		this.name = name;
		this.bitmap = bitmap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
	

}

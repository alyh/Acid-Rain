package com.alyhassan.raingame.model;

import android.graphics.Bitmap;

public class RainType {
	
	public static final RainType NORMAL = new RainType("normal",5);
	public static final RainType ACID= new RainType("acid",-10);
		
	private String name;
	private int effect;
	private Bitmap bit;
	
	public RainType(String name, int effect){
		setName(name);
		setEffect(effect);
	}
	
	public void setName(String s){
		name = s;
	}
	
	public void setEffect(int e){
		effect = e;
	}
	
	public String getName(){
		return name;
	}
	
	public int getEffect(){
		return effect;
	}
	
	public void setBitmap(Bitmap bitmap){
		bit = bitmap;
	}

	public Bitmap getImage() {
		return bit;
	}
}

package com.alyhassan.raingame.user;

import java.util.LinkedList;

import com.alyhassan.raingame.R;

public class Background {
	
	private static LinkedList<Background> backgrounds = new LinkedList<Background>();
	
	private String name;
	private int price;
	private int id;
	private boolean purchased = false;
	
	public Background(String name, int price, int id){
		setName(name);
		setPrice(price);
		setId(id);
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setPrice(int p){
		price = p;
	}
	
	public String getName(){
		return name;
	}

	public int getPrice(){
		return price;
	}
	
	public void setId(int i){
		id = i;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isPurchased(){
		return purchased;
	}
	
	public void setPurchased(boolean p){
		purchased = p;
	}
		
	public static void start() {
		backgrounds.add(new Background("day",0,R.id.day));		
		backgrounds.add(new Background("night",1000,R.id.night));
		backgrounds.add(new Background("sunrise",1000,R.id.sunrise));	
		backgrounds.add(new Background("sunset",1000,R.id.sunset));
		backgrounds.get(0).setPurchased(true);
	}
	
	public static LinkedList<Background> getBackgrounds(){
		return backgrounds;
		
	}
}

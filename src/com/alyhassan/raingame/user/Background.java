package com.alyhassan.raingame.user;

import java.util.LinkedList;


public class Background {
	
	private static LinkedList<Background> backgrounds = new LinkedList<Background>();
	
	private String name;
	private int price;
	private boolean purchased = false;
	
	public Background(String name, int price){
		setName(name);
		setPrice(price);
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
	
	public boolean isPurchased(){
		return purchased;
	}
	
	public void setPurchased(boolean p){
		purchased = p;
	}
		
	public static void start() {
		backgrounds.add(new Background("day",0));		
		backgrounds.add(new Background("sunset",1000));
		backgrounds.add(new Background("sunrise",1000));	
		backgrounds.add(new Background("night",1000));
	}
	
	public static LinkedList<Background> getBackgrounds(){
		return backgrounds;
		
	}
}

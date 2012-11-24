package com.alyhassan.raingame.model;

public class Grass {
	
	private int health;
	public static int max = 35;
	
	public Grass(int health){
		setHealth(health);
	}
	
	public void setHealth(int h){
		if(h > max)
			health = max;
		else
			health = h;
	}
	
	public void addDropHit(RainType rt){
		if(health + rt.getEffect() <= max && health + rt.getEffect() >= 0)
			health += rt.getEffect();
		else if(health + rt.getEffect() < 0)
			health = 0;
		else
			health = max;
	}
	
	public boolean isAlive(){
		return health>0;
	}	
	
	public boolean isMax(){
		return health==max;
	}
	
	public int getHealth(){
		return health;
	}
}

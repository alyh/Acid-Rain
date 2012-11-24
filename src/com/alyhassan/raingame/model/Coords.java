package com.alyhassan.raingame.model;

public class Coords {
	
	private int x, y;
	
	public Coords(){
		this(0,0);
	}
	
	public Coords(int x, int y){
		setX(x);
		setY(y);
	}
	
	public void setX(int n){
		x = n;
	}
	
	public void setY(int n){
		y = n;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getActualX(){
		return (x+(x+RainDrop.WIDTH))/2;
	}
	
	public int getActualY(){
		return y + RainDrop.HEIGHT;
	}
	
	public boolean equals(Coords c){
		return getX()==c.getX()&&getY()==c.getY();		
	}
	
	public String toString(){
		return "Coords: ("+x+","+y+")";
	}
}

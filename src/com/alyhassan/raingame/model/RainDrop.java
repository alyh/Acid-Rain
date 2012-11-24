package com.alyhassan.raingame.model;

import android.graphics.Rect;



public class RainDrop {
	
	private RainType type;
	private Coords coords;
	
	public static final int MIN_SPEED = 5, MAX_SPEED = 10;
	public static int speed = MIN_SPEED; 
	
	private int expY;
	private DropManager manager;
	private boolean visible = true, triggered = false;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	
	public RainDrop(RainType dt){
		this(dt,new Coords());
	}
	
	public RainDrop(RainType dt, Coords c){
		this(dt, c, new DropManager());
	}
	
	public RainDrop(RainType dt, Coords c, DropManager f){
		setType(dt);
		setCoords(c);
		manager = f;
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public RainType getType(){
		return type;
	}
	
	public Coords getCoords(){
		return coords;
	}
	
	public void setType(RainType type){
		this.type = type;
	}
	
	public void setCoords(Coords c){
		coords = c;
	}
	
	public boolean fall(){
		getCoords().setY(getCoords().getY() + speed);
		
		if(!triggered && getCoords().getY() >= DropManager.triggerLine){
			manager.triggerDrop();
			triggered = true;
		}else if(getCoords().getY() >= DropManager.FLOOR){	
			if(isVisible())
				manager.changeGrass(this);
			setVisible(false);
			return false;
		}		
		return true;
	}
		
	public boolean isInDanger(){
		return getCoords().getActualY()>=DropManager.DANGER_START && getCoords().getActualY()<=DropManager.DANGER_END;
	}

	public Rect getRect(){
		return new Rect(getCoords().getX(),getCoords().getY(),getCoords().getX()+WIDTH,getCoords().getY()+HEIGHT);
	}
	
	public void setExplosion(int y){
		setVisible(false);
		getCoords().setY(DropManager.FLOOR-(speed*10));
		expY = y;
	}
	
	public int getExpolosionY(){
		return expY;
	}
}

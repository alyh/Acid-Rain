package com.alyhassan.raingame.model;

public class Blocker {
	
	private int angle;
	public static final int RADIUS = 540, SWEEP_ANGLE=15, BASE_ANGLE = 243;
	
	public Blocker(int angle){
		setAngle(angle);
	}
	
	public int getAngle(){
		return angle;
	}	
	
	public void setAngle(int a){
		angle = a;
	}
	
	public double getX(){		
		return (RADIUS*(Math.cos(convertToRadians(BASE_ANGLE-180))-Math.cos(convertToRadians(angle-180))));
	}	
	
	public int getWidth(){		
		return (int) (RADIUS*(Math.cos(convertToRadians(angle-180))-Math.cos(convertToRadians(angle-180+SWEEP_ANGLE))));
	}
	
	public int getY(int ang){		
		int a = 90 - (ang - 180);		
		return 1130 - (int)(RADIUS*Math.cos(convertToRadians(a)));
	}
	
	public static int getAngle(double x){			
		return 297 - ((int) convertToDegrees((Math.acos((x/540) - Math.cos(convertToRadians(63)))))+180)+243;		
	}
	
	private static double convertToRadians(int a){
		return a*(Math.PI/180);
	}
	
	private static double convertToDegrees(double d){
		return d*(180/Math.PI);
	}
}

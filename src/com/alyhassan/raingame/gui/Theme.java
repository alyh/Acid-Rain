package com.alyhassan.raingame.gui;

import java.io.IOException;

import com.alyhassan.raingame.R;
import com.alyhassan.raingame.user.Background;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class Theme {	

	public static final int DAY = 0, NIGHT = 1, SUNRISE = 2, SUNSET = 3;
	public static int selected = DAY;
	
	public static Drawable getBackground(AssetManager a){
		try {
			return new BitmapDrawable(a.open("backgrounds/"+Background.getBackgrounds().get(selected).getName()+".png"));
		} catch (IOException e) { return null; }
	}
	
	
	public static Drawable createButtonBackgrounds(Resources resources) {
		StateListDrawable states = null;
		if(Background.getBackgrounds().get(selected).getName().equals("night")){
			states = new StateListDrawable();
	        states.addState(new int[] {android.R.attr.state_pressed},resources.getDrawable(R.drawable.night_button_click));
	        states.addState(new int[] {android.R.attr.state_focused},resources.getDrawable(R.drawable.night_button_color));
	        states.addState(new int[] { },resources.getDrawable(R.drawable.night_button_color));
	        
		}else if(Background.getBackgrounds().get(selected).getName().equals("sunset")){
			states = new StateListDrawable();
	        states.addState(new int[] {android.R.attr.state_pressed},resources.getDrawable(R.drawable.sunset_button_click));
	        states.addState(new int[] {android.R.attr.state_focused},resources.getDrawable(R.drawable.sunset_button_color));
	        states.addState(new int[] { },resources.getDrawable(R.drawable.sunset_button_color));
	        
		}else if(Background.getBackgrounds().get(selected).getName().equals("sunrise")){
			states = new StateListDrawable();
	        states.addState(new int[] {android.R.attr.state_pressed},resources.getDrawable(R.drawable.sunrise_button_click));
	        states.addState(new int[] {android.R.attr.state_focused},resources.getDrawable(R.drawable.sunrise_button_color));
	        states.addState(new int[] { },resources.getDrawable(R.drawable.sunrise_button_color));
	        
		}else{
			states = new StateListDrawable();
	        states.addState(new int[] {android.R.attr.state_pressed},resources.getDrawable(R.drawable.main_button_click));
	        states.addState(new int[] {android.R.attr.state_focused},resources.getDrawable(R.drawable.main_button_color));
	        states.addState(new int[] { },resources.getDrawable(R.drawable.main_button_color));
		}
		return states;
	}
	
	public static int getTextColor(){
		if(Background.getBackgrounds().get(selected).getName().equals("night")){
			return Color.WHITE;
		}else{
			return Color.BLACK;
		}
	}

}

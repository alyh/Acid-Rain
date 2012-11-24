package com.alyhassan.raingame.model;

import java.util.LinkedList;

import com.alyhassan.raingame.gui.GameScreen;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class FallThread extends Thread{
	
	private SurfaceHolder surface;
    private GameScreen game;
    private boolean run = false;
	
	public FallThread(SurfaceHolder s, GameScreen g){
		surface = s;
		game = g;
	}
	
	public void setRunning(boolean b){
		run = b;
	}
	
	public boolean isRunning(){
		return run;
	}
	
	@Override
	public void run(){
		Canvas c;
        while (run) {
            c = null;
            try {     	            	 
            	c = surface.lockCanvas(null);
                synchronized (surface) {
                	
                	LinkedList<RainDrop> drops = game.getDropManager().getAllDrops();
                	
                	for(int i=0;i<drops.size();i++){
                		 if(!drops.get(i).fall()){
                			 drops.remove(i);
                		 }else if(drops.get(i).isInDanger()){
                			 game.getDropManager().checkHit(i);
                		 }
             	    }
                    game.onDraw(c);
                    game.postInvalidate();
                }
            }finally{
            	if (c != null) {
                    surface.unlockCanvasAndPost(c);
                }
            }
        }
	}

}

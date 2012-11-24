package com.alyhassan.raingame.gui;

import java.util.LinkedList;

import com.alyhassan.raingame.model.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback{

	private DropManager manager;
	private FallThread thread;
	private boolean started = false, ended = false;
	private int angle = 235, amount;
	private final int SENSITVITY = 240;
	private GamePlayActivity parent;
	
	public GameScreen(Context context, AttributeSet attrs) {
		super(context,attrs);
		getHolder().addCallback(this);
		manager = new DropManager();
		manager.triggerDrop();
		setFocusable(true);
		Log.d("egg","Game Screen Launched");
		amount = SENSITVITY/39;
	}
	
	public void setParent(GamePlayActivity a){
		parent = a;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		
		if(!started){
			Paint paint = new Paint(); 
			paint.setColor(Theme.getTextColor()); 
			paint.setTextSize(42);
			float x = (canvas.getWidth()-paint.measureText("Tap Anywhere to Start"))/2;
			canvas.drawText("Tap Anywhere to Start", x, 400, paint);
			return;
		}
		
		Paint paint = new Paint(); 
		
		canvas.drawColor(Color.TRANSPARENT);
		LinkedList<RainDrop> drops = manager.getAllDrops();
    	if(manager.isPlaying())
	    	for(int i=0;i<drops.size();i++){
	  			if(drops.get(i).getType().getEffect()>0)
	    			paint.setColor(Color.argb(200,0,0,255));
	    		else
	    			paint.setColor(Color.argb(200,42,141,15));
	    		if(drops.get(i).isVisible())
	    			canvas.drawRect(drops.get(i).getRect(), paint);
	    		else{
	    			canvas.drawRect(drops.get(i).getCoords().getX()-10,drops.get(i).getExpolosionY()-5,
	    					drops.get(i).getCoords().getX()-5,drops.get(i).getExpolosionY(), paint);
	    			canvas.drawRect(drops.get(i).getCoords().getX()+10,drops.get(i).getExpolosionY()-5,
	    					drops.get(i).getCoords().getX()+5,drops.get(i).getExpolosionY(), paint);
	    		}
	    	}
    	RectF r = new RectF(-300,590,780,1670);
    	paint.setColor(Theme.getTextColor());
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeWidth(6.5f);
    	canvas.drawArc(r, angle, 15, false, paint);        
    	paint.setStrokeWidth(1f);
    	paint.setStyle(Paint.Style.FILL); 
    	paint.setColor(Color.argb(40,12,12,12));    	
    	canvas.drawArc(r, 220, 110, true, paint);    
    	
                 
        paint.setColor(Theme.getTextColor()); 
		paint.setTextSize(24);
        
        canvas.drawText("Score: "+manager.getScores()[0],200, 50, paint);
      	canvas.drawText("Health: "+manager.getGrass().getHealth(), 30, 50, paint);
      	
      	if(!manager.isPlaying()){
      		this.pause();
      		manager.endGame();
      		paint.setTextSize(42);
			float x = (canvas.getWidth()-paint.measureText("Game Over"))/2;			
	      	canvas.drawText("Game Over", x, 340, paint);
	      	if(!ended)
	      		startNext();
	      	pause();
      	}
	}
	
	private void startNext() {
		 Thread t = new Thread(){
			public void run(){
				try{
					sleep(1200);
					parent.endGame(manager.getScores());
				} catch (InterruptedException e) {}
			}
		};
		t.start();
		ended = true;
	}

	private int getAngle(double x){	 
		if(x > 335)
			return 282;
		else if(x < (480-SENSITVITY)/2)
			return 243;
		return (int)(x-((480-SENSITVITY)/2))/amount + 243;
	}
	
	public DropManager getDropManager(){
		return manager;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.setWillNotDraw(false);
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}
	
	
	 @Override
	 public boolean onTouchEvent(MotionEvent event) {
		 angle = getAngle(event.getX());		
		 manager.getBlocker().setAngle(angle);
		 
		 if (event.getAction() == MotionEvent.ACTION_DOWN) {
	    	 if(thread == null){
	    		 resume();
	         }
	     }
		 postInvalidate();
	     return true;
	 }

	public void resume() {
		thread = new FallThread(getHolder(),this);
		thread.setRunning(true);
		thread.start();
		started = true;
	}
	
	public void pause() {
		if(thread !=null){
			thread.setRunning(false);
			thread = null;
		}
	}
}

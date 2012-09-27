package com.alyhassan.raingame.gui;
import com.alyhassan.egggame.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

public class GamePlayActivity extends Activity {
    /** Called when the activity is first created. */
	private GameScreen game;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.gamescreen);
        game = (GameScreen) findViewById(R.id.gameview);
        game.setBackgroundDrawable(Theme.getBackground(getAssets()));
        game.setParent(this);
        
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		Log.d("egg","Paused");		
		game.pause();
		this.finish();
	}
	
	public void endGame(int[] scores){
		Intent i = new Intent("com.alyhassan.raingame.POSTGAME");
		i.putExtra("score", scores);
		startActivity(i);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		game.setBackgroundDrawable(Theme.getBackground(getAssets()));
	} 
	
}
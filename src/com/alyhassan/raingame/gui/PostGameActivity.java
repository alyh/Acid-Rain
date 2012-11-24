package com.alyhassan.raingame.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alyhassan.raingame.R;
import com.alyhassan.raingame.user.HighScore;

public class PostGameActivity extends Activity implements OnClickListener{

	private Button mainmenu, playagain;
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.postgame);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
        playagain = (Button) findViewById(R.id.playagain);
        playagain.setTypeface(tf,Typeface.BOLD);
        playagain.setOnClickListener(this);
	    
	    mainmenu = (Button) findViewById(R.id.mainmenu);
	    mainmenu.setTypeface(tf,Typeface.BOLD);
	    mainmenu.setOnClickListener(this);
	    
	    TextView score = (TextView) findViewById(R.id.scoreView);
	    
	    int[] s = getIntent().getIntArrayExtra("score");
	    
	    score.setText(s[0]+"");
	 //   score.setTextColor(Theme.getTextColor());
	    HighScore.addToTotal(s[0]);
	    HighScore.games+=1;
	    HighScore.totaldrops+=s[0];
	    HighScore.offerScore(s[0]);
	    HighScore.save();
	    
	    
	    LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
        background.setBackgroundDrawable(Theme.getBackground(getAssets()));
        
        playagain.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        mainmenu.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
	    
	    TextView coins = (TextView) findViewById(R.id.coins);
	    coins.setText("Total Coins: " +HighScore.getTotal());
	    coins.setTextColor(Theme.getTextColor());
	    
	    TextView title = (TextView) findViewById(R.id.textView1);
	    title.setTextColor(Theme.getTextColor());
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		if(v == playagain)
			startActivity(new Intent("com.alyhassan.raingame.PLAYGAME"));
		else if(v==mainmenu)
			finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
        background.setBackgroundDrawable(Theme.getBackground(getAssets()));
        
        playagain.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        mainmenu.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
	} 

}

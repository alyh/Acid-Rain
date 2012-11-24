package com.alyhassan.raingame.gui;


import com.alyhassan.raingame.R;
import com.alyhassan.raingame.user.Background;
import com.alyhassan.raingame.user.HighScore;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	private Button play, stats, shop, help;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mainmenu);
       
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
        play = (Button) findViewById(R.id.play);
        play.setTypeface(tf,Typeface.BOLD);
	    play.setOnClickListener(this);
	    
	    stats = (Button) findViewById(R.id.garden);
        stats.setTypeface(tf,Typeface.BOLD);
        stats.setOnClickListener(this);
        
        shop = (Button) findViewById(R.id.shop);
        shop.setTypeface(tf,Typeface.BOLD);
        shop.setOnClickListener(this);
        
        help = (Button) findViewById(R.id.help);
        help.setTypeface(tf,Typeface.BOLD);
        help.setOnClickListener(this);
        
        TextView title = (TextView)findViewById(R.id.textView1);
        title.setTypeface(tf, Typeface.BOLD);
        
        Background.start();
        HighScore.load();
        
        LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
        background.setBackgroundDrawable(Theme.getBackground(getAssets()));
        
        play.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        stats.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        shop.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        help.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        
      }

	@Override
	public void onClick(View v) {
		Button b = (Button)v;
    	Log.d("egg", b.getText()+"");
		if(v==play)
			startActivity(new Intent("com.alyhassan.raingame.PLAYGAME"));	
		else if(b.getText().equals("Stats"))
			startActivity(new Intent("com.alyhassan.raingame.GARDEN"));
		else if(b.getText().equals("Theme"))
			startActivity(new Intent("com.alyhassan.raingame.SHOP"));
		else if(b.getText().equals("Help"))
			startActivity(new Intent("com.alyhassan.raingame.HELP"));
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
        background.setBackgroundDrawable(Theme.getBackground(getAssets()));
        
        play.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        stats.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        shop.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        help.setBackgroundDrawable(Theme.createButtonBackgrounds(getResources()));
        
	} 
	
	
	
}
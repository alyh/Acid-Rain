package com.alyhassan.raingame.gui;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alyhassan.egggame.R;
import com.alyhassan.raingame.user.Background;
import com.alyhassan.raingame.user.HighScore;

public class ShopActivity extends Activity implements OnClickListener {
	
	private Button night,day,sunrise,sunset;
	private TextView coins;
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.shop);
       
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
        night = (Button) findViewById(R.id.night);
        night.setTypeface(tf,Typeface.BOLD);
        night.setOnClickListener(this);
        if(Background.getBackgrounds().get(3).isPurchased() && Theme.selected == 3){
        	night.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_night2));
        }else if(Background.getBackgrounds().get(3).isPurchased()){
        	night.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_night3));
        }else{
        	night.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_night));
        }
	    
	    day = (Button) findViewById(R.id.day);
	    day.setTypeface(tf,Typeface.BOLD);
	    day.setOnClickListener(this);
	    if(Theme.selected == 0){
	    	day.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_day2));
        }else{
        	day.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_day3));
        }
	    
	    sunrise = (Button) findViewById(R.id.sunrise);
	    sunrise.setTypeface(tf,Typeface.BOLD);
	    sunrise.setOnClickListener(this);
	    if(Background.getBackgrounds().get(2).isPurchased() && Theme.selected == 2){
	    	sunrise.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunrise2));
        }else if(Background.getBackgrounds().get(2).isPurchased()){
        	sunrise.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunrise3));
        }else{
        	sunrise.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunrise));
        }
	    
	    sunset = (Button) findViewById(R.id.sunset);
	    sunset.setTypeface(tf,Typeface.BOLD);
	    sunset.setOnClickListener(this);
	    if(Background.getBackgrounds().get(1).isPurchased() && Theme.selected == 1){
	    	sunset.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunset2));
        }else if(Background.getBackgrounds().get(1).isPurchased()){
        	sunset.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunset3));
        }else{
        	sunset.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunset));
        }
	    
	    coins = (TextView)findViewById(R.id.coins);
	    coins.setText("Coins: "+HighScore.getTotal());
	   
	    LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
	    background.setBackgroundDrawable(Theme.getBackground(getAssets()));  
	   
	    night.setTextColor(Theme.getTextColor());
	    day.setTextColor(Theme.getTextColor());
	    sunrise.setTextColor(Theme.getTextColor());
	    sunset.setTextColor(Theme.getTextColor());
	    
   }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		if(v == night){
			if(Background.getBackgrounds().get(3).isPurchased()){
				changeOld(Theme.selected);
				Theme.selected = 3;
				night.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_night2));
			}else if(HighScore.getTotal() >= Background.getBackgrounds().get(3).getPrice()){
				HighScore.addToTotal(-Background.getBackgrounds().get(3).getPrice());
				Background.getBackgrounds().get(3).setPurchased(true);
				changeOld(Theme.selected);
				Theme.selected = 3;
				night.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_night2));
				Toast.makeText(getApplicationContext(), "Background Purchased!", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "Not enough coins!", Toast.LENGTH_SHORT).show();
			}
		}else if(v==day){
			changeOld(Theme.selected);
			Theme.selected = 0;
			day.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_day2));			
		}else if(v==sunset){
			if(Background.getBackgrounds().get(1).isPurchased()){
				changeOld(Theme.selected);
				Theme.selected = 1;
				sunset.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunset2));
			}else if(HighScore.getTotal() >= Background.getBackgrounds().get(1).getPrice()){
				HighScore.addToTotal(-Background.getBackgrounds().get(1).getPrice());
				Background.getBackgrounds().get(1).setPurchased(true);
				changeOld(Theme.selected);
				Theme.selected = 1;
				sunset.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunset2));
				Toast.makeText(getApplicationContext(), "Background Purchased!", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "Not enough coins!", Toast.LENGTH_SHORT).show();
			}
		}else if(v==sunrise){
			if(Background.getBackgrounds().get(2).isPurchased()){
				changeOld(Theme.selected);
				Theme.selected = 2;
				sunrise.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunrise2));
			}else if(HighScore.getTotal() >= Background.getBackgrounds().get(2).getPrice()){
				HighScore.addToTotal(-Background.getBackgrounds().get(2).getPrice());
				Background.getBackgrounds().get(2).setPurchased(true);
				changeOld(Theme.selected);
				Theme.selected = 2;
				sunrise.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunrise2));
				Toast.makeText(getApplicationContext(), "Background Purchased!", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "Not enough coins!", Toast.LENGTH_SHORT).show();
			}
		}
		
		LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
	    background.setBackgroundDrawable(Theme.getBackground(getAssets()));  

	    night.setTextColor(Theme.getTextColor());
	    day.setTextColor(Theme.getTextColor());
	    sunrise.setTextColor(Theme.getTextColor());
	    sunset.setTextColor(Theme.getTextColor());
	    
	    coins.setText("Coins: "+HighScore.getTotal());
	    HighScore.save();
	}
	
	private void changeOld(int n){
		Log.d("egg", n+" changed");
		if(n==0){
			day.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_day3));
		}else if(n==1){
			sunset.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunset3));
		}else if(n==2){
			sunrise.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_sunrise3));
		}else if(n==3){
			night.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_night3));
		}
	}
}

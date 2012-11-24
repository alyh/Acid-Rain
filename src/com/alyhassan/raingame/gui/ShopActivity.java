package com.alyhassan.raingame.gui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alyhassan.raingame.R;
import com.alyhassan.raingame.user.Background;
import com.alyhassan.raingame.user.HighScore;

public class ShopActivity extends Activity implements OnClickListener {
	
	private Button[] button;
	private TextView coins;
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.shop);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
        button = new Button[Background.getBackgrounds().size()];
        
        for(int i=0;i<button.length;i++){
        	button[i] = (Button) findViewById(Background.getBackgrounds().get(i).getId());
        	button[i].setTypeface(tf,Typeface.BOLD);
        	if(Theme.selected == i){
        		if(Theme.selected == Theme.NIGHT)
            		button[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_checked_white));
        		else
        			button[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_checked));
        	}else if(Background.getBackgrounds().get(i).isPurchased()||Background.getBackgrounds().get(i).getPrice()==0)
        		button[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner));
        	else
        		button[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_locked));
        	button[i].setTextColor(Theme.getTextColor());
        	button[i].setOnClickListener(this);
        }
          
	    coins = (TextView)findViewById(R.id.coins);
	    coins.setText("Coins: "+HighScore.getTotal());
	    LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
	    background.setBackgroundDrawable(Theme.getBackground(getAssets()));  	    
   }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		int code = 0;
		for(int i=0;i<button.length;i++){
			if(button[i].getId() == v.getId())
				code = i;
		}
		
		if(Background.getBackgrounds().get(code).isPurchased()){
			changeOld(Theme.selected);
			Theme.selected = code;
			if(code == Theme.NIGHT)
				button[code].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_checked_white));
			else
				button[code].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_checked));
		}else if(HighScore.getTotal() >= Background.getBackgrounds().get(code).getPrice()){
			HighScore.addToTotal(-Background.getBackgrounds().get(code).getPrice());
			Background.getBackgrounds().get(code).setPurchased(true);
			changeOld(Theme.selected);
			Theme.selected = code;
			if(code == Theme.NIGHT)
				button[code].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_checked_white));
			else
				button[code].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_checked));
			Toast.makeText(getApplicationContext(), "Background Purchased!", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplicationContext(), "Not enough coins!", Toast.LENGTH_SHORT).show();
		}
						
		LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
	    background.setBackgroundDrawable(Theme.getBackground(getAssets()));  

	    for(Button b : button)
	    	b.setTextColor(Theme.getTextColor());
	    
	    coins.setText("Coins: "+HighScore.getTotal());
	    HighScore.save();
	}
	
	private void changeOld(int n){
		Log.d("egg", n+" changed");
		button[n].setBackgroundDrawable(getResources().getDrawable(R.drawable.banner));
	}
}

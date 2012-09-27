package com.alyhassan.raingame.gui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alyhassan.egggame.R;
import com.alyhassan.raingame.user.HighScore;

public class GardenActivity extends Activity implements OnClickListener{
	 /** Called when the activity is first created. */
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.garden);
        
        LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
        background.setBackgroundDrawable(Theme.getBackground(getAssets()));
             
        TextView title = (TextView)findViewById(R.id.record);
        title.setText(HighScore.getHighScore()+"");
        
        TextView stats = (TextView)findViewById(R.id.stats);
        stats.setTextColor(Theme.getTextColor());
        stats.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ArchitectsDaughter.ttf"));
        if(HighScore.games>0)
        	stats.setText("Total Games: "+HighScore.games+"\n\n" +  
        		"Drops Blocked: "+ HighScore.totaldrops+"\n\n"+
        		"Average Score: "+(HighScore.totaldrops/HighScore.games));
        else
        	stats.setText("No stats yet!");
      }

	@Override
	public void onClick(View v) {
		
			
	} 
}

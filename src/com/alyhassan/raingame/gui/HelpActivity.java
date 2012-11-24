package com.alyhassan.raingame.gui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alyhassan.raingame.R;

public class HelpActivity extends Activity{
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        setContentView(R.layout.help);
	       
	        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
	        TextView text = (TextView)findViewById(R.id.info);
	        text.setTextColor(Theme.getTextColor());
	        text.setTypeface(tf);
	        text.setText(Html.fromHtml("• Your goal is to block the acid rain but let the normal rain pass.<br/><br/>" +
	        		"• Each acid rain drop does 10 points damage to your grass! Normal rain adds 5 health until you reach the max health of 35!<br/><br/>" +
	        		"• Keep going until your health reaches 0 and then you'll get a coin for every drop blocked.<br/><br/>" +
	        		"• You can use coins to buy new themes!<br/><br/> &#169; Aly Hassan"));
	        
	        LinearLayout background = (LinearLayout)findViewById(R.id.mainLayout);
	        background.setBackgroundDrawable(Theme.getBackground(getAssets()));
	        	        
	      }
}

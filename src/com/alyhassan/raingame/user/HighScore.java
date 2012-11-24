package com.alyhassan.raingame.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.alyhassan.raingame.gui.Theme;

import android.os.Environment;
import android.util.Log;

public class HighScore {
	
	private static int highscore = 0, total = 0;
	public static int games,totaldrops;
	
	public static int getHighScore(){
		return highscore;
	}
	
	public static boolean offerScore(int score){
		int old = highscore;
		if(score > highscore)
			highscore = score;
		return score > old;
	}
	
	public static int getTotal(){
		return total;
	}
	
	public static void addToTotal(int n){
		total += n;
	}
	
	public static void save(){
		try {
			File root = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.alyhassan.raingame/");
	        if(!root.exists())
	        	root.mkdirs();
	        File file = new File(root, "user.txt");
			
			PrintWriter out = new PrintWriter(file);

			out.println(highscore);	
			out.println(total);
			out.println(Theme.selected);
			for(int i=0;i<Background.getBackgrounds().size();i++)
				out.println(Background.getBackgrounds().get(i).isPurchased()?"yes":"no");
			out.println(games);
			out.println(totaldrops);
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {}
	}
	
	public static void load(){
		File root = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.alyhassan.raingame/");
		
        File file = new File(root, "user.txt");
        
        if(!file.exists())
        	return;
        
        try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    highscore = Integer.parseInt(br.readLine());		   
		    total = Integer.parseInt(br.readLine());
		    Theme.selected = Integer.parseInt(br.readLine());	
		    for(int i=0;i<Background.getBackgrounds().size();i++){
		    	if(Background.getBackgrounds().get(i)==null){
		    		Log.e("egg","Background is null ; "+br.readLine());
		    	}else{
		    		Background.getBackgrounds().get(i).setPurchased(br.readLine().equals("yes"));
		    	}
		    }
		    String s = br.readLine();
		    games = Integer.parseInt(s==null?"0":s);
		    s =  br.readLine();
		    totaldrops = Integer.parseInt(s==null?"0":s);
		    
		}catch (IOException e) {
		}catch (NullPointerException e){
			Log.e("egg","Null pointer exception!");
		}

	}

}

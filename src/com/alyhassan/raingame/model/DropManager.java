package com.alyhassan.raingame.model;

import java.util.LinkedList;

public class DropManager {
	
	private LinkedList<RainDrop> drops;
	public final static int FLOOR = 800, ROOF = 0, DANGER_START = 580, DANGER_END = 660;
	public static int triggerLine = 200;
	private int total, scoreAcid, scoreNormal, score;
	private boolean playing;
	private Grass grass;
	private Blocker blocker;
	
	public DropManager(){
		drops = new LinkedList<RainDrop>();	
		blocker = new Blocker(240);
		grass = new Grass(30);
		playing = true;
		RainDrop.speed = RainDrop.MIN_SPEED;
	}
	
	public LinkedList<RainDrop> getAllDrops(){
		return drops;
	}
	
	public void triggerDrop(){
		int r = (int)(Math.random()*12);
		if(r==8)
			drops.offer(new RainDrop(RainType.NORMAL,getRandomCoords(),this));
		else
			drops.offer(new RainDrop(RainType.ACID,getRandomCoords(),this));
		total++;
		if(total%20==0)
			this.increaseSpeed();
	}
	
	public void removeLeadingDrop(){
		drops.removeFirst();
	}
	
	public Coords getRandomCoords(){
		return new Coords((int)(Math.random()*400) + 1,ROOF);
	}
	
	public Blocker getBlocker(){
		return blocker;
	}
	
	public int[] getScores(){
		int[] i = {score,scoreAcid,scoreNormal};
		return  i;
	}
	
	public void checkHit(int index){
		RainDrop drop = drops.get(index);
		int x = drop.getCoords().getActualX();
		int y = drop.getCoords().getActualY();
		if(Math.abs(blocker.getY(Blocker.getAngle(x))-y)<RainDrop.speed 
				&& Blocker.getAngle(x) >= blocker.getAngle() 
				&& Blocker.getAngle(x) <= blocker.getAngle()+Blocker.SWEEP_ANGLE){
			drops.get(index).setExplosion(blocker.getY(Blocker.getAngle(x)));	
			if(drops.get(index).getType().getEffect()<0)
				scoreAcid++;
			else
				scoreNormal++;
			score++;
		}
	}
	
	public void changeGrass(RainDrop drop){
		grass.addDropHit(drop.getType());
		if(!grass.isAlive())
			playing = false;
	}
	
	public Grass getGrass(){
		return grass;
	}
	
	public void increaseSpeed(){
		if(RainDrop.speed < RainDrop.MAX_SPEED)
			RainDrop.speed+=1;
	}
	
	public boolean isPlaying(){
		return playing;
	}
	
	public void endGame(){
		for(int i=0;i<drops.size();i++)
			drops.remove(i);
	}
}

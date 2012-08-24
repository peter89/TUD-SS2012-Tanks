package de.tu_darmstadt.gdi1.tanks.objects;

public class Map extends TanksObjects {

	public String actualMap, nextMap;
	protected int maxDuration, elapsedTime, shots;

	public Map(String id) {
		super(id);
	}
	
	public void setShots(int shots){
		this.shots = shots;
	}
	
	public void setActualMap(String actualMap){
		this.actualMap = actualMap;
	}
	
	public void setNextMap(String nextMap){
		this.nextMap = nextMap;
	}
	
	public void setMaxDuration(int maxDuration){
		this.maxDuration = maxDuration;
	}
	
	public void setElapsedTime(int elapsedTime){
		this.elapsedTime = elapsedTime;
	}
	
	
}

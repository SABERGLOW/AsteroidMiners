package main;

import java.util.ArrayList;

public class Inventory {
	ArrayList<Resources> currentLoad = new ArrayList<Resources>();
	int totalLoad = 10;
	
	public ArrayList<Resources> GetCurrentLoad(){
		return currentLoad;
	}
	
	public void addResource(Resources res) {
		if(currentLoad.size() < totalLoad) {
			currentLoad.add(res);
			System.out.println(res.TypeOfMineral() + " added.");
			
		}else {
			System.out.println("Inventory full!");
		}
	}
	
}

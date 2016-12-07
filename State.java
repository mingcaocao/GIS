package gis;

import java.util.ArrayList;

public class State {
	
	int stateNumber;
	ArrayList<Location> locations;
	
	public State(int stateNumber){
		this.stateNumber = stateNumber;
		this.locations = new ArrayList<Location>();
	}
	
	public void addLocation(Location location){
		locations.add(location);
	}
	
	public ArrayList<Location> getLocations(){
		return locations;
	}
	
}

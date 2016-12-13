package application;

import java.util.ArrayList;

/**
 * This is the state class
 * @author Ming Cao
 *
 */
public class State implements StateInterface {
	// every state has a distinct state number
	int stateNumber;
	// save the locations
	ArrayList<Location> locations;
	
	public State(int stateNumber){
		this.stateNumber = stateNumber;
		this.locations = new ArrayList<Location>();
	}
	
	/**
	 * This is the method to add the locations into the list
	 */
	@Override
	public void addLocation(Location location){
		locations.add(location);
	}
	
	/**
	 * This is the getter method of location
	 */
	@Override
	public ArrayList<Location> getLocations(){
		return locations;
	}
	
}

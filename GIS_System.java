package gis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GIS_System {
	
	HashMap <Integer, State> states = new HashMap <Integer, State>();
	String filename = "data/NationalFile_20161201.txt";
	
	public void dataImport(){
		File file = new File(filename);
		try {
			Scanner reader = new Scanner(file, "UTF-8");
			String line = reader.nextLine();
			String[] info;
			double i = 0;
			while(reader.hasNextLine()){
				line = reader.nextLine();
				info = line.split("\\|");
				if(info.length != 20) continue;
				Location location = new Location(info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[9],info[10],info[15],info[17]);
				int stateNum = Integer.parseInt(info[4]);
				if(states.containsKey(stateNum)){
					states.get(stateNum).addLocation(location);
				}else{
					State newState = new State(stateNum);
					newState.addLocation(location);
					states.put(stateNum, newState);
				}
				i++;
			}
			reader.close();
			System.out.println(i);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");;
		}
	}
	
	public double computeDistance(double latitude1, double longitude1, double latitude2, double longitude2){
		double R = 6371;
		
		double dLat = Math.abs(latitude2 - latitude1);
		double dLon = Math.abs(longitude2 - longitude2);
		double angle = Math.pow(Math.sin(dLat/2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dLon/2), 2);
		double c = Math.atan2(Math.sqrt(angle), Math.sqrt(1-angle));
		return c * R;
	}
	
	public Location nearestArea(double latitude, double longitude, int stateNumber){
		
		State state = states.get(stateNumber);
		ArrayList<Location> locations = state.getLocations();
		Location nearest = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for(Location loc: locations){
			double latitude2 = loc.getLatitude();
			double longitude2 = loc.getLongitude();
			double distance = computeDistance(latitude, longitude, latitude2, longitude2);
			if(distance < minDistance){
				nearest = loc;
				minDistance = distance;
			}
		}
		return nearest;
	}

	public static void main(String[] args) {
		System.out.println("Importing data");
		GIS_System gis = new GIS_System();
		gis.dataImport();
		System.out.println("Please input the latitude");
		Scanner reader = new Scanner(System.in);
		String lat = reader.nextLine();
		double latitude = Double.parseDouble(lat);
		System.out.println("Please input the longitude");
		String lon = reader.nextLine();
		double longitude = Double.parseDouble(lon);
		System.out.println("Please input the state number");
		String sn = reader.nextLine();
		int stateNumber = Integer.parseInt(sn);
		System.out.println("Searching for nearest area...");
		Location location = gis.nearestArea(latitude, longitude, stateNumber);
		reader.close();
		double distance = gis.computeDistance(latitude, longitude, location.getLatitude(), location.getLongitude());
		System.out.println(location.getName());
		System.out.println(location.getCounty());
		System.out.println(location.getState());
		System.out.println("" + distance);
		System.out.println(location.getFeature());
		System.out.println("" + location.getElevation());
	}

}

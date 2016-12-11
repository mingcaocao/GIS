package gis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Map.Entry;

public class GIS_System {

	HashMap <Integer, State> states;
	String[] files = {"data/NationalFile_20161201_1.csv", "data/NationalFile_20161201_2.csv", "data/NationalFile_20161201_3.csv"};

	public GIS_System(){
		this.states = new HashMap <Integer, State>();
	}

	public void dataImport(){
		double i = 0;
		for(String filename: files){
			try {
				File file = new File(filename);
				Scanner reader = new Scanner(file);
				String line = reader.nextLine();
				String[] info;
				while(reader.hasNextLine()){
					line = reader.nextLine();
					info = line.split(",");
					if(info.length != 10) continue;
					Location location = new Location(info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7],info[8],info[9]);
					int stateNum = Integer.parseInt(info[3]);
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
	}

	public double computeDistance(double latitude1, double longitude1, double latitude2, double longitude2){
		double R = 6371;

		double dLat = Math.abs(latitude2 - latitude1);
		double dLon = Math.abs(longitude2 - longitude2);
		double angle = Math.pow(Math.sin(dLat/2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dLon/2), 2);
		double c = Math.atan2(Math.sqrt(angle), Math.sqrt(1-angle));
		return c * R;
	}

	public ArrayList<Location> nearestArea(double latitude, double longitude, int stateNumber, int numOfnearest){

		State state = states.get(stateNumber);
		ArrayList<Location> locations = state.getLocations();
		ArrayList<Location> nearest = new ArrayList<Location>();
		HashMap<Location, Double> distances = new HashMap<Location, Double>();
		for(Location loc: locations){
			double latitude2 = loc.getLatitude();
			double longitude2 = loc.getLongitude();
			double distance = computeDistance(latitude, longitude, latitude2, longitude2);
			distances.put(loc, distance);
		}
		distances = locationSortByDistance(distances);		//sorted this similarity map according to its value in descending order
		int n = numOfnearest;
		for(HashMap.Entry<Location, Double> entry : distances.entrySet()){	//get a hash map of users with highest similarities and their similarities
			if(n <= 0){
				break;
			}
			nearest.add(entry.getKey());
			n--;
		}
		return nearest;
	}

	public static <Location, Double extends Comparable<? super Double>> HashMap<Location, Double> locationSortByDistance( HashMap<Location, Double> map )
	{
		LinkedList<Entry<Location, Double>> list = new LinkedList<HashMap.Entry<Location, Double>>( map.entrySet() );			//Construct a Linked list of given hash map's entries
		Collections.sort( list, new Comparator<HashMap.Entry<Location, Double>>()											//Define the comparator in collection works according to each entry's value, and then uses collection.sort() to sorted the linked list
				{
			public int compare( HashMap.Entry<Location, Double> user1, HashMap.Entry<Location, Double> user2 )
			{
				return (user1.getValue()).compareTo( user2.getValue() );
			}
				} );

		HashMap<Location, Double> result = new LinkedHashMap<Location, Double>();						//Rebuild the hash map as a linked hash map to maintain the order
		for (HashMap.Entry<Location, Double> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;						// return the sorted hash map
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
		System.out.println("Please input the number of nearest location you want to get");
		String ns = reader.nextLine();
		int n = Integer.parseInt(ns);
		System.out.println("Searching for nearest area...");
		ArrayList<Location> locations = gis.nearestArea(latitude, longitude, stateNumber, n);
		reader.close();
		for(Location location: locations){
			double distance = gis.computeDistance(latitude, longitude, location.getLatitude(), location.getLongitude());
			System.out.println(location.getName());
			System.out.println(location.getCounty());
			System.out.println(location.getState());
			System.out.println("" + distance);
			System.out.println(location.getFeature());
			System.out.println("" + location.getElevation());
		}
	}

}

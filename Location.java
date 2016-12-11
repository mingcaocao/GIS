package gis;

public class Location {
	
	private int stateNumber, countyNumeric;
	private String name, feature, state, county, map;
	private double latitude, longitude, elevation;
	
	public Location(String name, String feature, String state, String statenNumber, String county, String countyNumeric, String latitude, String longitude, String elevation, String map){
		this.name = name;
		this.feature = feature;
		this.state = state;
		this.stateNumber = Integer.parseInt(statenNumber);
		this.county = county;
		if(countyNumeric.equals("")){
			this.countyNumeric = -1;
		}else{
			this.countyNumeric = Integer.parseInt(countyNumeric);
		}
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		if(elevation.equals("")){
			this.elevation = -1;
		}else{
			this.elevation = Double.parseDouble(elevation);
		}
		this.map = map;
	}


	public int getStateNumber() {
		return stateNumber;
	}

	public int getCountyNumeric() {
		return countyNumeric;
	}

	public String getName() {
		return name;
	}

	public String getFeature() {
		return feature;
	}

	public String getState() {
		return state;
	}

	public String getCounty() {
		return county;
	}

	public String getMap() {
		return map;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getElevation() {
		return elevation;
	}

}

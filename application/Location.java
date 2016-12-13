package application;

/**
 * This is the location method,
 * containing several geographical features of one location
 * @author Ming Cao
 *
 */
public class Location implements LocationInterface {
	// geographical features
	private int stateNumber, countyNumeric;
	private String name, feature, state, county, map;
	private double latitude, longitude, elevation;
	
	/**
	 * This is the constructor of the method
	 * @param name
	 * @param feature
	 * @param state
	 * @param statenNumber
	 * @param county
	 * @param countyNumeric
	 * @param latitude
	 * @param longitude
	 * @param elevation
	 * @param map
	 */
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

	/**
	 * this is the getter method of stateNumber
	 */
	@Override
	public int getStateNumber() {
		return stateNumber;
	}

	/**
	 * this is the getter method of countyNumeric
	 */
	@Override
	public int getCountyNumeric() {
		return countyNumeric;
	}

	/**
	 * This is the getter method of name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * This is the getter method of feature
	 */
	@Override
	public String getFeature() {
		return feature;
	}

	/**
	 * This is the getter method of state
	 */
	@Override
	public String getState() {
		return state;
	}

	/**
	 * This is the getter method of county
	 */
	@Override
	public String getCounty() {
		return county;
	}

	/**
	 * This is the getter method of map
	 */
	@Override
	public String getMap() {
		return map;
	}

	/**
	 * This is the getter method of latitude
	 */
	@Override
	public double getLatitude() {
		return latitude;
	}

	/**
	 * This is the getter of longitude
	 */
	@Override
	public double getLongitude() {
		return longitude;
	}

	/**
	 * This is the getter method of elevation
	 */
	@Override
	public double getElevation() {
		return elevation;
	}

}


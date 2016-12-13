package application;

/**
 * This is the interface of Location method
 * @author Ming Cao
 *
 */
public interface LocationInterface {

	int getStateNumber();

	int getCountyNumeric();

	String getName();

	String getFeature();

	String getState();

	String getCounty();

	String getMap();

	double getLatitude();

	double getLongitude();

	double getElevation();

}
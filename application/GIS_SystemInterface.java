package application;

import java.util.ArrayList;

/**
 * This is the interface of GIS_System
 * @author Ming Cao
 *
 */
public interface GIS_SystemInterface {

	void dataImport();

	double computeDistance(double latitude1, double longitude1, double latitude2, double longitude2);

	ArrayList<Location> nearestArea(double latitude, double longitude, int stateNumber, int numOfnearest);

	ArrayList<Location> nearestArea(double latitude, double longitude, int numOfnearest);

}
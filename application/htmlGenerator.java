package application;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This is the html generator method
 * Read a template file and generate a new file
 * based on the user input, then change the center of
 * the map, and add the search result into the new file
 * The new html is user, when the user click the search 
 * button on the interface.
 * @author Ming Cao
 *
 */
public class htmlGenerator {
	
	String locations;
	double lat, lon;

	public htmlGenerator(ArrayList<Location> location){
		int i = 1;
		this.locations = "[ ";
		String sep = "";
		this.lat = location.get(0).getLatitude();
		this.lon = location.get(0).getLongitude();
		for(LocationInterface loc: location){
			this.locations += sep;
			this.locations = this.locations + "['" + loc.getName() + "', " + loc.getLatitude() + ", "
					+ loc.getLongitude() + ", " + i +"]";
			sep = ", ";
		}
		this.locations += "]";
	}

	public void writeNewHtml() throws IOException{
		ArrayList<String> newLines = new ArrayList<String>();
		for (String line : Files.readAllLines(Paths.get("googleMapTemplatehtml.html"), StandardCharsets.UTF_8)) {
			if (line.contains("'locations'")) {
				String loc = ""+this.locations;
				newLines.add(line.replace("'locations'", loc));
			}else if(line.contains("center")){
				newLines.add("center: new google.maps.LatLng(" + lat +", " + lon+"),");
			}else {
				newLines.add(line);
			}
		}
		Files.write(Paths.get("bin/application/googlemap.html"), newLines, StandardCharsets.UTF_8);
	}
}

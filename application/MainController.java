package application;






import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * This is the method controlling the buttons on inteface,
 * and make it all together
 * @author Shan Luo
 *
 */
public class MainController implements Initializable{

	// create the instance of other classed
	GIS_SystemInterface gisSystem;
	htmlGenerator generator;
	Converter converter;

	// create the components 
	@FXML
	private Label lbStatus;

	@FXML
	private TextField UserName;

	@FXML
	private TextField txtPassword;


	@FXML
	private TextField latitude = new TextField();

	@FXML
	private TextField longitude= new TextField();

	@FXML
	private TextField placeNumber;

	@FXML
	private TextArea infoWindow;

	@FXML
	private RadioButton latLongButton = new RadioButton();

	@FXML
	private RadioButton locationButton = new RadioButton();

	@FXML
	private TextField locationField;


	@FXML private WebView view = new WebView();
	public WebEngine engine;

	@FXML
	public ComboBox<String> comboBox = new ComboBox<>();

	ObservableList<String> list = FXCollections.observableArrayList("ALL STATES", "AL", "BC", "AZ"
			,"AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
			"KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
			"NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI",
			"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");


	public HashMap<String, Integer> stateNumber = new HashMap<String, Integer>();
	public String info = "Below is the search result:\n";
	double lat;
	double lon;
	@FXML
	public Button search;

	/**
	 * this is the constructor of the class
	 */
	public MainController(){
		// add state and the value
		stateNumber.put("AL", 1); 
		stateNumber.put("BC", 2);
		stateNumber.put("AZ", 4);
		stateNumber.put("AR", 5);
		stateNumber.put("CA", 6);
		stateNumber.put("CO", 8);
		stateNumber.put("CT", 9);
		stateNumber.put("DE", 10);
		stateNumber.put("DC", 11);
		stateNumber.put("FL", 12);
		stateNumber.put("GA", 13);
		stateNumber.put("HI", 15);
		stateNumber.put("ID", 16);
		stateNumber.put("IL", 17);
		stateNumber.put("IN", 18);
		stateNumber.put("IA", 19);
		stateNumber.put("KS", 20);
		stateNumber.put("KY", 21);
		stateNumber.put("LA", 22);
		stateNumber.put("ME", 23);
		stateNumber.put("MD", 24);
		stateNumber.put("MA", 25);
		stateNumber.put("MI", 26);
		stateNumber.put("MN", 27);
		stateNumber.put("MS", 28);
		stateNumber.put("MO", 29);
		stateNumber.put("MT", 30);
		stateNumber.put("NE", 31);
		stateNumber.put("NV", 32);
		stateNumber.put("NH", 33);
		stateNumber.put("NJ", 34);
		stateNumber.put("NM", 35);
		stateNumber.put("NY", 36);
		stateNumber.put("NC", 37);
		stateNumber.put("ND", 38);
		stateNumber.put("OH", 39);
		stateNumber.put("OK", 40);
		stateNumber.put("OR", 41);
		stateNumber.put("PA", 42);
		stateNumber.put("RI", 44);
		stateNumber.put("SC", 45);
		stateNumber.put("SD", 46);
		stateNumber.put("TN", 47);
		stateNumber.put("TX", 48);
		stateNumber.put("UT", 49);
		stateNumber.put("VT", 50);
		stateNumber.put("VA", 51);
		stateNumber.put("WA", 53);
		stateNumber.put("WV", 54);
		stateNumber.put("WI", 55);
		stateNumber.put("WY", 56);
		// create gisSystem, and load data
		gisSystem = new GIS_System();
		gisSystem.dataImport();

	}

	/**
	 * This is the action method of radio button
	 * @param e
	 */
	public void radioSelect(ActionEvent e){
		// when the location button is selected, disable the lat and long input
		if(locationButton.isSelected()){
			longitude.setText(""); // clear the prev text in the field
			latitude.setText("");
			longitude.setDisable(true);
			latitude.setDisable(true);
			locationField.setEditable(true);
			locationField.setDisable(false);
		}
		// when the latLong button is selcted
		if(latLongButton.isSelected()){
			locationField.setText("");
			locationField.setDisable(true);;
			longitude.setEditable(true);
			latitude.setEditable(true);
			longitude.setDisable(false);
			latitude.setDisable(false);
		}
	}

	/**
	 * This is the action method of login button
	 * @param event
	 * @throws Exception
	 */
	public void Login(ActionEvent event) throws Exception{
		// set the username as "user", the password as "123"
		if(UserName.getText().equals("user") && txtPassword.getText().equals("123")){
			//when login success, jump to another stage
			lbStatus.setText("Login Success.");
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Stage stage = new Stage();

			Scene scene = new Scene(root);
			stage.setTitle("GIS");
			stage.setScene(scene); 
			stage.setResizable(false);
			stage.show();
		}
		// else print the wrong message
		else{
			lbStatus.setText("Wrong username or password.");
		}
	}

	/**
	 * This is the initialize method, everytime when enter the state, intilize
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		engine = view.getEngine();
		
		// set up the initial view
		URL urlMap = getClass().getResource("googlemap.html");
		engine.load(urlMap.toExternalForm());
		comboBox.setItems(list);
		locationButton.setSelected(true);
		latitude.setDisable(true);
		longitude.setDisable(true);
		comboBox.getSelectionModel().select("ALL STATES");



	}

	/**
	 * This is the action method of search button
	 * @param e
	 * @throws Exception
	 */
	public void clickSearch (ActionEvent e) throws Exception {
		// if the location button is selected
		// run the search method without the state argument
		if(locationButton.isSelected()){
			String inputLocation = locationField.getText();
			converter = new Converter();
			String[] latLong = new String[2];
			// try to convert the position to lat and long
			try{
				latLong = converter.getLatLongPositions(inputLocation);
			}
			catch(Exception exception){
				infoWindow.setText("No information about the input location.");
			}
			try{
				lat = Double.parseDouble(latLong[0]);
				lon = Double.parseDouble(latLong[1]);
			}
			catch(Exception exception){
				infoWindow.setText("No information about the input location.");
			}
			info = "Below is the search result:\n"
					+ "Name   City    State  Distance  Type  Elevation\n";
			//try to run the search method when the the button is clicked
			try{
				int pNumber = Integer.parseInt(placeNumber.getText());
				String state = comboBox.getSelectionModel().getSelectedItem().toString();
				ArrayList<Location> locations = new ArrayList<Location>();
				if(state.equals("ALL STATES")){
					locations = gisSystem.nearestArea(lat, lon, pNumber);
				}
				else{
					int number = stateNumber.get(state);

					locations = gisSystem.nearestArea(lat, lon, number, pNumber);
				}
				
				// save the information
				for(LocationInterface location: locations){
					double distance = gisSystem.computeDistance(lat, lon, location.getLatitude(), location.getLongitude());
					info = info + location.getName() + ", " + location.getCounty() +  ", " + 
							location.getState() + ", " + Double.parseDouble(String.format("%.3f",distance)) + "km, " + location.getFeature()+ 
							", " + location.getElevation() + "m.\n";
				}
				// print the information
				infoWindow.setText(info);
				// refresh the webview by load a new html file
				generator = new htmlGenerator(locations);
				generator.writeNewHtml();
				engine.reload();
			}
			catch(NumberFormatException exception){
				infoWindow.setText("Invalid input.");
			}


		}

		// else the state number is selected, run the search method with it
		else{
			info = "Below is the search result:\n"
					+ "Name   City    State  Distance  Type  Elevation\n";
			try{
				lat = Double.parseDouble(latitude.getText());
				lon = Double.parseDouble(longitude.getText());
				int pNumber = Integer.parseInt(placeNumber.getText());
				String state = comboBox.getSelectionModel().getSelectedItem().toString();
				ArrayList<Location> locations = new ArrayList<Location>();
				if(state.equals("ALL STATES")){
					locations = gisSystem.nearestArea(lat, lon, pNumber);
				}
				else{
					int number = stateNumber.get(state);

					locations = gisSystem.nearestArea(lat, lon, number, pNumber);
				}
				for(LocationInterface location: locations){
					double distance = gisSystem.computeDistance(lat, lon, location.getLatitude(), location.getLongitude());
					info = info + location.getName() + ", " + location.getCounty() +  ", " + 
							location.getState() + ", " + Double.parseDouble(String.format("%.3f",distance)) + "km, " + location.getFeature()+ 
							", " + location.getElevation() + "m.\n";
				}
				infoWindow.setText(info);
				generator = new htmlGenerator(locations);
				generator.writeNewHtml();
				engine.reload();
			}
			catch(NumberFormatException ex){
				infoWindow.setText("Invalid input.");

			}


		}
	}

}

/**
 * This is the helper class to get the webview
 * @author Shan Luo
 *
 */
class Browser extends Region {
	@FXML
	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();

	public Browser() {
		//apply the styles
		getStyleClass().add("browser");
		// these two lines will give me NULLPOINTER
		URL urlMap = getClass().getResource("googlemap.html");
		webEngine.load(urlMap.toExternalForm());
		//webEngine.load(link);
		//add the web view to the scene
		getChildren().add(browser);

	}
}


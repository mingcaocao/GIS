package application;



import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainController implements Initializable{
	@FXML
	private Label lbStatus;

	@FXML
	private TextField UserName;

	@FXML
	private TextField txtPassword;

	@FXML private WebView view = new WebView();
	private WebEngine engine;
	
	String link;

	public void Login(ActionEvent event) throws Exception{
		if(UserName.getText().equals("user") && txtPassword.getText().equals("123")){
			lbStatus.setText("Login Success.");
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setTitle("GIS");
			stage.setScene(scene);     
			stage.show();
		}
		else{
			lbStatus.setText("Wrong username or password.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		engine = view.getEngine();
		link =
				"https://maps.googleapis.com"
						+ "/maps/api/staticmap?center=40.711614,-74.012318&zoom=14&size=400x400&maptype=roadmap"
						+ "&markers=color:green"
						+ "%7Clabel:G%7C40.711614,-74.012318"
						+ "&key=AIzaSyDG64Ww8rDEvUOa-mFp1D8jbPPx_bkhGA8";
		//URL urlMap = getClass().getResource("google.html");
		//engine.load(urlMap.toExternalForm());
		engine.load(link);

	}
}
class Browser extends Region {
	@FXML
	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();

	public Browser() {
		//apply the styles
		getStyleClass().add("browser");
		// these two lines will give me NULLPOINTER
		URL urlMap = getClass().getResource("google.html");
		webEngine.load(urlMap.toExternalForm());
		//webEngine.load(link);
		//add the web view to the scene
		getChildren().add(browser);

	}
}


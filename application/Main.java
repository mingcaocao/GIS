package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main method of this project
 * @author Shan Luo
 *
 */
public class Main extends Application {
	/**
	 * set up the state
	 */
	@Override public void start(Stage primaryStage) {
		try{
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Login");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		System.out.println("System initializing...");
		launch(args);
	}
}

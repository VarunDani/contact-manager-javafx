package edu.utdallas.db.controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is Main Class that contains main method for launching Java FX Application
 * 
 * 
 * @version 1.0
 * @since v1.0
 * @author Varun Dani
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			//Loading Root Layout File 
			VBox root =  FXMLLoader.load(getClass().getResource("/edu/utdallas/db/layout/ContactManager.fxml")); 
			
			//Making  New FX Scene and Loading Current CSS File 
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//ready Primary Stage File 
			primaryStage.setTitle("Contact Manager - Varun Dani");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

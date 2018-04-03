/**
 * @author Bartosz Grzegorz Jêdrzejczak
 */

package application;


import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/** Main class extends Application, calls LogonDialog constructor
 *  Checks result and shows error / success information
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Optional<Pair<Environment,String>> result =	(new LogonDialog("Logowanie", "Logowanie do systemu STYLEman")).showAndWait();
			
			if (result.isPresent()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Logowanie");
				alert.setHeaderText(null);
				alert.setContentText("Logowanie udane!");

				alert.showAndWait();
			}
			else {		
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Logowanie");
				alert.setHeaderText(null);
				alert.setContentText("B³¹d logowania!");

				alert.showAndWait();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

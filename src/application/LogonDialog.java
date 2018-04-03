package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class LogonDialog extends Dialog<Pair<Environment, String>> {
	private ChoiceBox<Environment> choiceBoxEnv;
	private ComboBox<String> comboBoxUsers = new ComboBox<>();
	private ButtonType logonButtonType = new ButtonType("Logon", ButtonData.OK_DONE);
	private ButtonType anulujButtonType = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);
	private PasswordField passwordFieldPass = new PasswordField();
	
	public LogonDialog(String title, String text) {
		super();
		this.setTitle(title);
		this.setHeaderText(text);
		
		/** Adding image, source in bin/Login_64x.png */
		Image image = new Image(ClassLoader.getSystemResourceAsStream("Login_64x.png"));
		ImageView imageView = new ImageView(image);
		this.setGraphic(imageView);
		
		GridPane gridPane = new GridPane();
		
		/** Adding text "Œrodowisko:" and ChoiceBox with three environments */
		Text textEnv = new Text("Œrodowisko:");
		
		gridPane.add(textEnv, 0, 0);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.getColumnConstraints().add(new ColumnConstraints(80));
		gridPane.getColumnConstraints().add(new ColumnConstraints(200));
		
		Environment produkcyjneEnv = new Environment("Produkcyjne");
		Environment testoweEnv = new Environment("Testowe");
		Environment deweloperskieEnv = new Environment("Deweloperskie");
		
		ObservableList<Environment> environments = FXCollections.observableArrayList(produkcyjneEnv, testoweEnv, deweloperskieEnv);
		
		choiceBoxEnv = new ChoiceBox<>(environments);
		
		choiceBoxEnv.setPrefWidth(200);
		gridPane.add(choiceBoxEnv, 1, 0);
		
		/** Adding text "U¿ytkownicy:" and ComboBox with users */
		Text textUser = new Text("U¿ytkownik:");
		
		gridPane.add(textUser, 0, 1);
		
		produkcyjneEnv.addUserAndPass("adam.nowak", "an");
		produkcyjneEnv.addUserAndPass("ola.kot", "ok");
		produkcyjneEnv.addUserAndPass("ala.malecka", "am");
		testoweEnv.addUsersAndPasses("jan.kowalski", "jk", "piotr.boski", "pb", "albert.muzyk", "am");
		deweloperskieEnv.addUsersAndPasses("anna.malina", "am", "maria.koza", "mk", "henryk.kowal", "hk", "lukasz.wilk", "lw");
		
		comboBoxUsers.setEditable(true);
		comboBoxUsers.setPrefWidth(200);
		
		gridPane.add(comboBoxUsers, 1, 1);
		
		/** Adding text "Has³o:" and PasswordField */		
		Text textPass = new Text("Has³o:");
		gridPane.add(textPass, 0, 2);
		
		passwordFieldPass.setPrefWidth(200);
		gridPane.add(passwordFieldPass, 1, 2);
		
		
		/** Adding "Logon" and "Anuluj" ButtonTypes */
		this.getDialogPane().getButtonTypes().addAll(logonButtonType, anulujButtonType);
		this.getDialogPane().lookupButton(logonButtonType).setDisable(true);
		this.getDialogPane().setContent(gridPane);
		
		choiceBoxEnv.valueProperty().addListener((obs, oldValue, newValue) -> envChanged(oldValue, newValue));
		passwordFieldPass.textProperty().addListener((obs, oldVal, newVal) -> passFieldChanged(newVal));
			
		setResultConverter(button -> resultConverter(button));
	}
	/**
	 * 
	 * @param buttonType variable of type ButtonType, which type is default returned
	 * @return Pair of Environment and String, created from values chosen in ChoiceBox and ComboBox or null, when button is not clicked
	 */
	private Pair<Environment, String> resultConverter(ButtonType buttonType) {
		if (buttonType == logonButtonType) {
			if (choiceBoxEnv.valueProperty().get().checkPass(comboBoxUsers.getValue().toString() , passwordFieldPass.getText()) == true) {
				return new Pair<Environment, String>(choiceBoxEnv.getValue(), comboBoxUsers.getValue().toString());
			}
		}
		return null;
	}
	/**
	 * 
	 * @param newVal value chosen in password field
	 * @return null
	 */
	private Object passFieldChanged(String newVal) {
		if(choiceBoxEnv.getValue() != null && comboBoxUsers.getValue() != null) {
			getDialogPane().lookupButton(logonButtonType).setDisable(false);
		}
		return null;
	}
	/**
	 * 
	 * @param oldValue old value in ChoiceBox
	 * @param newValue new value chosen in ChoiceBox
	 * @return null
	 */
	private Object envChanged(Environment oldValue, Environment newValue) {
		comboBoxUsers.getItems().clear();
		comboBoxUsers.getItems().addAll(newValue.getListOfUsers());
		comboBoxUsers.getSelectionModel().selectFirst();
		comboBoxUsers.setVisibleRowCount(3);
		return null;
	}
}

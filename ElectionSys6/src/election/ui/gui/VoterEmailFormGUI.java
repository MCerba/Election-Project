package election.ui.gui;

import election.business.DawsonElectionFactory;
import election.business.DawsonElectionOffice;
import election.business.DawsonVoter;
import election.business.ElectionType;
import election.business.interfaces.*;
import election.data.ElectionListDB;
import election.data.InexistentElectionException;
import election.data.InexistentVoterException;
import election.data.ObjectSerializedList;
import election.data.VoterListDB;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.VoterDAO;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/**
 * Form that gets the voter email, finds the Voter in the model If the voter is
 * eligible for the election, instantiate and start a SingleBallotFormGUI
 */
public class VoterEmailFormGUI {

	private ElectionOffice model;
	private Election election;
	private Stage primaryStage;
	private Text actionTarget;
	private TextField emailTextField;

	/**
	 * Constructor validates that the parameters are not null and the election has
	 * ElectionType.SINGLE. Invokes the initialize() method
	 * 
	 * @throws IllegalArgumentException
	 *             if the conditions are not met.
	 */
	public VoterEmailFormGUI(ElectionOffice model, Election election) {

		if (election == null || election.getElectionType().toString() == "single") {
			throw new IllegalArgumentException("Invalid Election");
		}
		this.model = model;
		this.election = election;
		initialize();
	}

	/**
	 * The stage and the scene are created in the start.
	 *
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		// Set Window's Title
		primaryStage.setTitle("Get Voter Email");
		GridPane root = createUserInterface();
		Scene scene = new Scene(root, 500, 275);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Create the user interface as the root
	 *
	 * @return GridPane with the UI
	 */
	private GridPane createUserInterface() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(30, 30, 30, 30));
		
		Text scenetitle = new Text(
				"Welcome to the Election System 2017");
		scenetitle.setFont(Font.font(
				"Times New Roman", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 1, 1);
		
		Label email = new Label("Enter email address:");
		grid.add(email, 0, 2);
		grid.add(emailTextField, 0, 3);
		
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		btn.setDefaultButton(true);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 3);
		actionTarget.setId("actionTarget");
		grid.add(actionTarget, 0, 6, 2, 1);
		btn.setOnAction(this::signInButtonHandler);
		return grid;
	}

	/**
	 * Event handler for the Sign In Button
	 *
	 * @param e
	 */
	private void signInButtonHandler(ActionEvent e) {

		try
		{
			DawsonVoter v;
			v = (DawsonVoter) model.findVoter(emailTextField.getText().trim());
			if (v.isEligible(election)) {
				
				startvoting(v);
				
			} 
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(
						"Voter " + v.getName() + " is not eligible to vote in this election");
				alert.setContentText(
						"Make sure that you are eligible to vote by looking at the postal range");
				alert.showAndWait();
			}
			
		  } catch (InexistentVoterException ive) 
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(
						"Email address: " + emailTextField.getText().trim() + " is invalid!");
				alert.setContentText(
						"To register to vote, go back to the main menu.");
				alert.showAndWait();
			}

	}
	/**Starts the opening of SingleBallot Form GUI
	 * 
	 * @param v Voter
	 * 
	 */
	public void startvoting(Voter v) {
		Ballot b = model.getBallot(v, election);
		SingleBallotFormGUI sbf = new SingleBallotFormGUI(model, election, v, b, this);
		sbf.start(primaryStage);
	}

	/**
	 * This method is usually used for data binding of a "data bean" class to the
	 * JavaFX controls. A "bean" class is a simple class with getters and setters
	 * for all properties. Changes to a control are immediately set on the bean and
	 * a change to the bean is immediately shown in the control.
	 */
	private void initialize() {
		actionTarget = new Text();
		emailTextField = new TextField(" ");
	}
}

package election.ui.gui;


import election.business.interfaces.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {

  private static ElectionOffice model;
  private static Election election;
  private VoterEmailFormGUI gui;

  @Override
  public void start(Stage primaryStage) {
    gui.start(primaryStage);
  }

  public static void main(String[] args) {
	
	  launch(args);
  }

  public static void initContext(ElectionOffice mod, Election e) {
    model = mod;
    election = e;
  }

  public void init() {
    gui = new VoterEmailFormGUI(model, election);
  }
}

/**
 * Assignment Name:   MilkWeight
 * Filename:          Main.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class that starts the application
 * 
 * @author ATEAM050
 */
public class Main extends Application {

  // Global Variables
  private static final int WINDOW_WIDTH = 1280;
  private static final int WINDOW_HEIGHT = 720;
  private static final String APP_TITLE = "MilkWeight";
  private static Stage STAGE;
  public static ArrayList<Farm> farms = new ArrayList<Farm>();
  
  /**
   * Starts the application GUI and manages application data
   */
  @Override
  public void start(Stage stage) throws Exception {
    // Set the stage
    STAGE = stage;

    // Start the application with the import scene
    Main.setStage("Import");
  }

  /**
   * Changes the stage between either report or import
   * 
   * @param scene the scene that is to be set
   */
  public static void setStage(String scene) {
    // If the scene is report, set to report
    if (scene.equals("Report"))
      ReportScene.getStage(STAGE, WINDOW_WIDTH, WINDOW_HEIGHT, APP_TITLE);
    // If the scene is import, set to import
    else if (scene.equals("Import"))
      ImportScene.getStage(STAGE, WINDOW_WIDTH, WINDOW_HEIGHT, APP_TITLE);
  }

  /**
   * Launches the application
   * 
   * @param args user supplied arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}

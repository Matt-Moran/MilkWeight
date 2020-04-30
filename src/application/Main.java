/**
 * Assignment Name:   MilkWeight
 * Filename:          Main.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import javafx.application.Application;
import javafx.scene.Scene;
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
  private static final String APP_TITLE = "Milk Weight";

  // Scene Variables
  private static Stage Stage;
  private static ImportScene importSceneManager;
  private static ReportScene reportSceneManager;
  private static Scene importScene;
  private static Scene reportScene;

  /**
   * Starts the application GUI and manages application data
   */
  @Override
  public void start(Stage stage) throws Exception {
    // Set the stage
    Stage = stage;

    // Create the ReportScene
    reportSceneManager = new ReportScene(stage);

    // Create the ImportScene
    importSceneManager = new ImportScene(stage, reportSceneManager);

    // Create the Report and Import Scenes
    importScene = new Scene(importSceneManager.getScene(), WINDOW_WIDTH, WINDOW_HEIGHT);
    reportScene = new Scene(reportSceneManager.getScene(), WINDOW_WIDTH, WINDOW_HEIGHT);

    // Start the application with the import scene
    Main.setStage("Import");
  }

  /**
   * Changes the stage between either report or import
   * 
   * @param name the scene that is to be set
   */
  public static void setStage(String name) {
    // If the scene is report, set to report
    if (name.equals("Report"))
      Stage.setScene(reportScene);
    // If the scene not report, set to import
    else
      Stage.setScene(importScene);
    Stage.setTitle(APP_TITLE);
    Stage.show();
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

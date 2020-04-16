package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The scene that occurs when a report is created from the import scene
 * 
 * @author ATEAM050
 */
public class ReportScene {

  // Main Pane containing the scene's content
  private static BorderPane root;

  /**
   * Generates the elements for the report scene
   */
  private static void createScene() {

    /**
     * START WRITING CODE HERE: DELETE EXAMPLE CODE BELOW
     */

    // Create an BorderPane as the root of the scene
    root = new BorderPane();

    // Add a label to the root
    root.setTop(new Label("Report Scene"));

    // Add a button to the root
    Button b = new Button("Go to Import Scene");
    root.setCenter(b);
    b.setOnAction(e -> {
      Main.setStage("Import");
    });

    /**
     * STOP WRITING CODE HERE
     */
  }

  /**
   * Sets the stage to the report scene
   */
  public static void getStage(Stage stage, int width, int height, String title) {
    createScene();
    stage.setTitle(title);
    stage.setScene(new Scene(root, width, height));
    stage.show();
  }
}

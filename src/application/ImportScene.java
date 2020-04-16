package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The scene used for importing data and confirming data before reports are
 * generated
 * 
 * @author ATEAM050
 */
public class ImportScene {

  // Main Pane containing the scene's content
  private static BorderPane root;

  /**
   * Generates the elements for the import scene
   */
  private static void createScene() {

    /**
     * START WRITING CODE HERE: DELETE EXAMPLE CODE BELOW
     */

    // Create an BorderPane as the root of the scene
    root = new BorderPane();

    // Add a label to the root
    root.setTop(new Label("Import Scene"));

    // Add a button to the root
    Button b = new Button("Go to Report Scene");
    root.setCenter(b);
    b.setOnAction(e -> {
      Main.setStage("Report");
    });

    /**
     * STOP WRITING CODE HERE
     */
  }

  /**
   * Sets the stage to the import scene
   */
  public static void getStage(Stage stage, int width, int height, String title) {
    createScene();
    stage.setTitle(title);
    stage.setScene(new Scene(root, width, height));
    stage.show();
  }
}

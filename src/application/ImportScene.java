package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    Label topLabel = new Label("Import Scene");
    BorderPane.setAlignment(topLabel, Pos.TOP_CENTER);
    root.setTop(topLabel);
    // Add a button to the root
    
    Button b1 = new Button("Generate Report");
    BorderPane.setAlignment(b1, Pos.BOTTOM_RIGHT); // sets position of button
    										      // to go to report scene
    Button b2 = new Button("Add");
    Button b3 = new Button("Import CSV");
    BorderPane.setAlignment(b2, Pos.BOTTOM_RIGHT);
    BorderPane.setAlignment(b3, Pos.BOTTOM_RIGHT);

    
    
    
    VBox vbox = new VBox();
    vbox.setSpacing(20);
    root.setRight(vbox); // creates a new Vbox

    // adds labels and text boxes so user can enter in values for
    // the farm, date, and weight 
    TextField farmField = new TextField();
    Label farm = new Label("Farm");
    TextField dateField = new TextField();
    Label date = new Label("Date");
    TextField weightField = new TextField();
    Label weight = new Label("Weight"); 
    
    // adds labels and text boxes to the vbox
    vbox.getChildren().addAll(farm, farmField, date, dateField, weight, weightField, b2, b3, b1);
    root.setRight(vbox); // sets the vbox to the right pane
    
    
    // STILL NEEDS
    // 1. Add table object to center of border pane
    // 2. Add Hbox to top pane with "Farm, Date, Weight"
    // 3. Add "report type" drop down 

 
    // sets the action of button b to take user from ImportScene 
    // to ReportScene
    b1.setOnAction(e -> {
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

/**
 * Assignment Name:   MilkWeight
 * Filename:          ReportScene.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.chart.*;

/**
 * The scene that occurs when a report is created from the import scene
 * 
 * @author ATEAM050
 */
public class ReportScene {

  // Main Pane containing the scene's content
  private static BorderPane root;

  // Pie Chart
  private static PieChart pieChart;

  // Report Details
  private static Text resultText;

  /**
   * Generates the elements for the report scene
   */
  private static void createScene() {

    /*
     * Initialization (Creating the Scene Base)
     */

    // Create an BorderPane as the root of the scene
    root = new BorderPane();

    /*
     * BorderPane Left (Displaying Report Text and New Report)
     */

    // Create an VBox to store the Text and Return Button
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.setAlignment(Pos.CENTER);

    // Create new Report Button
    Button newReportButton = new Button("Create New Report");
    newReportButton.setOnAction(e -> {
      Main.setStage("Import");
    });

    // Display A Text Box with Report Data
    resultText = new Text();

    // Add the elements to the VBox
    vbox.getChildren().setAll(resultText, newReportButton);

    // Set the VBox to the Left
    root.setLeft(vbox);

    /*
     * BorderPane Center (Pie Graph)
     */

    // Create the Pie Chart
    pieChart = new PieChart();

    // Display Pie Chart in Center
    root.setCenter(pieChart);
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

  public static void setReport(Report report) {
    // Set the title of the Pie Chart as the Report Type
    pieChart.setTitle(report.getType() + " Report");
    
    // Add slices to the pie chart
    for (Pair<String, Integer> pair : report.getPieGraph()) {
      PieChart.Data slice = new PieChart.Data(pair.getKey(), pair.getValue());
      pieChart.getData().add(slice);
    }
    
    // Set the text for the report details
    String result = new String();
    for(Entry<String, String> entry : report.getReport().entrySet())
      result = result.concat(entry.getKey() + ": " + entry.getValue() + "\n");
    resultText.setText(result);
  }
}

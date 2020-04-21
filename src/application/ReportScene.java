/**
 * Assignment Name:   MilkWeight
 * Filename:          ReportScene.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.*;

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
    Text resultText = new Text();

    // START OF TEMPORARY EXAMPLE DATA CODE
    resultText.setText("Farm ID: farm01\n" + "Year: 2019\n\n" + "Monthly Milk Weights:\n" + "Month 1: 100\n"
        + "Month 2: 200\n" + "Month 3: 300\n" + "Month 4: 400\n" + "Month 5: 500\n" + "Month 6: 600\n"
        + "Month 7: 700\n" + "Month 8: 800\n" + "Month 9: 900\n" + "Month 10: 1,000\n" + "Month 11: 1,100\n"
        + "Month 12: 1,200\n\n" + "Total Weight: 7,800");
    // END OF TEMPORARY EXAMPLE DATA CODE

    // Add the elements to the VBox
    vbox.getChildren().setAll(resultText, newReportButton);

    // Set the VBox to the Left
    root.setLeft(vbox);

    /*
     * BorderPane Center (Pie Graph)
     */

    // Create the Pie Chart
    PieChart pieChart = new PieChart();

    // Declare the Type of Report
    String reportType;

    // START OF TEMPORARY EXAMPLE DATA CODE
    reportType = "Farm";
    for (int i = 1; i <= 12; i++) {
      PieChart.Data slice = new PieChart.Data("Month " + i, i * 100);

      pieChart.getData().add(slice);
    }
    // END OF TEMPORARY EXAMPLE DATA CODE

    // Set the title of the Pie Chart as the Report Type
    pieChart.setTitle(reportType + " Report");

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
}

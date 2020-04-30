/*
 * Assignment Name:   MilkWeight
 * Filename:          ReportScene.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;

/**
 * The scene that occurs when a report is created from the import scene
 * 
 * @author ATEAM050
 */
public class ReportScene {

  /**
   * Pair Table Row Inner-Class
   * 
   * @author ATEAM050
   *
   */
  public class PairRow {
    // Variables
    public StringProperty key;
    public StringProperty value;

    /**
     * Create the row entry
     * 
     * @param key   the key
     * @param value the value
     */
    public PairRow(String key, String value) {
      setKey(key);
      setValue(value);
    }

    /**
     * Set the key
     * 
     * @param value the key
     */
    public void setKey(String value) {
      keyProperty().set(value);
    }

    /**
     * Set the value
     * 
     * @param value the value
     */
    public void setValue(String value) {
      valueProperty().set(value);
    }

    /**
     * Returns the key simple string
     * 
     * @return the key simple string
     */
    public StringProperty keyProperty() {
      if (key == null)
        key = new SimpleStringProperty(this, "key");
      return key;
    }

    /**
     * Returns the value simple stirng
     * 
     * @return the value simple string
     */
    public StringProperty valueProperty() {
      if (value == null)
        value = new SimpleStringProperty(this, "value");
      return value;
    }
  }

  // Variables

  // Array list of Row objects
  private ObservableList<PairRow> pairRows;

  // Main Pane containing the scene's content
  private BorderPane root;

  // Pie Chart
  private PieChart pieChart;

  // Create the TableView for each column
  private TableView<PairRow> dataTable;

  // Export String
  private String export;

  /**
   * Creates the elements in the report scene
   * 
   * @param stage the main stage
   */
  public ReportScene(Stage stage) {

    /*
     * Initialization (Creating the Scene Base)
     */

    // Create an BorderPane as the root of the scene
    root = new BorderPane();

    // Declare Pie Chart
    pieChart = new PieChart();

    // Create Table View
    dataTable = new TableView<PairRow>();

    // Create Observable List
    pairRows = FXCollections.observableArrayList();

    // Create Export String
    export = new String();

    /*
     * BorderPane Center (Report Table)
     */

    // Create the three columns for each value
    TableColumn<PairRow, String> farmColumn = new TableColumn<>("Key");
    farmColumn.setCellValueFactory(x -> x.getValue().keyProperty());
    TableColumn<PairRow, String> dateColumn = new TableColumn<>("Value");
    dateColumn.setCellValueFactory(x -> x.getValue().valueProperty());

    // add each column to the table
    dataTable.getColumns().add(farmColumn);
    dataTable.getColumns().add(dateColumn);

    // Set each columns properties and size
    for (TableColumn<PairRow, ?> c : dataTable.getColumns()) {
      c.setReorderable(false);
      c.setResizable(false);
      c.prefWidthProperty().bind(dataTable.widthProperty().divide(2));
      c.setSortable(false);
    }

    // Set the VBox to the Center
    root.setCenter(dataTable);

    // Set the data for the table
    dataTable.setItems(pairRows);

    /*
     * BorderPane Bottom (Buttons)
     * 
     */

    // Create new Report Button
    Button newReportButton = new Button("Create New Report");

    // Create Export Report Button
    Button exportReportButton = new Button("Export Report");

    // Create an VBox to store the Text and Return Button
    HBox hbox = new HBox();
    hbox.setSpacing(20);
    hbox.setAlignment(Pos.CENTER);

    // Add the elements to the HBox
    hbox.getChildren().setAll(newReportButton, exportReportButton);

    // Display Buttons in Top
    root.setBottom(hbox);

    /*
     * BorderPane Right (Pie Graph)
     */

    // Display Pie Chart in Right
    root.setRight(pieChart);

    /*
     * Events for Buttons
     */

    // New Report button
    newReportButton.setOnAction(e -> {
      Main.setStage("Import");
    });

    // Export Report button
    exportReportButton.setOnAction(e -> {
      // Create a file chooser pop-up
      FileChooser fileChooser = new FileChooser();

      // Set extension filter to txt only
      FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
      fileChooser.getExtensionFilters().add(extFilter);

      // Show save file dialog built in system
      File file = fileChooser.showSaveDialog(stage);

      if (file != null) {
        try {
          FileWriter fileWriter = new FileWriter(file);
          fileWriter.write(export);
          fileWriter.close();
        } catch (IOException ex) {
          errorPrompt(stage, "Export", "Failed to export file to system.");
        }
      }
    });

  }

  /**
   * Create a error message prompt
   * 
   * @param stage   the main stage
   * @param title   the title of the prompt
   * @param message the error message for the prompt
   */
  private void errorPrompt(Stage stage, String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title + " Error");
    alert.setHeaderText(title + " Error");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Sets the stage to the report scene
   * 
   * @return
   */
  public BorderPane getScene() {
    return root;
  }

  /**
   * Sets the elements in the scene to reflect the current report's content
   * 
   * @param report
   */
  public void setReport(Report report) {
    // Reset the table and pie chart
    pairRows.clear();
    pieChart.getData().clear();

    // Set the title of the Pie Chart as the Report Type
    pieChart.setTitle(report.getType() + " Report");

    // Add slices to the pie chart
    for (Pair<String, Integer> pair : report.getPieGraph()) {
      Data slice = new Data(pair.getKey(), pair.getValue());
      pieChart.getData().add(slice);
    }

    // If there is more than 30 slices, remove the pie chart
    if (pieChart.getData().size() > 30 || pieChart.getData().isEmpty())
      root.setRight(null);
    else
      root.setRight(pieChart);

    // Set the text for the report details table (application)
    for (Pair<String, String> pair : report.getReport())
      pairRows.add(new PairRow(pair.getKey(), pair.getValue()));

    // Set the text for the report to the export string (export)
    export = report.getType() + " Report\n\n";

    for (Pair<String, String> pair : report.getReport())
      export = export.concat(pair.getKey() + ": " + pair.getValue() + "\n");
  }
}

/**
 * Assignment Name:   MilkWeight
 * Filename:          ReportScene.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;

/**
 * The scene that occurs when a report is created from the import scene
 * 
 * @author ATEAM050
 */
public class ReportScene {

//Pair Table Row Inner-Class
  public class PairRow {
    public StringProperty key;
    public StringProperty value;

    public PairRow(String key, String value) {
      setKey(key);
      setValue(value);
    }

    public void setKey(String value) {
      keyProperty().set(value);
    }

    public void setValue(String value) {
      valueProperty().set(value);
    }

    public StringProperty keyProperty() {
      if (key == null)
        key = new SimpleStringProperty(this, "key");
      return key;
    }

    public StringProperty valueProperty() {
      if (value == null)
        value = new SimpleStringProperty(this, "value");
      return value;
    }
  }

  // Array list of Row objects
  private ObservableList<PairRow> pairRows;

  // Main Pane containing the scene's content
  private BorderPane root;

  // Pie Chart
  private PieChart pieChart;

  // Create the TableView for each column
  private TableView<PairRow> dataTable;

  public ReportScene() {
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

    // Create new Report Button
    Button newReportButton = new Button("Create New Report");
    newReportButton.setOnAction(e -> {
      Main.setStage("Import");
    });

    // Create Export Report Button
    Button exportReportButton = new Button("Export Report");

    // Set the VBox to the Center
    root.setCenter(dataTable);

    // Set the data for the table
    dataTable.setItems(pairRows);

    /*
     * BorderPane Top (Buttons)
     */

    // Create an VBox to store the Text and Return Button
    HBox hbox = new HBox();
    hbox.setSpacing(10);
    hbox.setAlignment(Pos.CENTER_LEFT);

    // Add the elements to the HBox
    hbox.getChildren().setAll(newReportButton, exportReportButton);

    // Display Buttons in Top
    root.setTop(hbox);

    /*
     * BorderPane Right (Pie Graph)
     */

    // Display Pie Chart in Right
    root.setRight(pieChart);
  }

  /**
   * Sets the stage to the report scene
   * 
   * @return
   */
  public BorderPane getScene() {
    return root;
  }

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
    if (pieChart.getData().size() > 30)
      root.setRight(null);
    else
      root.setRight(pieChart);

    // Set the text for the report details table
    for (Pair<String, String> pair : report.getReport())
      pairRows.add(new PairRow(pair.getKey(), pair.getValue()));
  }
}

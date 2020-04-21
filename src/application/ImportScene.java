/**
 * Assignment Name:   MilkWeight
 * Filename:          ImportScene.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The scene used for importing data and confirming data before reports are
 * generated
 * 
 * @author ATEAM050
 */
public class ImportScene {

  // START OF TEMPORARY EXAMPLE DATA CODE
  private static class Farm {
    private StringProperty farm;

    public void setFarm(String value) {
      farmProperty().set(value);
    }

    @SuppressWarnings("unused")
    public String getFarm() {
      return farmProperty().get();
    }

    public StringProperty farmProperty() {
      if (farm == null)
        farm = new SimpleStringProperty(this, "farm");
      return farm;
    }

    private StringProperty date;

    public void setDate(String value) {
      dateProperty().set(value);
    }

    @SuppressWarnings("unused")
    public String getDate() {
      return dateProperty().get();
    }

    public StringProperty dateProperty() {
      if (date == null)
        date = new SimpleStringProperty(this, "date");
      return date;
    }

    private StringProperty weight;

    public void setWeight(String value) {
      weightProperty().set(value);
    }

    @SuppressWarnings("unused")
    public String getWeight() {
      return weightProperty().get();
    }

    public StringProperty weightProperty() {
      if (weight == null)
        weight = new SimpleStringProperty(this, "weight");
      return weight;
    }

    public Farm(String f, String d, String w) {
      setFarm(f);
      setDate(d);
      setWeight(w);
    }
  }

  private final static ObservableList<Farm> data = FXCollections.observableArrayList(
      new Farm("farm01", "2/15/2019", "1,258"), new Farm("farm01", "1/26/2019", "342"),
      new Farm("farm02", "2/5/2019", "926"), new Farm("farm03", "1/2/2019", "2,472"),
      new Farm("farm04", "1/19/2019", "630"));
  // END OF TEMPORARY EXAMPLE DATA CODE

  // Main Pane containing the scene's content
  private static BorderPane root;

  /**
   * Generates the elements for the import scene
   */
  private static void createScene() {

    /*
     * Initialization (Creating the Scene Base)
     */

    // Create an BorderPane as the root of the scene
    root = new BorderPane();

    /*
     * BorderPane Right (Manual Input, CSV Import and Report Generation)
     */

    // Create Buttons
    Button generateButton = new Button("Generate Report");
    Button addButton = new Button("Add");
    Button importButton = new Button("Import CSV");

    // Create the ComboBox for selecting the report type
    ComboBox<Object> comboBox = new ComboBox<Object>();
    comboBox.getItems().add("Farm");
    comboBox.getItems().add("Annual");
    comboBox.getItems().add("Monthly");
    comboBox.getItems().add("Date Range");
    comboBox.setPromptText("Report Type");

    // Create labels and text fields for manual data input
    TextField farmField = new TextField();
    Label farm = new Label("Farm");
    TextField dateField = new TextField();
    Label date = new Label("Date");
    TextField weightField = new TextField();
    Label weight = new Label("Weight");

    // Create Separators
    Separator importSeparator = new Separator();
    Separator generateSeparator = new Separator();

    // Create a VBox and set it to the right of the BorderPane
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.setAlignment(Pos.CENTER);
    root.setRight(vbox);

    // Add Labels, Buttons, ComboBox and TextFields to the right VBox
    vbox.getChildren().addAll(farm, farmField, date, dateField, weight, weightField, addButton, importSeparator,
        importButton, generateSeparator, comboBox, generateButton);

    /*
     * BorderPane Center (Data Table)
     */

    // Create the TableView for each column
    TableView<Farm> dataTable = new TableView<>();

    // Create the three columns for each value
    TableColumn<Farm, String> farmColumn = new TableColumn<>("Farm ID");
    farmColumn.setCellValueFactory(x -> x.getValue().farmProperty());
    TableColumn<Farm, String> dateColumn = new TableColumn<>("Date");
    dateColumn.setCellValueFactory(x -> x.getValue().dateProperty());
    TableColumn<Farm, String> weightColumn = new TableColumn<>("Weight");
    weightColumn.setCellValueFactory(x -> x.getValue().weightProperty());

    // add each column to the table
    dataTable.getColumns().add(farmColumn);
    dataTable.getColumns().add(dateColumn);
    dataTable.getColumns().add(weightColumn);

    // Set each columns properties and size
    for (TableColumn<Farm, ?> c : dataTable.getColumns()) {
      c.setReorderable(false);
      c.setResizable(false);
      c.prefWidthProperty().bind(dataTable.widthProperty().divide(3));
    }

    // Add the TableView to the center of the BorderPane
    root.setCenter(dataTable);

    // START OF TEMPORARY EXAMPLE DATA CODE
    dataTable.setItems(data);
    // END OF TEMPORARY EXAMPLE DATA CODE

    /*
     * Events (Not used until A3)
     */

    // Makes the generate report button temporarily change the scene to the
    // ReportScene
    generateButton.setOnAction(e -> {
      Main.setStage("Report");
    });
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

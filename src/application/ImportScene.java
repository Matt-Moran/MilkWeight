/**
 * Assignment Name:   MilkWeight
 * Filename:          ImportScene.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * The scene used for importing data and confirming data before reports are
 * generated
 * 
 * @author ATEAM050
 */
public class ImportScene {

  // Farm Table Row Inner-Class
  public class FarmRow {
    public StringProperty farm;
    public StringProperty date;
    public StringProperty weight;

    public FarmRow(String farm, LocalDate date, int weight) {
      setFarm(farm);
      setDate(date.toString());
      setWeight(String.valueOf(weight));
    }

    public void setFarm(String value) {
      farmProperty().set(value);
    }

    public void setDate(String value) {
      dateProperty().set(value);
    }

    public void setWeight(String value) {
      weightProperty().set(value);
    }

    public StringProperty farmProperty() {
      if (farm == null)
        farm = new SimpleStringProperty(this, "farm");
      return farm;
    }

    public StringProperty dateProperty() {
      if (date == null)
        date = new SimpleStringProperty(this, "date");
      return date;
    }

    public StringProperty weightProperty() {
      if (weight == null)
        weight = new SimpleStringProperty(this, "weight");
      return weight;
    }
  }

  // Variables

  // Array list of Farm objects
  private ArrayList<Farm> farms;
  // Array list of Row objects
  private ObservableList<FarmRow> farmRows;
  // Main Pane containing the scene's content
  private BorderPane root;
  // Import object for running imports
  Import imp;
// Report Scene for sending report results
  ReportScene reportScene;

  public ImportScene(Stage stage, ReportScene reportScene) {
    /*
     * Initialization (Creating the Scene Base)
     */

    // Create the Farms array list
    farms = new ArrayList<Farm>();

    // Create the Row array list
    farmRows = FXCollections.observableArrayList();

    // Create an BorderPane as the root of the scene
    root = new BorderPane();

    // Create an Import object
    imp = new Import(farms);

    // Create the Report Scene
    this.reportScene = reportScene;

    /*
     * BorderPane Right (Manual Input, CSV Import and Report Generation)
     */

    // Create Buttons
    Button generateButton = new Button("Generate Report");
    Button addButton = new Button("Add");
    Button removeButton = new Button("Remove");
    Button importButton = new Button("Import CSV");

    // Create the ComboBox for selecting the report type
    ComboBox<Object> comboBox = new ComboBox<Object>();
    comboBox.getItems().add("Farm");
    comboBox.getItems().add("Annual");
    comboBox.getItems().add("Monthly");
    comboBox.getItems().add("Date Range");
    comboBox.setPromptText("Report Type");

    // Create labels and text fields for manual data input
    TextField addFarmField = new TextField();
    Label addFarm = new Label("Farm");
    DatePicker addDateField = new DatePicker();
    Label addDate = new Label("Date");
    TextField addWeightField = new TextField();
    Label addWeight = new Label("Weight");
    TextField removeFarmField = new TextField();
    Label removeFarm = new Label("Farm");
    DatePicker removeDateField = new DatePicker();
    Label removeDate = new Label("Date");

    // Create File Chooser
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select CSV File");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));

    // Create Separators
    Separator removeSeparator = new Separator();
    Separator importSeparator = new Separator();
    Separator generateSeparator = new Separator();

    // Create a VBox and set it to the right of the BorderPane
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.setAlignment(Pos.CENTER);
    root.setRight(vbox);

    // Add Labels, Buttons, ComboBox and TextFields to the right VBox
    vbox.getChildren().addAll(addFarm, addFarmField, addDate, addDateField, addWeight, addWeightField, addButton,
        removeSeparator, removeFarm, removeFarmField, removeDate, removeDateField, removeButton, importSeparator,
        importButton, generateSeparator, comboBox, generateButton);

    /*
     * BorderPane Center (Data Table)
     */

    // Create the TableView for each column
    TableView<FarmRow> dataTable = new TableView<>();

    // Create the three columns for each value
    TableColumn<FarmRow, String> farmColumn = new TableColumn<>("Farm ID");
    farmColumn.setCellValueFactory(x -> x.getValue().farmProperty());
    TableColumn<FarmRow, String> dateColumn = new TableColumn<>("Date");
    dateColumn.setCellValueFactory(x -> x.getValue().dateProperty());
    TableColumn<FarmRow, String> weightColumn = new TableColumn<>("Weight");
    weightColumn.setCellValueFactory(x -> x.getValue().weightProperty());

    // add each column to the table
    dataTable.getColumns().add(farmColumn);
    dataTable.getColumns().add(dateColumn);
    dataTable.getColumns().add(weightColumn);

    // Set each columns properties and size
    for (TableColumn<FarmRow, ?> c : dataTable.getColumns()) {
      c.setReorderable(false);
      c.setResizable(false);
      c.prefWidthProperty().bind(dataTable.widthProperty().divide(3));
    }

    // Add the TableView to the center of the BorderPane
    root.setCenter(dataTable);

    // Set the data of the TableView to the Rows array list
    dataTable.setItems(farmRows);

    /*
     * Events
     */

    // Add Button
    addButton.setOnAction(e -> {
      try {
        // Get the id, date and weight
        String id = addFarmField.getText().replaceAll("\\s", "");
        LocalDate date = addDateField.getValue();
        int weight = Integer.parseInt(addWeightField.getText());
        // If all three parameters are valid, call the add function
        if (!id.isEmpty() && weight > 0 && date != null)
          add(id, date, weight);
      } catch (Exception error) {
      }
      // If there was an exception or if the data was added, clear all three fields
      addFarmField.clear();
      addWeightField.clear();
      addDateField.setValue(null);
      // After a value is added, update the table
      updateTable();
    });

    // Remove Button
    removeButton.setOnAction(e -> {
      try {
        // Get the id and date
        String id = removeFarmField.getText().replaceAll("\\s", "");
        LocalDate date = removeDateField.getValue();
        // If all two parameters are valid, call the remove function
        if (!id.isEmpty() && date != null)
          remove(id, date);
      } catch (Exception error) {
      }
      // If there was an exception or if the data was remove, clear all three fields
      removeFarmField.clear();
      removeDateField.setValue(null);
      // After a value is removed, update the table
      updateTable();
    });

    // Import CSV Button
    importButton.setOnAction(e -> {
      // Ask the user to select a file, than import the csv file data
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) {
        try {
          imp.Parse(file);
        } catch (MissingDataException | DataFormatException | IOException error) {
          errorPrompt(stage, "CSV Import", error.getMessage());
        }
      }
      // After the import is done, update the table
      updateTable();
    });

    // Generate Report Button
    generateButton.setOnAction(e -> {
      Report report;
      if (!comboBox.getSelectionModel().isEmpty()) {
        if (comboBox.getValue().equals("Farm")) {
          String id = textPrompt("Farm ID");
          Integer year;
          try {
            year = Integer.parseInt(textPrompt("Year"));
          } catch (NumberFormatException error) {
            year = null;
          }
          if (id != null && year != null) {
            try {
              report = new Report(id, year, farms);
              reportScene.setReport(report);
              Main.setStage("Report");
            } catch (InvalidReportException | InvalidDateException error) {
              errorPrompt(stage, "Report Creation", "Report generation error occured.");
            }
          } else {
            errorPrompt(stage, "Report Creation", "Invalid farm ID and year inputs.");
          }
        } else if (comboBox.getValue().equals("Annual")) {
          Integer year;
          try {
            year = Integer.parseInt(textPrompt("Year"));
          } catch (NumberFormatException error) {
            year = null;
          }
          if (year != null) {
            try {
              report = new Report(year, farms);
              reportScene.setReport(report);
              Main.setStage("Report");
            } catch (InvalidReportException | InvalidDateException error) {
              errorPrompt(stage, "Report Creation", "Report generation error occured.");
            }
          } else {
            errorPrompt(stage, "Report Creation", "Invalid year input.");
          }
        } else if (comboBox.getValue().equals("Monthly")) {
          Integer year, month;
          try {
            year = Integer.parseInt(textPrompt("Year"));
            month = Integer.parseInt(textPrompt("Month"));
          } catch (NumberFormatException error) {
            year = null;
            month = null;
          }
          if (year != null && month != null) {
            try {
              report = new Report(year, farms);
              reportScene.setReport(report);
              Main.setStage("Report");
            } catch (InvalidReportException | InvalidDateException error) {
              errorPrompt(stage, "Report Creation", "Report generation error occured.");
            }
          } else {
            errorPrompt(stage, "Report Creation", "Invalid year input.");
          }
        } else if (comboBox.getValue().equals("Date Range")) {
          Main.setStage("Report");
        }
      } else {
        errorPrompt(stage, "Report Selection", "Select a report type before generating a report.");
      }
    });
  }

  /**
   * Sets the stage to the import scene
   */
  public BorderPane getScene() {
    return root;
  }

  // Private Helper Methods

  /**
   * Adds a farm milk weight entry to the farm matching the id passed in the farms
   * list. If the farm does not already exist, it creates a farm and adds the milk
   * weight entry to the new farm.
   * 
   * @param id     the id of the farm
   * @param date   the date being added
   * @param weight the weight being added
   */
  private void add(String id, LocalDate date, int weight) {
    // Create a new farm variable
    Farm farm = null;
    // Look through the farms array list, see if any farm object has the same
    // ID as the ID passed for the add function
    for (Farm f : farms)
      // If the ID for the farm matches, set the farm variable to the farm in the list
      // (variable f)
      if (f.getID().equals(id))
        farm = f;
    // After the for loop, if the farm variable is still null (meaning no matches)
    // create a new entry in the farms array list
    if (farm == null) {
      farm = new Farm(id);
      farms.add(farm);
    }
    // Insert the value into the correct farm in the farms array list
    farm.insert(date, weight);
  }

  /**
   * Removes a farm milk weight entry to the farm matching the id passed in the
   * farms list. If the farm or date entry doesn't exist, nothing occurs
   * 
   * @param id   the id of the farm
   * @param date the date being removed
   */
  private void remove(String id, LocalDate date) {
    // Look through the farms array list, see if any farm object has the same
    // ID as the ID passed for the remove function
    for (Farm f : farms)
      // If the ID for the farm matches, remove the date key from the farm
      if (f.getID().equals(id))
        f.remove(date);
  }

  /**
   * Updates the main table on the scene with all the current values across all
   * farm objects in the farms array list
   */
  private void updateTable() {
    farmRows.clear();
    for (Farm farm : farms)
      for (Entry<LocalDate, Integer> entry : farm.getSet())
        farmRows.add(new FarmRow(farm.getID(), entry.getKey(), entry.getValue()));
  }

  private String textPrompt(String name) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Enter " + name);
    dialog.setHeaderText("Enter " + name);
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      return result.get();
    }
    return null;
  }

  private void errorPrompt(Stage stage, String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title + " Error");
    alert.setHeaderText(title + " Error");
    alert.setContentText(message);
    alert.showAndWait();
  }

}

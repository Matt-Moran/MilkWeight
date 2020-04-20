package application;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.TilePane;

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
		// Create an BorderPane as the root of the scene
		root = new BorderPane();

		// Add a button to the root
		Button b = new Button("Go to Import Scene");
		root.setBottom(b);
		b.setOnAction(e -> {
			Main.setStage("Import");
		});
		// TODO: Call ImportScene.getReport() - a3

		// Display Type of Report at Top of screen
		String report = new String("Farm Report"); 
		Label l = new Label(report);
		root.setTop(l);
		// Display Table on Right side of screen
		// General Format: Month/Farm ID, Total Weight, Percent of Total Weight
		
		
		// Display Pie Chart in Center
		

		// Display Min, Max, Average on Right side

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

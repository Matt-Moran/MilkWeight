package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.TilePane;
import javafx.scene.chart.*;
import java.util.Arrays;

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
		int[] month = new int[] { 500, 400, 200, 100, 300, 200, 650, 50, 90, 80, 110, 120 };
		int[] percentages = determinePercentages(month);

		// Display Pie Chart in Center
		PieChart p = new PieChart();
		// add data to piechart
		for (int i = 0; i < percentages.length; i++) {
			PieChart.Data slice = new PieChart.Data("Month " + Integer.toString(i), percentages[i]);
			p.getData().add(slice);
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		p.setTitle(report);
		root.setCenter(p);

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

	public static int[] determinePercentages(int[] month) {
		int percent[] = new int[month.length];
		int total = 0;
		int percentage = 0;
		for (int i = 0; i < month.length; i++) {
			total = total + month[i];
		}

		for (int j = 0; j < month.length; j++) {
			percentage = (int) ((((double) month[j] / (double) total)) * 100);
			percent[j] = percentage;
		}
		return percent;
	}

	public static int getMin(int[] month) {
		int min = month[0];
		for (int i = 0; i < month.length; i++) {
			if (min > month[i]) {
				min = month[i];
			}
		}
		return min;
	}

	public static int getMax(int[] month) {
		int max = month[0];
		for (int i = 0; i < month.length; i++) {
			if (max < month[i]) {
				max = month[i];
			}
		}
		return max;
	}

	public static int getAverage(int[] month) {
		int total = 0;
		for (int i = 0; i < month.length; i++) {
			total = total + month[i];
		}
		int average = (total / month.length);
		return average;
	}
}

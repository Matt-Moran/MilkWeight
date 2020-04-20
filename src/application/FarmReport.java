package application;

/**
 * Implements Report to create a report based on the type selected (farm, year,
 * monthly or date range)
 * 
 * @author ATEAM 050
 *
 */
public class FarmReport implements Report {
	private String type;

	FarmReport(String type) {
		this.type = type;
	}

	@Override
	public int[] getTotal() {
		int[] month = new int[] { 500, 400, 200, 100, 300, 200, 650, 50, 90, 80, 110, 120 };
		return month;
	}

}

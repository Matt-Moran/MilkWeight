package application;

/**
 * Contains the main functions required to generate a report for a farm, year,
 * monthly or date range
 * 
 * @author ATEAM 050
 *
 */
public interface Report {
	/** Gets the 
	 * 
	 * @return an array of the total weights 
	 */
	public int[] getTotal();
	
	
}

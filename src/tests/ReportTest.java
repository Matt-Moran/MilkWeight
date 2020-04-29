package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

import application.DataFormatException;
import application.Farm;
import application.Import;
import application.InvalidDateException;
import application.InvalidReportException;
import application.MissingDataException;
import application.Report;
import javafx.util.Pair;
//import junit.framework.Assert;

public class ReportTest {
	
	private ArrayList<Farm> testFarms;
	
	
	
	/**
	 *  Tests that Farm Report is correctly constructed from
	 *  some sample data
	 */
	@Test
	public void test000_farm_report() {
		testFarms = new ArrayList<Farm>();
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		Farm farm4 = new Farm("4");
		Farm farm5 = new Farm("5");
		Farm farm6 = new Farm("6");
		
		LocalDate date1 = LocalDate.of(2019, 1, 1);
		LocalDate date2 = LocalDate.of(2019, 3, 12);
		LocalDate date3 = LocalDate.of(2019, 4, 16);
		LocalDate date4 = LocalDate.of(2019, 8, 7);
		LocalDate date5 = LocalDate.of(2019, 2, 2);
		LocalDate date6 = LocalDate.of(2019, 4, 11);

		farm1.insert(date1, 10);
		farm2.insert(date2, 20);
		farm3.insert(date3, 30);
		farm4.insert(date4, 40);
		farm5.insert(date5, 50);
		farm6.insert(date6, 50);
		
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		testFarms.add(farm4);
		testFarms.add(farm5);
		testFarms.add(farm6);

		try {
			Report farmReport = new Report("1", 2019, testFarms);
			ArrayList<Pair<String, String>> report = farmReport.getReport();
			ArrayList<Pair<String, Integer>> pieGraph = farmReport.getPieGraph();
			
			Pair<String, String> farmId = new Pair<String, String>("Farm ID", "1");
			Pair<String, String> year = new Pair<String, String>("Year", "2019");
			Pair<String, String> yearTotal = new Pair<String, String>("Year Total", "10");
			Pair<String, String> yearPercentage = new Pair<String, String>("Year Percentage", "5.0%");
			Pair<String, String> monthTotal = new Pair<String, String>("Month 1 Total", "10");
			Pair<String, String> monthPercentage = new Pair<String, String>("Month 1 Percentage", "100.0%");
			
			Pair<String, Integer> pieGraphMonthTotal = new Pair<String, Integer>("Month 1", 10);
			// checks with sample data that report is correctly constructed
			// when creating a farm report
			Assert.assertEquals(farmId, report.get(0));
			Assert.assertEquals(year, report.get(1));
			Assert.assertEquals(yearTotal, report.get(2));
			Assert.assertEquals(yearPercentage, report.get(3));
			Assert.assertEquals(monthTotal, report.get(4));
			Assert.assertEquals(monthPercentage, report.get(5));
			
			// tests that piegraph is correctly constructed from data
			Assert.assertEquals(pieGraphMonthTotal, pieGraph.get(0));
			
			// all other months should have 0 for their milkweight
			// loops through and checks every month
			for (int i = 2; i <=12; i++) {
				Pair<String, Integer> restOfMonths = 
						new Pair<String, Integer>("Month " + String.valueOf(i), 0);
				
				Assert.assertEquals(restOfMonths, pieGraph.get(i-1));
				
			}
			
		} catch (InvalidReportException e) {
			fail("Should not throw InvalidReportException");
			e.printStackTrace();
		}
		
		catch (InvalidDateException e) {
			fail("Should not throw InvalidDateException");
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Tests that Annual Report is correctly constructed with
	 *  sample data
	 */
	@Test
	public void test001_annual_report() {
		testFarms = new ArrayList<Farm>();
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		Farm farm4 = new Farm("4");
		Farm farm5 = new Farm("5");
		Farm farm6 = new Farm("6");
		
		LocalDate date1 = LocalDate.of(2019, 1, 1);
		LocalDate date2 = LocalDate.of(2019, 3, 12);
		LocalDate date3 = LocalDate.of(2019, 4, 16);
		LocalDate date4 = LocalDate.of(2019, 8, 7);
		LocalDate date5 = LocalDate.of(2019, 2, 2);
		LocalDate date6 = LocalDate.of(2019, 4, 11);

		farm1.insert(date1, 10);
		farm2.insert(date2, 20);
		farm3.insert(date3, 30);
		farm4.insert(date4, 40);
		farm5.insert(date5, 50);
		farm6.insert(date6, 50);
		
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		testFarms.add(farm4);
		testFarms.add(farm5);
		testFarms.add(farm6);
		

		try {
			Report annualReport = new Report(2019, testFarms);
			ArrayList<Pair<String, String>> report = annualReport.getReport();
			ArrayList<Pair<String, Integer>> pieGraph = annualReport.getPieGraph();
			
			// sample data for what the yearly report should produce
			Pair<String, String> year = new Pair<String, String>("Year", "2019");
			Pair<String, String> yearTotal = new Pair<String, String>("Year Total", "200");
			
			Pair<String, String> farm1Total = new Pair<String, String>("Farm 1 Total", "10");
			Pair<String, String> farm1Percentage = new Pair<String, String>("Farm 1 Percentage", "5.0%");
			
			Pair<String, String> farm2Total = new Pair<String, String>("Farm 2 Total", "20");
			Pair<String, String> farm2Percentage = new Pair<String, String>("Farm 2 Percentage", "10.0%");
			
			Pair<String, String> farm3Total = new Pair<String, String>("Farm 3 Total", "30");
			Pair<String, String> farm3Percentage = new Pair<String, String>("Farm 3 Percentage", "15.0%");
			
			Pair<String, String> farm4Total = new Pair<String, String>("Farm 4 Total", "40");
			Pair<String, String> farm4Percentage = new Pair<String, String>("Farm 4 Percentage", "20.0%");
			
			Pair<String, String> farm5Total = new Pair<String, String>("Farm 5 Total", "50");
			Pair<String, String> farm5Percentage = new Pair<String, String>("Farm 5 Percentage", "25.0%");
			
			Pair<String, String> farm6Total = new Pair<String, String>("Farm 6 Total", "50");
			Pair<String, String> farm6Percentage = new Pair<String, String>("Farm 6 Percentage", "25.0%");
			
			// creates arraylist reportTest, which should match the report arraylist
			ArrayList<Pair<String, String>> reportTest = new ArrayList<Pair<String, String>>();
			reportTest.add(year);
			reportTest.add(yearTotal);
			reportTest.add(farm1Total);
			reportTest.add(farm1Percentage);
			reportTest.add(farm2Total);
			reportTest.add(farm2Percentage);
			reportTest.add(farm3Total);
			reportTest.add(farm3Percentage);
			reportTest.add(farm4Total);
			reportTest.add(farm4Percentage);
			reportTest.add(farm5Total);
			reportTest.add(farm5Percentage);
			reportTest.add(farm6Total);
			reportTest.add(farm6Percentage);
			
			// tests that report matches report test
			Assert.assertEquals(reportTest, report);
			
	
			// PieGraph entries based on sample data
			Pair<String, Integer> pieGraphFarm1 = new Pair<String, Integer>("Farm1", 10);
			Pair<String, Integer> pieGraphFarm2 = new Pair<String, Integer>("Farm2", 20);
			Pair<String, Integer> pieGraphFarm3 = new Pair<String, Integer>("Farm3", 30);
			Pair<String, Integer> pieGraphFarm4 = new Pair<String, Integer>("Farm4", 40);
			Pair<String, Integer> pieGraphFarm5 = new Pair<String, Integer>("Farm5", 50);
			Pair<String, Integer> pieGraphFarm6 = new Pair<String, Integer>("Farm6", 50);
			
			// creates piegraphtest arraylist that should match piegraph
			ArrayList<Pair<String, Integer>> pieGraphTest = new ArrayList<Pair<String, Integer>>();
			pieGraphTest.add(pieGraphFarm1);
			pieGraphTest.add(pieGraphFarm2);
			pieGraphTest.add(pieGraphFarm3);
			pieGraphTest.add(pieGraphFarm4);
			pieGraphTest.add(pieGraphFarm5);
			pieGraphTest.add(pieGraphFarm6);
			
			// tests that pieGraphTest matches pieGraph
			Assert.assertEquals(pieGraphTest, pieGraph);


	
		} catch (InvalidReportException e) {
			fail("Should not throw InvalidReportException");
			e.printStackTrace();
		}
		
		catch (InvalidDateException e) {
			fail("Should not throw InvalidDateException");
			e.printStackTrace();
		}

		
	}
	
	/** 
	 *  Tests that the montly report is correctly constructed 
	 *  with sample data
	 */
	@Test
	public void test002_monthly_report(){
		
		// creates sample data to test the monthly report
		
		// farms in report
		testFarms = new ArrayList<Farm>();
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		Farm farm4 = new Farm("4");
		Farm farm5 = new Farm("5");
		Farm farm6 = new Farm("6");
		
		// dates where farms will produce milkweight
		LocalDate date1 = LocalDate.of(2019, 3, 1);
		LocalDate date2 = LocalDate.of(2019, 3, 12);
		LocalDate date3 = LocalDate.of(2019, 3, 16);
		LocalDate date4 = LocalDate.of(2019, 3, 7);
		LocalDate date5 = LocalDate.of(2019, 3, 2);
		LocalDate date6 = LocalDate.of(2019, 3, 11);

		// adds milkweight data to each farm
		farm1.insert(date1, 10);
		farm2.insert(date2, 20);
		farm3.insert(date3, 30);
		farm4.insert(date4, 40);
		farm5.insert(date5, 50);
		farm6.insert(date6, 50);
		
		// adds farms with milkweight data to sample
		// arraylist of farms
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		testFarms.add(farm4);
		testFarms.add(farm5);
		testFarms.add(farm6);
		

		try {
			Report monthReport = new Report(2019, 3, testFarms);
			ArrayList<Pair<String, String>> report = monthReport.getReport();
			ArrayList<Pair<String, Integer>> pieGraph = monthReport.getPieGraph();

			
			// what monthly report should populate the "report" array with
			// based on sample data
			Pair<String, String> year = new Pair<String, String>("Year", "2019");
			Pair<String, String> month = new Pair<String, String>("Month", "3");
			Pair<String, String> monthTotal = new Pair<String, String>("Month Total", "200");

			
			Pair<String, String> farm1Total = new Pair<String, String>("Farm1 Total", "10");
			Pair<String, String> farm1Percentage = new Pair<String, String>("Farm1 Percentage", "5.0%");
			
			Pair<String, String> farm2Total = new Pair<String, String>("Farm2 Total", "20");
			Pair<String, String> farm2Percentage = new Pair<String, String>("Farm2 Percentage", "10.0%");
			
			Pair<String, String> farm3Total = new Pair<String, String>("Farm3 Total", "30");
			Pair<String, String> farm3Percentage = new Pair<String, String>("Farm3 Percentage", "15.0%");
			
			Pair<String, String> farm4Total = new Pair<String, String>("Farm4 Total", "40");
			Pair<String, String> farm4Percentage = new Pair<String, String>("Farm4 Percentage", "20.0%");
			
			Pair<String, String> farm5Total = new Pair<String, String>("Farm5 Total", "50");
			Pair<String, String> farm5Percentage = new Pair<String, String>("Farm5 Percentage", "25.0%");
			
			Pair<String, String> farm6Total = new Pair<String, String>("Farm6 Total", "50");
			Pair<String, String> farm6Percentage = new Pair<String, String>("Farm6 Percentage", "25.0%");
			
			// creates arraylist reportTest, which should match the report arraylist
			ArrayList<Pair<String, String>> reportTest = new ArrayList<Pair<String, String>>();
			reportTest.add(year);
			reportTest.add(month);
			reportTest.add(monthTotal);
			reportTest.add(farm1Total);
			reportTest.add(farm1Percentage);
			reportTest.add(farm2Total);
			reportTest.add(farm2Percentage);
			reportTest.add(farm3Total);
			reportTest.add(farm3Percentage);
			reportTest.add(farm4Total);
			reportTest.add(farm4Percentage);
			reportTest.add(farm5Total);
			reportTest.add(farm5Percentage);
			reportTest.add(farm6Total);
			reportTest.add(farm6Percentage);
			
			// tests that reportTest matches report
			Assert.assertEquals(reportTest, report);
			
			// pieGraph entries based on sample data
			Pair<String, Integer> pieGraphFarm1 = new Pair<String, Integer>("Farm1", 10);
			Pair<String, Integer> pieGraphFarm2 = new Pair<String, Integer>("Farm2", 20);
			Pair<String, Integer> pieGraphFarm3 = new Pair<String, Integer>("Farm3", 30);
			Pair<String, Integer> pieGraphFarm4 = new Pair<String, Integer>("Farm4", 40);
			Pair<String, Integer> pieGraphFarm5 = new Pair<String, Integer>("Farm5", 50);
			Pair<String, Integer> pieGraphFarm6 = new Pair<String, Integer>("Farm6", 50);
			
			// creates pieGraphTest arraylist which should match pieGraph
			ArrayList<Pair<String, Integer>> pieGraphTest = new ArrayList<Pair<String, Integer>>();
			pieGraphTest.add(pieGraphFarm1);
			pieGraphTest.add(pieGraphFarm2);
			pieGraphTest.add(pieGraphFarm3);
			pieGraphTest.add(pieGraphFarm4);
			pieGraphTest.add(pieGraphFarm5);
			pieGraphTest.add(pieGraphFarm6);
			
			// tests that pieGraphTest matches pieGraph
			Assert.assertEquals(pieGraphTest, pieGraph);

			
		} catch (InvalidReportException e) {
			fail("Should not throw InvalidReportException");
			e.printStackTrace();
		}
		
		catch (InvalidDateException e) {
			fail("Should not throw InvalidDateException");
			e.printStackTrace();
		}
		

		
	}
	
	/**
	 *  Tests that a date range report is correctly
	 *  constructed with sample data
	 */
	@Test
	public void test003_date_range_report() {
		
		// creates sample data to test the monthly report
		
		// farms in report
		testFarms = new ArrayList<Farm>();
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		Farm farm4 = new Farm("4");
		Farm farm5 = new Farm("5");
		Farm farm6 = new Farm("6");
		
		// dates where farms will produce milkweight
		LocalDate date1 = LocalDate.of(2019, 2, 1);
		LocalDate date2 = LocalDate.of(2019, 3, 12);
		LocalDate date3 = LocalDate.of(2019, 5, 16);
		LocalDate date4 = LocalDate.of(2019, 5, 7);
		LocalDate date5 = LocalDate.of(2019, 7, 2);
		LocalDate date6 = LocalDate.of(2019, 11, 11);

		// adds milkweight data to each farm
		farm1.insert(date1, 10);
		farm2.insert(date2, 20);
		farm3.insert(date3, 30);
		farm4.insert(date4, 40);
		farm5.insert(date5, 50);
		farm6.insert(date6, 50);
		
		// adds farms with milkweight data to sample
		// arraylist of farms
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		testFarms.add(farm4);
		testFarms.add(farm5);
		testFarms.add(farm6);
		

		try {
			Report dateRangeReport = new Report(date1, date6, testFarms);
			ArrayList<Pair<String, String>> report = dateRangeReport.getReport();
			ArrayList<Pair<String, Integer>> pieGraph = dateRangeReport.getPieGraph();

			
			// what monthly report should populate the "report" arraylist with
			// based on sample data
			Pair<String, String> startDate = new Pair<String, String>("Start Date", "2019-02-01");
			Pair<String, String> endDate = new Pair<String, String>("End Date", "2019-11-11");
			Pair<String, String> rangeTotal = new Pair<String, String>("Range Total", "200");

			Pair<String, String> farm1Total = new Pair<String, String>("Farm 1 Total", "10");
			Pair<String, String> farm1Percentage = new Pair<String, String>("Farm 1 Percentage", "5.0%");
			
			Pair<String, String> farm2Total = new Pair<String, String>("Farm 2 Total", "20");
			Pair<String, String> farm2Percentage = new Pair<String, String>("Farm 2 Percentage", "10.0%");
			
			Pair<String, String> farm3Total = new Pair<String, String>("Farm 3 Total", "30");
			Pair<String, String> farm3Percentage = new Pair<String, String>("Farm 3 Percentage", "15.0%");
			
			Pair<String, String> farm4Total = new Pair<String, String>("Farm 4 Total", "40");
			Pair<String, String> farm4Percentage = new Pair<String, String>("Farm 4 Percentage", "20.0%");
			
			Pair<String, String> farm5Total = new Pair<String, String>("Farm 5 Total", "50");
			Pair<String, String> farm5Percentage = new Pair<String, String>("Farm 5 Percentage", "25.0%");
			
			Pair<String, String> farm6Total = new Pair<String, String>("Farm 6 Total", "50");
			Pair<String, String> farm6Percentage = new Pair<String, String>("Farm 6 Percentage", "25.0%");
			
			// creates arraylist reportTest, which should match the report arraylist
			ArrayList<Pair<String, String>> reportTest = new ArrayList<Pair<String, String>>();
			reportTest.add(startDate);
			reportTest.add(endDate);
			reportTest.add(rangeTotal);
			reportTest.add(farm1Total);
			reportTest.add(farm1Percentage);
			reportTest.add(farm2Total);
			reportTest.add(farm2Percentage);
			reportTest.add(farm3Total);
			reportTest.add(farm3Percentage);
			reportTest.add(farm4Total);
			reportTest.add(farm4Percentage);
			reportTest.add(farm5Total);
			reportTest.add(farm5Percentage);
			reportTest.add(farm6Total);
			reportTest.add(farm6Percentage);
			
			// tests that reportTest and report are equal
			Assert.assertEquals(reportTest, report);
			
			// the correct data that should be populated into the piegraph arraylist
			Pair<String, Integer> pieGraphFarm1 = new Pair<String, Integer>("Farm 1", 10);
			Pair<String, Integer> pieGraphFarm2 = new Pair<String, Integer>("Farm 2", 20);
			Pair<String, Integer> pieGraphFarm3 = new Pair<String, Integer>("Farm 3", 30);
			Pair<String, Integer> pieGraphFarm4 = new Pair<String, Integer>("Farm 4", 40);
			Pair<String, Integer> pieGraphFarm5 = new Pair<String, Integer>("Farm 5", 50);
			Pair<String, Integer> pieGraphFarm6 = new Pair<String, Integer>("Farm 6", 50);
			
			ArrayList<Pair<String, Integer>> pieGraphTest = new ArrayList<Pair<String, Integer>>();
			pieGraphTest.add(pieGraphFarm1);
			pieGraphTest.add(pieGraphFarm2);
			pieGraphTest.add(pieGraphFarm3);
			pieGraphTest.add(pieGraphFarm4);
			pieGraphTest.add(pieGraphFarm5);
			pieGraphTest.add(pieGraphFarm6);
			
			// tests that pieGraphTest and pieGraph are equal
			Assert.assertEquals(pieGraphTest, pieGraph);


			
		} catch (InvalidReportException e) {
			fail("Should not throw InvalidReportException");
			e.printStackTrace();
		}
		
		catch (InvalidDateException e) {
			fail("Should not throw InvalidDateException");
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid farm ID when creating a farm report
	 */
	@Test
	public void test004_faulty_farm_id_farm_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		
		try {
			// farm report with farm ID not existing in list of farms
			Report farmReport = new Report("4", 2018, testFarms);
			// fail if exception not caught
			fail("InvalidReportException not caught");
			
		}
		catch (InvalidReportException e) {
			// pass if exception caught
			Assert.assertTrue(true);
		}
		catch (InvalidDateException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid year when creating a farm report
	 */
	@Test
	public void test005_faulty_year_farm_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		
		try {
			// farm report with faulty year
			Report farmReport = new Report("3", -33, testFarms);
			// fail if exception not caught
			fail("InvalidReportException not caught");
			
		}
		catch (InvalidReportException e) {
			// pass if exception caught
			Assert.assertTrue(true);
		}
		catch (InvalidDateException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid year when creating an annual report
	 */
	@Test
	public void test006_faulty_year_annual_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		
		try {
			// annual report with faulty year
			Report annual = new Report(-33, testFarms);
			// fail if exception not caught
			fail("InvalidReportException not caught");
			
		}
		catch (InvalidReportException e) {
			// pass if exception caught
			Assert.assertTrue(true);
		}
		catch (InvalidDateException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid year for monthly report
	 */
	@Test
	public void test007_faulty_year_monthly_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		
		try {
			// creates monthly report with faulty year
			Report monthlyReport = new Report(-33, 1, testFarms);
			// fail if exception not caught
			fail("InvalidReportException not caught");
			
		}
		catch (InvalidReportException e) {
			// pass if exception caught
			Assert.assertTrue(true);
		}
		catch (InvalidDateException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid month for monthly report
	 */
	@Test
	public void test008_faulty_month_monthly_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		
		try {
			// create monthly report with faulty month
			Report monthlyReport = new Report(2018, -3, testFarms);
			// fail if exception not caught
			fail("InvalidReportException not caught");
			
		}
		catch (InvalidReportException e) {
			// pass if exception caught
			Assert.assertTrue(true);
		}
		catch (InvalidDateException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid start date for date range report
	 */
	@Test
	public void test009_faulty_startDate_date_range_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		LocalDate start = null;
		LocalDate end = LocalDate.of(2019,04,2);
		
		
		
		try {
			// creates date Range Report with faulty start date
			Report dateRangeReport = new Report(start, end, testFarms);
			// fail if exception not caught
			fail("InvalidDateException not caught");
			
		}
		catch (InvalidReportException e) {
			e.printStackTrace();
		}
		catch (InvalidDateException e) {
			// pass if exception is caught
			Assert.assertTrue(true);
		}

	}
	
	/**
	 * Tests that an exception will be thrown if the user does not 
	 * enter in a valid end date for date range report
	 */
	@Test
	public void test010_faulty_endDate_date_range_report() {
		
		Farm farm1 = new Farm("1");
		Farm farm2 = new Farm("2");
		Farm farm3 = new Farm("3");
		
		ArrayList<Farm> testFarms = new ArrayList<Farm>();
		testFarms.add(farm1);
		testFarms.add(farm2);
		testFarms.add(farm3);
		
		LocalDate end = null;
		LocalDate start = LocalDate.of(2019,04,2);
		
		
		
		try {
			//creates annual report with faulty end date
			Report dateRangeReport = new Report(start, end, testFarms);
			// fail if exception not caught
			fail("InvalidDateException not caught");
			
		}
		catch (InvalidReportException e) {
			e.printStackTrace();
		}
		catch (InvalidDateException e) {
			// pass if exception caught
			Assert.assertTrue(true);
		}

	}
	
	
	
	
}


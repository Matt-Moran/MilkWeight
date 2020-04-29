package tests;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import application.DataFormatException;
import application.Farm;
import application.Import;
import application.MissingDataException;

public class ImportTest {
  
  ArrayList<Farm> farms;
  Import imp;
  
  @BeforeEach
  public void setUp() throws Exception {
    farms = new ArrayList<Farm>();
    imp = new Import(farms);
  }
  
	@Test
	public void test000_IOException() {
		try {
			File file = new File("incorrectFilepath");
			imp.Parse(file);
			fail("Should throw IOException");
		} catch (IOException e) {

		} catch (Exception e) {
			fail("Should throw IO Exception");
		}
	}

	@Test
	public void test001_correctParse() {
		try {
			File file = new File("data/small/2019-1.csv");
			imp.Parse(file);
		} catch (Exception e) {
			fail("Should not throw exception. Threw exception " + e);
		}
	}

	@Test
	public void test002_dataFormat1() {
		try {
			File file = new File("data/error/2019-1.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test003_dataFormat2() {
		try {
			File file = new File("data/error/2019-2.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test004_dataFormat3() {
		try {
			File file = new File("data/error/2019-3.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test005_dataFormat4() {
		try {
			File file = new File("data/error/2019-4.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test006_dataFormat5() {
		try {
			File file = new File("data/error/2019-5.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test007_dataFormat6() {
		try {
			File file = new File("data/error/2019-6.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test008_dataFormat7() {
		try {
			File file = new File("data/error/2019-7.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test009_dataFormat8() {
		try {
			File file = new File("data/error/2019-8.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test010_dataFormat9() {
		try {
			File file = new File("data/error/2019-9.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test011_dataFormat10() {
		try {
			File file = new File("data/error/2019-10.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test012_dataFormat11() {
		try {
			File file = new File("data/error/2019-11.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test013_dataFormat12() {
		try {
			File file = new File("data/error/2019-12.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test014_missingdata1() {
		try {
			File file = new File("data/missing/2019-1.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception. Threw" + e);
		}
	}

	@Test
	public void test015_missingdata2() {
		try {
			File file = new File("data/missing/2019-2.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test016_missingdata3() {
		try {
			File file = new File("data/missing/2019-3.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test017_missingdata4() {
		try {
			File file = new File("data/missing/2019-4.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test018_missingdata5() {
		try {
			File file = new File("data/missing/2019-5.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test019_missingdata6() {
		try {
			File file = new File("data/missing/2019-6.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test020_missingdata7() {
		try {
			File file = new File("data/missing/2019-7.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test021_missingdata8() {
		try {
			File file = new File("data/missing/2019-8.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test022_missingdata9() {
		try {
			File file = new File("data/missing/2019-9.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test023_missingdata10() {
		try {
			File file = new File("data/missing/2019-10.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test024_missingdata11() {
		try {
			File file = new File("data/missing/2019-11.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test025_missingdata12() {
		try {
			File file = new File("data/missing/2019-12.csv");
			imp.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (MissingDataException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}

	@Test
	public void test026_testingFarmIDasAComma() {
		try {
			File file = new File("data/extra/2019-1.csv");
			imp.Parse(file);
		} catch (Exception e) {
			fail("Should not throw any type of exception");
		}
	}

	@Test
	public void test027_testingFarmIDasEmptyWhiteSpace() {
		try {
			File file = new File("data/extra/2019-2.csv");
			imp.Parse(file);
		} catch (Exception e) {
			fail("Should not throw any exception");
		}
	}

	@Test
	public void test028_farmIDasEmptyString() {
		try {
			File file = new File("data/extra/2019-3.csv");
			imp.Parse(file);
			fail("Did not throw Data format exception");
		} catch (DataFormatException e) {
			// should throw missing data exception
		} catch (Exception e) {
			fail("Should not throw any exception");
		}
	}
}

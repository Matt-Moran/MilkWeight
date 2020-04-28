package tests;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import application.DataFormatException;
import application.Import;

public class ImportTest {
	@Test
	public void test000_IOException() {
		try {
			File file = new File("incorrectFilepath");
			Import.Parse(file);
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
			Import.Parse(file);
		} catch (Exception e) {
			fail("Should not throw exception. Threw exception " + e);
		}
	}

	@Test
	public void test002_dataFormat1() {
		try {
			File file = new File("data/error/2019-1.csv");
			Import.Parse(file);
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
			Import.Parse(file);
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
			Import.Parse(file);
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
			Import.Parse(file);
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
			Import.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}
}

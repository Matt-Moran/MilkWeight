package application;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImportTest {
	protected Import i;

	@Before
	public void setUp() throws Exception {
		i = new Import();
	}

	@Test
	public void test000_IOException() {
		try {
			File file = new File("incorrectFilepath");
			i.Parse(file);
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
			i.Parse(file);
		} catch (Exception e) {
			fail("Should not throw exception. Threw exception " + e);
		}
	}

	@Test
	public void test002_dataFormat1() {
		try {
			File file = new File("data/error/2019-1.csv");
			i.Parse(file);
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
			i.Parse(file);
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
			i.Parse(file);
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
			i.Parse(file);
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
			i.Parse(file);
			fail("Should throw Data Format Exception");
		} catch (DataFormatException e) {
			// should throw data Format Exception
		} catch (Exception e) {
			fail("Should not throw other type of exception");
		}
	}
}

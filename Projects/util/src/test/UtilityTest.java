package test;

import util.Utility;
import junit.framework.TestCase;

public class UtilityTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testReadFile() {
		String fileName = "E:/Long/EatJ.txt";
		Utility.readFile(fileName);
	}
}

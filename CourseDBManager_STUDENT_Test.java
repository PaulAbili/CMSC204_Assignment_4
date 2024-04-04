package application;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Paul Abili
 * This is the student created test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 */
public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgrStudent = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgrStudent = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgrStudent = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgrStudent.add("CMSC205",99999,6,"MC250","Paul Abili");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgrStudent.add("CMSC205",99999,6,"MC250","Paul Abili");
		dataMgrStudent.add("CMSC207",10,4,"H290","James Casey");
		dataMgrStudent.add("CMSC204",559,4,"SCF3DK","Howald Tuner");
		dataMgrStudent.add("CMSC205",99999,6,"MC250","Paul Abili");
		ArrayList<String> list = dataMgrStudent.showAll();
		assertEquals(3,list.size());
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("StudentTest.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC205 99999 6 MC250 Paul Abili");
			inFile.print("CMSC207 10 4 H290 James Casey");
			
			inFile.close();
			dataMgrStudent.readFile(inputFile);
			assertEquals("CMSC205",dataMgrStudent.get(99999).getID());
			assertEquals("CMSC207",dataMgrStudent.get(10).getID());
			assertEquals("MC250",dataMgrStudent.get(99999).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}

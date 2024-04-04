package application;
import java.io.*;
import java.util.*;
/**
 * @author Paul Abili
 * CourseDBManager which manages the CourseDBStrucutre
 */
public class CourseDBManager implements CourseDBManagerInterface {
	private CourseDBStructure CDS;

	public CourseDBManager() {
		CDS = new CourseDBStructure(500);
	}

	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CDS.add(new CourseDBElement(id,crn,credits,roomNum,instructor));
	}

	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	public CourseDBElement get(int crn) {
		try {
			return CDS.get(crn);
		} catch(IOException e) {
			return new CourseDBElement("",-1,0,"","");
		}
	}

	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	public void readFile(File input) throws FileNotFoundException{
		if(input.exists()) {
			Scanner reader = new Scanner(input);
			String data;
			String courseID = ""; int CRN = 0; int credits = 0; 
			String roomNumber = ""; String instructorName = "";
			char c;
			int prevIndex = 0;
			while(reader.hasNextLine()) {
				data = reader.nextLine();
				courseID = roomNumber = instructorName = "";
				CRN = credits = 0;
				for(int j = 0; j < data.length(); j++) {
					c = data.charAt(j);
					if(Character.isSpaceChar(c) && courseID.equals("")) {
						courseID = data.substring(0,j);
						prevIndex = j+1;
					} else if(Character.isSpaceChar(c) && CRN == 0) {
						CRN = Integer.parseInt(data.substring(prevIndex,j));
						prevIndex = j+1;
					} else if(Character.isSpaceChar(c) && credits == 0) {
						credits = Integer.parseInt(data.substring(prevIndex,j));
						prevIndex = j+1;
					} else if(Character.isSpaceChar(c) && roomNumber.equals("")) {
						roomNumber = data.substring(prevIndex,j);
						prevIndex = j+1;
					}
				}
				instructorName = data.substring(prevIndex);
				this.add(courseID, CRN, credits, roomNumber, instructorName);
			}
			reader.close();
		} else {
			throw new FileNotFoundException();
		}
	}
	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	public ArrayList<String> showAll(){
		return CDS.showAll();
	}

}

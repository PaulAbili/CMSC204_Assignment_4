package application;
import java.io.*;
import java.util.*;


/**
 * @author Paul Abili
 * CourseDBStructure Class which creates a hashtable 
 */
public class CourseDBStructure implements CourseDBStructureInterface {
	private int count;
	private int tableSize;
	private final static float LOADFACTOR = 1.5f;
	private LinkedList<CourseDBElement>[] CDS;

	public CourseDBStructure(int n) {
		count = n;
		tableSize = getTableSize();
		CDS = new LinkedList[tableSize];
	}

	public CourseDBStructure(String info, int hashTableSize) {
		tableSize = hashTableSize;
		CDS = new LinkedList[tableSize];
	}

	/** 
	 * Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	 * of the CourseDatabaseElemen object's crn value.
	 * If the CourseDatabaseElement already exists, exit quietly
	 *  
	 * @param element the CourseDBElement to be added to CourseDBStructure
	 */
	public void add(CourseDBElement element) {
		boolean check = true;
		int mod = element.hashCode() % tableSize;
		if(mod < 0) {
			mod += tableSize;
		}
		if(CDS[mod] == null) {
			CDS[mod] = new LinkedList<CourseDBElement>();
			CDS[mod].add(element);
		} else {
			for(int i = 0; i < CDS[mod].size(); i++) {
				if(CDS[mod].get(i).getCRN() == element.getCRN()) {
					check = false;
					CDS[mod].get(i).setID(element.getID());
					CDS[mod].get(i).setCredits(element.getCredits());
					CDS[mod].get(i).setRoomNum(element.getRoomNum());
					CDS[mod].get(i).setInstructorName(element.getInstructorName());
				}
			}
			if(check) {
				CDS[mod].add(element);
			}
		}
	}

	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */

	public CourseDBElement get(int crn) throws IOException{
		int mod = ("" + crn).hashCode() % tableSize;
		if(CDS[mod] == null) {
			throw new IOException();
		} else {
			return (CourseDBElement) CDS[mod].get(0);
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
		ArrayList<String> courseInfo=new ArrayList<String>();
		for(int i = 0; i < tableSize; i++) {
			while(CDS[i]!= null) {
				for(int j = 0; j < CDS[i].size();j++) {
					CourseDBElement element= (CourseDBElement) CDS[i].get(j);
					courseInfo.add("\nCourse:" + element.getID() + " CRN:" + element.getCRN() 
					+ " Credits:" + element.getCredits() + " Instructor:" + element.getInstructorName() +
					" Room:" + element.getRoomNum());
				}
				break;
			}
		}
		return courseInfo;
	}
	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	 */
	public int getTableSize() {
		boolean fkp3 = false;
		boolean primeNumber = false;
		boolean check = false;
		int prime = (int)(count / LOADFACTOR);
		if(prime % 2 == 0) {
			prime++;
		}
		while(fkp3 == false) {
			while(primeNumber == false) {
				check = false;
				for(int i = 2; i < prime && check == false; i++){
					if(prime % i == 0) {
						check = true;
					}
				}
				if(check == true) {
					prime += 2;
				} else {
					primeNumber = true;
				}

			}
			if((prime - 3) % 4 == 0) {
				fkp3 = true;
			} else {
				prime += 2;
				primeNumber = false;
			}
		}
		if(tableSize == 0) { // Initial Value for Integers
			return prime;
		} else {
			return tableSize;
		}
	}
}

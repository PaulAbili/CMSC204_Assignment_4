package application;
import java.lang.Comparable;
/**
 * @author Paul Abili
 * CourseDBElement class which creates an Object CourseDBElement 
 */
public class CourseDBElement implements Comparable<CourseDBElement>{
	private String courseID;
	private String roomNumber;
	private String instructorName;
	private int CRN;
	private int credits; 


	public CourseDBElement() {
		this(null,0,0,null,null);
	}

	public CourseDBElement(String courseID, int CRN, int credits, String roomNumber, String instructorName) {
		this.courseID = courseID;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
		this.CRN = CRN;
		this.credits = credits;
	}
	/** 
	 *  Returns the value of the courseID 
	 * @returns String the courseID Value 
	 */
	public String getID() {
		return courseID;
	}
	/** 
	 *  Returns the value of the roomNumber 
	 * @returns String the rooomNumber Value 
	 */
	public String getRoomNum() {
		return roomNumber;
	}
	/** 
	 *  Returns the instructor of the roomNumber 
	 * @returns String the rooomNumber Value 
	 */
	public String getInstructorName() {
		return instructorName;
	}
	/** 
	 *  Returns the value of the CRN 
	 * @returns int the CRN Value 
	 */
	public int getCRN() {
		return CRN;
	}
	/** 
	 *  Returns the value of the credits 
	 * @returns int the credits Value 
	 */
	public int getCredits() {
		return credits;
	}
	/** 
	 *  Sets the value of the CourseID 
	 * @param id the String which the courseID will be set to 
	 */
	public void setID(String id) {
		courseID = id;
	}
	/** 
	 *  Sets the value of the RoomNumber 
	 * @param roomNum the String which the roomNumber will be set to 
	 */
	public void setRoomNum(String roomNum) {
		roomNumber = roomNum;
	}
	/** 
	 *  Sets the value of the instuctorName 
	 * @param name the String which the instuctorName will be set to 
	 */
	public void setInstructorName(String name) {
		instructorName = name;
	}
	/** 
	 *  Sets the value of the CRN 
	 * @param crn the int which the CRN will be set to 
	 */
	public void setCRN(int crn) {
		CRN = crn;
	}
	/** 
	 *  Sets the value of the credits 
	 * @param credit the int which the credits will be set to 
	 */
	public void setCredits(int credit) {
		credits = credit;
	}
	/** 
	 *  Defines Comparsions for this Class
	 * @param other the CourseDBElement which is being compared to the other CourseDBElement 
	 * @return returns an integer 0 if they are the same, -1 if not
	 */
	public int compareTo(CourseDBElement other) {
		if(this.getID().equals(other.getID())) {
			if(this.getRoomNum().equals(other.getRoomNum())) {
				if(this.getInstructorName().equals(other.getInstructorName())) {
					if(this.getCRN() == other.getCRN()) {
						if(this.getCredits() == other.getCredits()) {
							return 0;
						}
					}
				}
			}
		}
		return -1;
	}
	/** 
	 *  Calculates the hashcode by turning the CRN to a String and finding the hashcode of that
	 * @return returns the hashcode value of the CRN
	 */
	public int hashCode() {
		String hash =  "" + CRN;
		return hash.hashCode();
	}
	/** 
	 *  Class creates the String representation of the information in this class 
	 * @return returns the that String 
	 */
	public String toString() {
		String info = "Course:" + this.getID() + " CRN:" + this.getCRN() 
		+ " Credits:" + this.getCredits() +" Room:" + this.getRoomNum()  +
		" Instructor:" + this.getInstructorName();
		return info;
	}
}

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee. Added the serializable interface to enable save/restore of the
 * employees database to a binary file on disk.
 */
public class Employee implements Serializable {
	private static int employeeNum = 0;
	
	private int empID;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The age. */
	private int age;
	
	/** The pronouns. */
	private String pronouns;
	
	/** The salary. */
	private double salary;
	
	/** The years. */
	private int years;
	
	/** The ssn. */
	private String SSN;	
	
	/** The dept. */
	private String dept;


	/**
	 * Instantiates a new employee.
	 *
	 * @param fname the fname
	 * @param lname the lname
	 * @param SSN the ssn
	 * @param age the age
	 * @param pronouns the pronouns
	 * @param salary the salary
	 * @param years the years
	 * @param dept the dept
	 */
	public Employee (String fname, String lname, String SSN, int age, String pronouns, double salary, int years, String dept) {
		employeeNum ++;
		this.empID = employeeNum;
		this.firstName = fname;
		this.lastName = lname;
		this.SSN = SSN;
		this.age = age;
		this.pronouns = pronouns;
		this.salary = salary;
		this.years = years;
		this.dept = dept;
	}


	public int getEmpID() {
		return empID;
	}


	public void setEmpID(int empID) {
		this.empID = empID;
	}


	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}


	/**
	 * Gets the pronouns.
	 *
	 * @return the pronouns
	 */
	public String getPronouns() {
		return pronouns;
	}


	/**
	 * Sets the pronouns.
	 *
	 * @param pronouns the new pronouns
	 */
	public void setPronouns(String pronouns) {
		this.pronouns = pronouns;
	}


	/**
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}


	/**
	 * Sets the salary.
	 *
	 * @param salary the new salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}


	/**
	 * Gets the years.
	 *
	 * @return the years
	 */
	public int getYears() {
		return years;
	}


	/**
	 * Sets the years.
	 *
	 * @param years the new years
	 */
	public void setYears(int years) {
		this.years = years;
	}


	/**
	 * Gets the ssn.
	 *
	 * @return the ssn
	 */
	public String getSSN() {
		return SSN;
	}


	/**
	 * Sets the ssn.
	 *
	 * @param sSN the new ssn
	 */
	public void setSSN(String sSN) {
		SSN = sSN;
	}


	/**
	 * Gets the dept.
	 *
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	 void updateEmpNum(int usedId) {
		employeeNum = usedId;
	}

	/**
	 * Sets the dept.
	 *
	 * @param dept the new dept
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	/**
	 * To string
	 *
	 * @return the string
	 */
	public String toString( ) {
		String str =   "        Name: "+firstName+" "+lastName+" ("+pronouns+")\n";
		str = str +    " Employee ID: "+empID+"\n";
		str = str +    "         Age: "+age+"      Years Experience: "+years+"\n";
		str = str +    "  Department: "+dept+"\n";
		str = str +    "      Salary: "+salary;
		return(str);
	}

}

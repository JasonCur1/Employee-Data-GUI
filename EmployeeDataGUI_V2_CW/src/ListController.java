import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListController {
	private static ArrayList<Employee> employees;
	
	// these are for new functions to store the employee database... 
	private final static String EMPLOYEE_DB_NAME = "employees.dat";
	private static File objectFile = new File(EMPLOYEE_DB_NAME);
	
	public ListController () {
	}

	// adds a new employee
	public void addEmployee(String fname, String lname, String SSN, String age, String pronouns, 
			                String salary, String years, String dept) {
		
		// controller needs to convert the string data
		employees.add(new Employee(fname,lname,SSN,Integer.parseInt(age),pronouns, 
				      Double.parseDouble(salary),Integer.parseInt(years),dept));
		
		// for debugging
		// System.out.println(employees);

	}
	
	
	// returns a string array of the employee information to be viewed
	public String[] getEmployeeDataStr() {
		// create String[] that is the size of the employee ArrayList
		String[] empStr = new String[employees.size()];
		
		// for each employee, add the toString() info in the corresponding location.
		for (int i = 0; i < employees.size(); i++) {
			empStr[i] = employees.get(i).toString();
		}
		
		
		// temporary placeholder for compilation reasons - will remove later...
		return(empStr);
		
	}
	

	// This will sort the employees array using the ByName comparator
	public void sortByName() {
		Collections.sort(employees, new ByName());
	}

	// This will sort the employees array using the ByID comparator
	public void sortByID() {
		Collections.sort(employees, new ByID());
	}
	
	// ByName comparator to sort the employee array by lastname, firstname
	private class ByName implements Comparator<Employee> {
		public int compare(Employee o1, Employee o2) {
			if (o1.getLastName().compareTo(o2.getLastName()) < 0) {
				return -1;
			}
			
			else if (o1.getLastName().compareTo(o2.getLastName()) > 0) {
				return 1;
			}
			
			else if (o1.getLastName().compareTo(o2.getLastName()) == 0) {
				if (o1.getFirstName().compareTo(o2.getFirstName()) < 0) {
					return -1;
				}
				
				else if (o1.getFirstName().compareTo(o2.getFirstName()) > 0) {
					return 1;
				}
				
				else return 0;
			}
			
			return 0;
		}
	}
	
	// ByID comparator to sort the employee array by empID....
	//        To avoid potential overflow issues, use the Integer.compare() method...
	private class ByID implements Comparator<Employee> {
		public int compare(Employee o1, Employee o2) {
			if (Integer.compare(o1.getEmpID(), o2.getEmpID()) < 0) {
				return -1;
			}
			
			else if (Integer.compare(o1.getEmpID(), o2.getEmpID()) > 0) {
				return 1;
			}
			
			else return 0;
			
		}
	}
	
	// the next routines are used to store and retrieve the employees database from a binary file
	// the database file will need to be deleted if you change the Employee.java file...
	public void initializeDataStructures() {
		readObjectFromDisk();
	}
	/**
	 * This method saves employees array to disk
	 */
	public void writeObjectToDisk() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(objectFile));
			oos.writeObject(employees);
			oos.close();
		} catch (IOException e) {
			System.err.println("Error in writing object to a file");
			e.printStackTrace();
		}
	}

	/**
	 * This method reads the employees array from a disk file if it exists...
	 */
	private void readObjectFromDisk() {
		if (objectFile.exists()) {
			if (objectFile.length() != 0) {
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream(objectFile));
					employees = (ArrayList<Employee>) ois.readObject();
					ois.close();
				} catch (IOException | ClassNotFoundException e) {
					System.err.println("Error in reading object from file");
					e.printStackTrace();
				}
			} else {
				employees = new ArrayList<Employee>();
			}
		} else {
			employees = new ArrayList<Employee>();
			try {
				objectFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Error in creating the file");
				e.printStackTrace();
			}
		}
		if (employees.size() > 0) {
			employees.get(0).updateEmpNum(employees.size());
		}
	}

}
 
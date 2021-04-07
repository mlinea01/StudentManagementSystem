import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentDatabase 
{

	public static void main(String[] args) 
	{	
		StudentDatabase studentDatabase = new StudentDatabase();
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		studentDatabase.createTable();
		
		studentDatabase.enrollStudents(students);
		
		studentDatabase.saveStudentInfo(students);
		
		studentDatabase.displayStudentInfo();
	}
	
	//Creates new table in the database if one doesn't already exist.  
	public void createTable()
	{
		try(Connection connnection = DriverManager.getConnection("jdbc:sqlite:students.db"); Statement statement = connnection.createStatement();) 
		{
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Students(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME STRING, LAST_NAME STRING, YEAR_ATTENDING INTEGER, COURSES STRING, BALANCE INTEGER)");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	//Function to allow the user to continue enrolling students until they would like to quit.
	public void enrollStudents(ArrayList<Student> students)
	{
		Scanner input = new Scanner(System.in);
		
		boolean finishedEnrollingStudents = false;
		boolean isNum = true;
		int newStudent = 0;
		
		//Continue adding students to database until you type 0 to quit. 
		while(!finishedEnrollingStudents)
		{
			
			System.out.print("Enter 1 to add new student or 0 to quit: ");
			
			try
			{
				newStudent = input.nextInt();
			}
			catch(InputMismatchException e)
			{
				isNum = false;
				input.nextLine();
			}
			
			//Doesn't allow the user to enter anything other than a number that is 0 or 1. 
			while(newStudent < 0 || newStudent > 1 || isNum == false)
			{
				System.out.print("Input must be a 0 or 1, Enter 1 to add a new student or 0 to quit: ");
				
				try
				{
					newStudent = input.nextInt();
					isNum = true;
				}
				catch(InputMismatchException e)
				{
					isNum = false;
					input.nextLine();
				}
			}
			
			if(newStudent != 0)
			{
				/*
				 * Create new student object for each student added.
				 * Enroll each of those students in classes. 
				 * Pay tuition at the end of enrolling in classes. 
				 */
				students.add(new Student());
				students.get(students.size() - 1).enrollStudent();
				students.get(students.size() - 1).payTuition();
			}
			else
			{
				finishedEnrollingStudents = true;
			}
			
		}
		
		Collections.sort(students, new SortObjects());
		
		input.close();

	}
	
	//Goes through the list of students and adds each student to the database.   
	public void saveStudentInfo(ArrayList<Student> students)
	{
		try(Connection connnection = DriverManager.getConnection("jdbc:sqlite:students.db"); Statement statement = connnection.createStatement();) 
		{
			//Executes INSERT statement to add each student object to the database. 
			for(int i = 0; i < students.size(); i++)
			{
				Student s = students.get(i);
				statement.executeUpdate("INSERT INTO Students (FIRST_NAME, LAST_NAME, YEAR_ATTENDING, COURSES, BALANCE) VALUES('" + s.getFirstName() + "','" + s.getLastName() + "'," + s.getYearAttending() + ",'" + s.getCourses() + "'," + s.getBalance() + ")");
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}

	}
	
	//Goes through the list of students and displays student info for each student in the system.
	public void displayStudentInfo()
	{
		try(Connection connnection = DriverManager.getConnection("jdbc:sqlite:students.db"); Statement statement = connnection.createStatement();) 
		{
			ResultSet students = statement.executeQuery("SELECT * FROM Students");
			
			while(students.next())
			{
				String id = students.getString("ID");
				String firstName = students.getString("FIRST_NAME");
				String lastName = students.getString("LAST_NAME");
				int yearAttending = students.getInt("YEAR_ATTENDING");
				String courses = students.getString("COURSES");
				int balance = students.getInt("BALANCE");
				
				System.out.println("\n" + "Student ID: " + id + "\n" + 
									"First Name " + firstName + "\n" + 
									"Last Name: " + lastName + "\n" + 
									"Year Attending: " + yearAttending + "\n" + 
									"Courses: " + courses + "\n" + 
									"Balance: " + balance);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

}

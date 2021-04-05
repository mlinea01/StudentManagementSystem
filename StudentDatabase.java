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
		
		studentDatabase.enrollStudents(students);
		
		studentDatabase.displayStudentInfo(students);
		
	}
	
	//Function to allow the user to continue enrolling students until they would like to quit.
	public void enrollStudents(ArrayList<Student> students)
	{
		Scanner input = new Scanner(System.in);
		
		boolean finishedEnrollingStudents = false;
		boolean isNum = true;
		int new_student = 0;
		
		//Continue adding students to database until you type 0 to quit. 
		while(!finishedEnrollingStudents)
		{
			
			System.out.print("Enter 1 to add new student or 0 to quit: ");
			
			try
			{
				new_student = input.nextInt();
			}
			catch(InputMismatchException e)
			{
				isNum = false;
				input.nextLine();
			}
			
			//Doesn't allow the user to enter anything other than a number that is 0 or 1. 
			while(new_student < 0 || new_student > 1 || isNum == false)
			{
				System.out.print("Input must be a 0 or 1, Enter 1 to add a new student or 0 to quit: ");
				
				try
				{
					new_student = input.nextInt();
					isNum = true;
				}
				catch(InputMismatchException e)
				{
					isNum = false;
					input.nextLine();
				}
			}
			
			if(new_student != 0)
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
	
	//Goes through the list of students and displays student info for each student in the system.
	public void displayStudentInfo(ArrayList<Student> students)
	{
		//Prints student info for each student in database. 
		for(int i = 0; i < students.size(); i++)
		{
			System.out.println("");
			students.get(i).displayStudentInfo();
		}
	}

}

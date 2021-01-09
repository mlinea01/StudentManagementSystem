import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentDatabase 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		ArrayList<Student> Students = new ArrayList<Student>();
		
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
				//Create new student object for each student added.
				Students.add(new Student());
				Students.get(Students.size() - 1).enroll_student();
				Students.get(Students.size() - 1).pay_tuition();
			}
			else
			{
				finishedEnrollingStudents = true;
			}
			
		}
		
		//Prints student info for each student in database. 
		for(int i = 0; i < Students.size(); i++)
		{
			System.out.println("");
			Students.get(i).student_info();
		}
		
		input.close();

	}

}

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student 
{
	private String first_name;
	private String last_name;
	private int current_school_year;
	private String student_ID;
	private int balance = 0;
	private static int course_cost = 600;
	private static int id = 1000;
	private boolean isNum = true;
	
	ArrayList<String> student_courses = new ArrayList<>();
	ArrayList<String> professors = new ArrayList<>();
	String[] available_courses = {"Object Oriented Programming", "Java", "Python", "Calculus", "History", "Writing"};
	
	Scanner input = new Scanner(System.in);
	
	//Student constructor
	public Student()
	{
		System.out.print("Enter students first name: ");
		this.first_name = input.nextLine().trim();
		
		System.out.print("Enter students last name: ");
		this.last_name = input.nextLine().trim();
		
		System.out.print("1. Freshman \n"
				+ "2. Sophomore \n"
				+ "3. Junior \n"
				+ "4. Senior \n"
				+ "Please enter the year you are going into: ");
		
		try
		{
			this.current_school_year = input.nextInt();
			input.nextLine();
		}
		catch(InputMismatchException e)
		{
			isNum = false;
			input.nextLine();
		}
		
		//Doesn't allow the user to enter anything other than a number between 1 and 4.
		while(current_school_year < 1 || current_school_year > 4 || isNum == false)
		{
			System.out.print("School year must be a number between 1 and 4, Please enter the school year you are going into: ");
			
			try
			{
				this.current_school_year = input.nextInt();
				isNum = true;
			}
			catch(InputMismatchException e)
			{
				isNum = false;
				input.nextLine();
			}
		}
		
		create_student_id();
	}
	
	//Creates a student ID
	private void create_student_id()
	{
		id++;
		this.student_ID = current_school_year + "" + id;
	}
	
	//Prompts user to enter courses the student would like to enroll in and adds them to a list of courses for that student. 
	public void enroll_student()
	{
		boolean isNotFinished = true;
		
		while(isNotFinished)
		{
			System.out.println("Enter course from the list of available courses to enroll (q to quit) ");
			
			for(int i = 0; i < available_courses.length; i++)
			{
				System.out.println(i + 1 + ". " + available_courses[i]);
			}
			
			System.out.print("Course: ");
			String course = input.nextLine().trim().toLowerCase();
			Courses courseName = new Courses(course);
			String nameOfCourse = courseName.getName().substring(0, 1).toUpperCase() + courseName.getName().substring(1);
			
			if(!course.equals("q"))
			{
				
				for(int i = 0; i < available_courses.length; i++)
				{

					if(course.equalsIgnoreCase(available_courses[i]))
					{
						if(student_courses.contains(nameOfCourse))
						{
							System.out.println("You are already enrolled in that course");
							break;
						}
						else
						{
							student_courses.add(nameOfCourse);
							professors.add(courseName.getProfessorName());
							balance += course_cost;
							break;
						}
					}
				}
				
				if(!student_courses.contains(nameOfCourse))
				{
					System.out.println("That course is not available!");
				}
				
			}
			else
			{
				isNotFinished = false;
			}
			
		}
	}
	
	//Asks the user how much they would like to pay toward their tuition and prints the payment that was made.  
	public void pay_tuition()
	{
		int payment = 0;
		
		System.out.println("Your balance is: $" + balance);
		System.out.print("How much would you like to pay toward your tuition? ");
		
		try
		{
			payment = input.nextInt();
		}
		catch(InputMismatchException e)
		{
			isNum = false;
			input.nextLine();
		}
		
		while(payment < 0 || payment > balance || isNum == false)
		{
			if(payment < 0 || isNum == false)
			{
				System.out.println("Payment must be a number greater than 0");
				System.out.print("How much would you like to pay toward your tuition? ");
			}
			else if(payment > balance)
			{
				System.out.println("Payment shouldn't be greater than your balance of $" + balance);
				System.out.print("How much would you like to pay toward your tuition? ");
			}
			
			try
			{
				payment = input.nextInt();
				isNum = true;
			}
			catch(InputMismatchException e)
			{
				isNum = false;
				input.nextLine();
			}
		}
		
		if(payment > 0)
		{
			balance -= payment;
			System.out.println("Your payment of $" + payment + " was recieved.");
		}
	}
	
	
	//Looks at the current student and prints all the information pertaining to his/her enrollment. 
	public void student_info()
	{
		String courses = "";
		//int index = 1;
		
		for(int i = 0; i < student_courses.size(); i++)
		{
			courses += "\n" + " " + (i + 1) + ". Course: " + student_courses.get(i) + "\n" + "    Professor: " + professors.get(i);
		}
		
		System.out.println("Student Name: " + first_name + " " + last_name +
				"\nStudent ID: " + student_ID +  
				"\nYear Attending: " + current_school_year +
				"\nCourses: " + courses + 
				"\nBalance: $" + balance);
	}
}

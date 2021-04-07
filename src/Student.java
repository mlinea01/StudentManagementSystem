import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student 
{
	private String firstName;
	private String lastName;
	private int currentSchoolYear;
	private int balance = 0;
	private final int courseCost = 600;
	private boolean isNum = true;
	private String courses = "";
	
	ArrayList<String> studentCourses = new ArrayList<>();
	ArrayList<String> professors = new ArrayList<>();
	
	//Available courses and assignments for each course.
	String[] availableCourses = {"Object Oriented Programming", "Java", "Python", "Calculus", "History", "Writing"};
	String[] OOPAssignments = {"Discussions: 3, Total Points: 50", "Quizzes: 3, Total Points: 100", "Milestones: 3, Total Points: 300", "Final Project: 1, Total Points: 500"};
	String[] javaAssignments = {"Discussions: 3, Total Points 50", "Exercises: 5, Total Points: 100", "Tests: 2, Total Points 100", "Final Project: 1, Total Points: 300"};
	String[] pythonAssignments = {"Discussions: 3, Total Points 50", "Exercises: 3, Total Points: 50", "Quizzes: 3, Total Points 150", "Tests: 1, Total Points 100", "Final Project: 1, Total Points: 500"};
	String[] calculusAssignments = {"Discussions: 2, Total Points: 25", "Practice Problems: 100, Total Points: 200", "Quizzes: 4: Total Points: 200", "Tests: 2, Total Points: 200", "Final Exam: 1, Total Points: 300"};
	String[] historyAssignments = {"Discussion: 6, Total Points 100", "Papers: 2, Total Points 200", "Quizzes: 3, Total Points 150", "Final Research Paper: 1, Total Points: 300"};
	String[] writingAssignments = {"Discussions: 4, total points 100", "Writing Fundamental Exercises: 3, Total Points 75", "Quizzes: 3, Total Points: 100", "Research Paper: 1, Total Points: 400"};
	
	Scanner input = new Scanner(System.in);
	
	//Student constructor
	public Student()
	{
		System.out.print("Enter students first name: ");
		this.firstName = input.nextLine().trim();
		
		System.out.print("Enter students last name: ");
		this.lastName = input.nextLine().trim();
		
		System.out.print("1. Freshman \n"
				+ "2. Sophomore \n"
				+ "3. Junior \n"
				+ "4. Senior \n"
				+ "Please enter the year you are going into: ");
		
		//Gets user input for the current school year and makes sure the user is typing a number that is between 1 and 4. 
		try
		{
			this.currentSchoolYear = input.nextInt();
			input.nextLine();
		}
		catch(InputMismatchException e)
		{
			isNum = false;
			input.nextLine();
		}
		
		//Doesn't allow the user to enter anything other than a number between 1 and 4.
		while(currentSchoolYear < 1 || currentSchoolYear > 4 || isNum == false)
		{
			System.out.print("School year must be a number between 1 and 4, Please enter the school year you are going into: ");
			
			try
			{
				this.currentSchoolYear = input.nextInt();
				input.nextLine();
				isNum = true;
			}
			catch(InputMismatchException e)
			{
				isNum = false;
				input.nextLine();
			}
		}
	}
	
	//Getter used to get the available balance for each student
	public int getBalance()
	{
		return this.balance;
	}
	
	//Getter used to get the courses each student is enrolled in. 
	public String getCourses()
	{
		return this.courses;
	}
	
	//Getter used to get the year each student is enrolled in.  
	public int getYearAttending()
	{
		return this.currentSchoolYear;
	}
	
	//Getter used to get the first name of the student
	public String getFirstName()
	{
		return this.firstName;
	}
	
	//Getter used to get the last name of the student
	public String getLastName()
	{
		return this.lastName;
	}
	
	//Prompts user to enter courses the student would like to enroll in and adds them to a list of courses for that student. 
	public void enrollStudent()
	{
		boolean isNotFinished = true;
		
		while(isNotFinished)
		{
			System.out.println("\nEnter course from the list of available courses to enroll (q to quit) ");
			
			for(int i = 0; i < availableCourses.length; i++)
			{
				System.out.println(i + 1 + ". " + availableCourses[i]);
			}
			
			/*
			 * These variables are used to grab the course the student would like to enroll in.
			 * The course variable is trimmed to avoid whitespace issues and is set to lowercase to equal the text in the switch case in the Course class which will allow me to get professor names.
			 * The Course object, courseName, is created which takes the string variable "course" as an argument so that we can get the professor name for each course. 
			 * The string variable nameOfCourse sets the first letter of each course typed in to uppercase so that it matches items in the list when users type them in. 
			 * 
			 */
			System.out.print("Course: ");
			String course = input.nextLine().trim().toLowerCase();
			Courses courseName = new Courses(course);
			String nameOfCourse = courseName.getName().substring(0, 1).toUpperCase() + courseName.getName().substring(1);
			int viewAssignment = 0;
			
			if(!course.equals("q"))
			{
				
				for(int i = 0; i < availableCourses.length; i++)
				{
					//Checks to see if the course entered is in the available courses. If yes, it alerts the user that they are already enrolled in that course.
					if(course.equalsIgnoreCase(availableCourses[i]))
					{
						if(studentCourses.contains(nameOfCourse))
						{
							System.out.println("You are already enrolled in that course");
							break;
						}
						else
						{
							studentCourses.add(nameOfCourse);
							professors.add(courseName.getProfessorName());
							courses += "\n" + " " + (studentCourses.size()) + ". Course: " + nameOfCourse + "\n" + " Professor: " + courseName.getProfessorName();
							balance += courseCost;
							
							System.out.println("Would you like to view the assignments for this course? (1. Yes, 2. No)");
							
							//Checks to see if the user entered a number and if that number is either 1 or 2.  
							try
							{
								viewAssignment = input.nextInt();
								input.nextLine();
							}
							catch(InputMismatchException e)
							{
								isNum = false;
								input.nextLine();
							}
							
							while(viewAssignment < 1 || viewAssignment > 2 || isNum == false)
							{
								System.out.println("Must choose 1 for Yes or 2 for No");
								
								try
								{
									viewAssignment = input.nextInt();
									input.nextLine();
									isNum = true;
								}
								catch(InputMismatchException e)
								{
									isNum = false;
									input.nextLine();
								}
							}
							
							//If user enters a 1 to view assignments for the course, the displayCourseAssignments function is called and displays the assignments to the screen. 
							if(viewAssignment == 1)
							{
								displayCourseAssignments(nameOfCourse);
							}
							
							break;
						}
					}
				}
				
				if(!studentCourses.contains(nameOfCourse))
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
	
	//Function that prompts user to pay some money toward their tuition.  
	public void payTuition()
	{
		int payment = 0;
		
		System.out.println("Your balance is: $" + balance);
		System.out.print("How much would you like to pay toward your tuition? ");
		
		//Makes sure the user enters a payment greater than 0 but less than the total balance due. 
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
	
	//Function to display assignments depending on the course the student enrolls in. 
	public void displayCourseAssignments(String nameOfCourse)
	{
		
		switch(nameOfCourse)
		{
			case "Object oriented programming":
				for(int i = 0; i < OOPAssignments.length; i++)
				{
					System.out.println(OOPAssignments[i]);
				}
				break;
			case "Java":
				for(int i = 0; i < javaAssignments.length; i++)
				{
					System.out.println(javaAssignments[i]);
				}
				break;
			case "Python":
				for(int i = 0; i < pythonAssignments.length; i++)
				{
					System.out.println(pythonAssignments[i]);
				}
				break;
			case "Calculus":
				for(int i = 0; i < calculusAssignments.length; i++)
				{
					System.out.println(calculusAssignments[i]);
				}
				break;
			case "History":
				for(int i = 0; i < historyAssignments.length; i++)
				{
					System.out.println(historyAssignments[i]);
				}
				break;
			case "Writing":
				for(int i = 0; i < writingAssignments.length; i++)
				{
					System.out.println(writingAssignments[i]);
				}
				break;
		}
	}
}

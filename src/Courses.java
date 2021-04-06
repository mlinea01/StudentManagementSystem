

public class Courses {

	public String courseName;
	public String professorName;
	
	public Courses(String nameOfCourse)
	{
		this.courseName = nameOfCourse;
		
		switch(courseName)
		{
			case "java":
				professorName = "Mr. Linea";
				break;
			case "python":
				professorName = "Mrs. Adams";
				break;
			case "object oriented programming":
				professorName = "Mr. Smith";
				break;
			case "calculus":
				professorName = "Mr. Johnson";
				break;
			case "history":
				professorName = "Mrs. White";
				break;
			case "writing":
				professorName = "Mrs. Kane";
				break;
		}
	}
	
	public String getName()
	{
		return courseName;
	}
	
	public String getProfessorName()
	{
		return professorName;
	}
	
}

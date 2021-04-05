import java.util.Comparator;

public class SortObjects implements Comparator<Student>
{

	/*
	 * Starts by sorting objects by last name.
	 * If two objects have the same last name, they will be sorted by first name. 
	 */
	@Override
	public int compare(Student student1, Student student2) 
	{
		int sortByLastName = student1.getLastName().compareTo(student2.getLastName());
		int sortByFirstName = student1.getFirstName().compareTo(student2.getFirstName());
		
		if(sortByLastName != 0)
		{
			return sortByLastName;
		}
		
		return sortByFirstName;
	}

}

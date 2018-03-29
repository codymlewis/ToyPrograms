public class Course
{
    private String courseName;      //object to store the course name
    private String courseNo;        //object to store the course number
    private char courseGrade;       //variable to store the grade
    private int courseCredits;      //variable to store the
                                    //course credits

   		//Default Constructor
   		//The object is initialized to the default values
   		//parameters.
   		//Postcondition: courseName = ""; courseNo = ""; 
   		//               courseGrade = '*'; courseCredits = 0;
    public Course()
    {
 	    courseName = "";
	    courseNo = "";
        courseGrade = '*';
 	    courseCredits = 0;
    }

		//Constructor
		//The object is initialized according to the parameters.
		//Postcondition: courseName = cName; courseNo = cNo;
  		//     courseGrade = grade; courseCredits = credits;
    public Course(String cName, String cNo,
                      char grade, int credits)
    {
        courseName = cName;
	    courseNo = cNo;
        courseGrade = grade;
 	    courseCredits = credits;
    }

	   	//Method to set the course information
  	    //The course information is set according to the
        //incoming parameters.
	  	//Postcondition: courseName = cName; courseNo = cNo;
  	    //     courseGrade = grade; courseCredits = credits;
     public void setCourseInfo(String cName, String cNo,
  			                    char grade, int credits)
    {
 	     courseName = cName;
 	     courseNo = cNo;
         courseGrade = grade;
 	     courseCredits = credits;
    }

	 	//Method to set the course Name
	  	//Postcondition: courseName = cName;
    public void setCourseName(String cName)
    {
    	courseName = cName;
    }

	  	//Method to set the course Number
	  	//Postcondition: courseNo = cNo;
    public void setCourseNumber(String cNo)
    {
        courseNo = cNo;
    }

	  	//Method to set the course Grade
	  	//Postcondition: courseGrade = grade;
    public void setCourseGrade(char grade)
    {
          courseGrade = grade;
    }

	  	//Method to set the course credits
	 	//Postcondition: courseCredits = credits;
    public void setCourseCredits(int credits)
    {
 	      courseCredits = credits;
    }

	 	//Method to return the course information as a string
      	//Postcondition: The course no, course name,
      	//               course credits, and grade is returned
     	//               as a string
    public String getCourseInfo(boolean isGrade)
    {
	    String str;

        str = courseNo + "\t  "
	         + courseName + "\t\t"
	         + courseCredits + "\t";

	    if(isGrade)
            str = str + courseGrade;
	    else
            str = str + "***";

        return str;
    }

	   	//Method to print the course information
  	   	//This method prints the course information on the
    	//screen. Furthermore, if the boolean parameter isGrade is
    	//true, the grade is shown, otherwise three stars
      	//are printed.
    public void print(boolean isGrade)
    {
	      System.out.print(courseNo + "\t  "
	                     + courseName + "\t\t"
	                     + courseCredits + "\t");

	      if(isGrade)
		        System.out.println(courseGrade);
	      else
		        System.out.println("***");
    }

        //Method to return the course name
        //Postcondition:  The value of courseName is returned;
    public String getCourseName()
    {
	      return courseName;
    }

        //Method to return the course number
        //Postcondition:  The value of courseNo is returned;
    public String getCourseNumber()
    {
	        return courseNo;
    }

        //Method to return the credit hours
        //The value of the private data member courseCredits
        //is returned.
    public int getCredits()
    {
          return courseCredits;
    }

	      //Method to return the grade for the course
	      //The value of the private data member courseGrade
	      //is returned.
    public char getGrade()
    {
 	        return courseGrade;
    }

    public void copyCourseInfo(Course otherCourse)
    {
        courseName = otherCourse.courseName;
	    courseNo = otherCourse.courseNo;
        courseGrade = otherCourse.courseGrade;
 	    courseCredits = otherCourse.courseCredits;
    }
}

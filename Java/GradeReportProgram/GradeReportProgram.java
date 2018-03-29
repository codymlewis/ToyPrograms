import java.util.*;

public class GradeReportProgram 
{
    static final int maxNumberOfStudents = 10;
    static Student[] studentList = new Student[maxNumberOfStudents];
    static int noOfStudents;
    static double tuitionRate;
    static int displayedStudentIndex = 0;
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) 
    {
        
         for(int i = 0; i < maxNumberOfStudents; i++)
             studentList[i] = new Student();

          System.out.print("number of students = ");
	  noOfStudents = console.nextInt();  	//get the number of students
          System.out.print("Tuition rate = ");
          tuitionRate = console.nextDouble();	//get the tuition rate
          getStudentData();
          int choice=0;
          while(choice!=9) {
              System.out.println("student: ");
              choice = console.nextInt();
              displayGradeReports(choice);
          }
    }

    public static void getStudentData() 
    {
        String fName;	    //variable to store the first name
        String lName;	    //variable to store the last name
        int ID;		        //variable to store the student ID
        int noOfCourses;   //variable to store the number of courses
        char isPaid;	    //variable to store Y/N; that is,
                          //is tuition paid?
        boolean isTuitionPaid;	//variable to store true/false

        String cName;	//variable to store the course name
        String cNo;	//variable to store the course number
        int credits;	//variable to store the course credit hours
        char grade;	//variable to store the course grade

        int count;	//loop control variable
        int i;		//loop control variable

        Course[] courses = new Course[6]; //array of objects to
                                          //store course information

        for(i = 0; i < 6; i++)
            courses[i] = new Course();

        for(count = 0; count < noOfStudents; count++)
        {
            System.out.print("last name = ");fName = console.next();
            System.out.print("first name = ");lName = console.next();
            System.out.print("ID = ");ID = console.nextInt();
            System.out.print("is paid? (Yes or No) ");isPaid = console.next().charAt(0);

            if(isPaid == 'Y') isTuitionPaid = true;
            else              isTuitionPaid = false;

            System.out.print("no of courses = ");noOfCourses = console.nextInt();

            for(i = 0; i < noOfCourses; i++) {
                      System.out.print("course name = ");cName = console.next();
                      System.out.print("course number = ");cNo = console.next();
                      System.out.print("credits = ");credits = console.nextInt();
                      System.out.print("grade = ");grade = console.next().charAt(0);	
	              courses[i].setCourseInfo(cName, cNo, grade, credits); 
              } 
	          studentList[count].setInfo(fName, lName, ID, noOfCourses, isTuitionPaid, courses);           //Step 5
        } //end for
    }

    public static void displayGradeReports(int stNo)
    {
        displayedStudentIndex = stNo;
        String CourseListing = "";

        boolean isPaid = studentList[stNo].getIsTuitionPaid();

        System.out.println("name: "+studentList[stNo].getName());
        System.out.println("ID: "+studentList[stNo].getStudentId());
        System.out.println("noCourses: "+studentList[stNo].getNumberOfCourses());
        System.out.println("hours: "+studentList[stNo].getHoursEnrolled());

        if(isPaid) System.out.println(("GPA: "+String.format("%.2f",studentList[stNo].getGpa())));
        else       System.out.println(""+"****");

        for(int count = 0; count < studentList[stNo].getNumberOfCourses(); count++)
        {
            if(count == 0)
              CourseListing = studentList[stNo].getCourse(count).getCourseInfo(isPaid);
            else
            {
                CourseListing = CourseListing + "\n" + studentList[stNo].getCourse(count).getCourseInfo(isPaid);
            }
        }

        if(!isPaid)
            CourseListing = CourseListing + "\n"
                         + "*** Grades are being held for "
  	                     + "not paying the tuition. ***\n"
	                       + "Amount Due: $"
                         + String.format("%.2f",studentList[stNo].billingAmount(tuitionRate));
        System.out.println(CourseListing);
    }
}


public class Student
{
     private String firstName;  //store the first name
     private String lastName;   //store the last name
     private int sId;               //variable to store the student ID
     private int numberOfCourses;   //variable to store the number of courses
     private boolean isTuitionPaid; //variable to indicate if the tuition is paid
     private Course [] coursesEnrolled; //array to store the courses

    //Default constructor
    //Postcondition: Instance variables are initialized
    public Student()
    {
        firstName = "";
        lastName = "";
        numberOfCourses = 0;
        sId = 0;
        isTuitionPaid = false;
        coursesEnrolled = new Course[6];
        for(int i = 0; i < 6; i++) coursesEnrolled[i] = new Course();
    }

    //Method to set a student's information
    //The instance variables are set according
    //to the parameters
     public void setInfo(String fName, String lName, int ID,int nOfCourses, boolean isTPaid, Course[] courses)
     {
         int i;

        firstName = fName;
        lastName = lName;
        
         sId = ID;                  //set the student ID
         isTuitionPaid = isTPaid;       //set isTuitionPaid
         numberOfCourses = nOfCourses ; //set the number of courses

         for(i = 0; i < numberOfCourses; i++) //set the array
             coursesEnrolled[i].copyCourseInfo(courses[i]);

         sortCourses();         //sort the array coursesEnrolled
     }
    public void setName(String first, String last)
    {
          firstName = first;
          lastName = last;
    }

      //Method to return the firstName
        //Postcondition: the value of firstName is returned
    public String getFirstName()
    {
        return firstName;
    }

      //Method to return the lastName
        //Postcondition: the value of lastName is returned
    public String getLastName()
    {
        return lastName;
    }

        //Method to set a student ID
        //Postcondition : sId = ID;
     public void setStudentId(int ID)
     {
         sId = ID;
     }

          //Method to determine whether tuition is paid
        //Postcondition : isTuitionPaid = isTPaid;
    public void setIsTuitionPaid(boolean isTPaid)
    {
         isTuitionPaid = isTPaid;
    }

        //Method to set number of courses taken
        //Postcondition : numberOfCourses = nOfCourses;
    public void setNumberOfCourses(int nOfCourses)
    {
         numberOfCourses = nOfCourses ;
    }

        //Method to set courses enrolled
        //Postcondition : array courses is copied into the array
        //       coursesEnrolled and the array coursesEnrolled
        //       is sorted.
     public void setCoursesEnrolled(Course[] courses)
     {
         for(int i = 0; i < numberOfCourses; i++)
             coursesEnrolled[i].copyCourseInfo(courses[i]);

         sortCourses();
     }

        //Method to print a student's grade report
    public void print(double tuitionRate)
    {
        int i;

        System.out.println("Student Name: " + firstName + " " + lastName);     //Step 1

        System.out.println("Student ID: " + sId);   //Step 2

        System.out.println("Number of courses enrolled: " + numberOfCourses);       //Step 3
        System.out.println("Course No Course Name"+ "\t    Credits"+ "   Grade");           //Step 4

        for(i = 0; i < numberOfCourses; i++)        //Step 5
            coursesEnrolled[i].print(isTuitionPaid);

        System.out.println();
        System.out.println("Total number of credit hours: "+ String.format("%.2f",getHoursEnrolled()));  //Step 6

        if(isTuitionPaid)                   //Step 8
           System.out.println("Midsemester GPA: " + String.format("%.2f",getGpa()));
        else
        {
            System.out.println("*** Grades are being held for " + "not paying the tuition. ***");
            System.out.println("Amount Due: $" + String.format("%.2f",billingAmount(tuitionRate)));
        }

            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
    }

        //Method to return a String with value
        //firstName + " " + lastName
        //Postcondition: returns firstName concatenated
        //      with a space and lasName
     public String getName()
    {
        return(getFirstName() + " " + getLastName());
    }

        //Method to get a student ID
        //Postcondition : sId is returned
     public int getStudentId()
     {
         return sId;
     }

        //Method to determine if the tuition is paid
        //Postcondition : isTuitionPaid is returned
     public boolean getIsTuitionPaid()
     {
         return isTuitionPaid;
     }

        //Method to get the number of courses taken
        //Postcondition : numberOfCourses is returned
     public int getNumberOfCourses()
     {
         return numberOfCourses;
     }

          //Method to get a copy of a course taken
          //Postcondition :  a copy of coursesEnrolled[i] is returned
     public Course getCourse(int i)
     {
         Course temp = new Course();

         temp.copyCourseInfo(coursesEnrolled[i]);

         return temp;
     }

        //Method to return the credit hours in which a
        //student is enrolled
        //Postcondition: Total credits are calculated and returned
    public int getHoursEnrolled()
    {
          int totalCredits = 0;
          int i;

          for(i = 0; i < numberOfCourses; i++)
              totalCredits += coursesEnrolled[i].getCredits();

          return totalCredits;
    }

        //Method to return the grade point average
       //Postcondition: GPA is calculated and returned
    public double getGpa()
    {
       int i;
       double sum = 0.0;

       for(i = 0; i < numberOfCourses; i++)
       {
            switch(coursesEnrolled[i].getGrade())
            {
            case 'A': sum += coursesEnrolled[i].getCredits() * 4;
                      break;
            case 'B': sum += coursesEnrolled[i].getCredits() * 3;
                      break;
            case 'C': sum += coursesEnrolled[i].getCredits() * 2;
                      break;
            case 'D': sum += coursesEnrolled[i].getCredits() * 1;
                      break;
            case 'F': sum += coursesEnrolled[i].getCredits() * 0;
                      break;
            default: System.out.println("Invalid Course Grade");
            }
       }

       return sum / getHoursEnrolled();
    }

        //Method to return the tuition fees
        //Postcondition: Billing amount is calculated and returned
    public double billingAmount(double tuitionRate)
    {
          return tuitionRate * getHoursEnrolled();
    }

        //Method to sort the courses
        //This method sorts the array coursesEnrolled
    private void sortCourses()
    {
       int i,j;
       int minIndex;
       Course temp = new Course();  //variable to swap data
       String course1;
       String course2;

       for(i = 0; i < numberOfCourses - 1; i++)
       {
              minIndex = i;

              for(j = i + 1; j < numberOfCourses; j++)
              {
                          //get course numbers
                  course1 =
                        coursesEnrolled[minIndex].getCourseNumber();
                  course2 = coursesEnrolled[j].getCourseNumber();

                if(course1.compareTo(course2) > 0)
                        minIndex = j;
              }//end for

              temp.copyCourseInfo(coursesEnrolled[minIndex]);
              coursesEnrolled[minIndex].copyCourseInfo(coursesEnrolled[i]);
              coursesEnrolled[i].copyCourseInfo(temp);
       }//end for
    }//end sortCourses
}


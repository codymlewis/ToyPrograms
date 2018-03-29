/* Student.java
Manage a student's name and three test scores.
*/
public class Student {

    //Instance variables
    //Each student object will have a name and three test scores
    private String name;             //Student name
    private int test1;               //Score on test 1
    private int test2;               //Score on test 2
    private int test3;               //Score on test 3

    //Constructor method

    public Student()
    {
    //Initialize a new student's name to the empty string and his test
    //scores to zero.
        name = "";
        test1 = 0;
        test2 = 0;
        test3 = 0;
    }
    
    //Other methods

    public void setName (String nm)
    {
    //Set a student's name
    // Preconditions  -- nm is not empty
    // Postconditions -- name has been set to nm
        name = nm;
    }
    
    public String getName ()
    {
    //Get a student's name
    //  Preconditions  -- none
    //  Postconditions -- returns the name
        return name;
    }
  
    public void setScore (int i, int score)
    {
    //Set the score on the indicated test
    //  Preconditions  -- 1 <= i <= 3 
    //                 -- 0 <= score <= 100
    //  Postconditions -- test i has been set to score
        if      (i == 1) test1 = score;
        else if (i == 2) test2 = score;
        else             test3 = score;
    }

    public int getScore (int i)
    {
    //Get the score on the indicated test
    //  Preconditions  -- none
    //  Postconditions -- returns the score on test i
        if      (i == 1) return test1;
        else if (i == 2) return test2;
        else             return test3;
    }
   
    public int getAverage()
    {
    //Compute and return a student's average
    //  Preconditions  -- none
    //  Postconditions -- returns the average of the test scores
        int average;
        average = (int) Math.round((test1 + test2 + test3) / 3.0);
        return average;
    }
  
    //  Method getHighScore
    //  Compute and return a student's highest score
    //  Preconditions  -- none
    //  Postconditions -- returns the highest test score
    public int getHighScore()
    {
      int highScore;
      if (test1>test2 && test1>test3)
      highScore = (int) test1;
      else if (test2>test1 && test2>test3)
      highScore = (int) test2;
      else
      highScore = (int) test3;
      
      return highScore;
        
    }

}  





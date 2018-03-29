
/**
 * Write a description of class studentInterface here.
 *
 * @author Cody Lewis
 * @version 06-04-2017
 */
import java.util.*;
public class studentInterface
{

    static int aMark1,aMark2,aMark3,eMark1,eMark2;
    static Subject subject1 = new Subject();
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.print("Welcome to the student interface \n please enter the marks for Assignment 1: ");
        aMark1 = console.nextInt();
        System.out.print("Please enter the marks for Assignment 2: ");
        aMark2 = console.nextInt();
        System.out.print("Please enter the marks for Assignment 3: ");
        aMark3 = console.nextInt();
        AssignMarks(subject1,aMark1,aMark2,aMark3);
        System.out.print("Please enter the marks for Exam 1: ");
        eMark1 = console.nextInt();
        System.out.print("Please enter the marks for Exam 2: ");
        eMark2 = console.nextInt();
        ExamMarks(subject1,eMark1,eMark2);
        System.out.print("The percantage average of the marks is "); System.out.print(subject1.getTest());
        
        
        
        
        
    }
    private static void AssignMarks(Subject subject1,int aMark1,int aMark2,int aMark3){
        subject1.setAssign(aMark1,aMark2,aMark3);
    }
    private static void ExamMarks(Subject subject1,int eMark1,int eMark2){
        subject1.setExams(eMark1,eMark2);
    }
}

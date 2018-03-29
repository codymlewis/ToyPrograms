import java.util.*;

public class TestStudent
{
    public static void main (String[] args) 
    {
       Scanner console = new Scanner(System.in);

        Student student1;
        Student student2;
        String s;
        int t;

        student1 = new Student();
        student2 = new Student();

        System.out.println("name:");
        s = console.next();
        student1.setName (s);
        System.out.println("mark:");
        t = console.nextInt();
        student1.setScore (1, t);
        System.out.println("mark:");        
        student1.setScore (2, console.nextInt());
        System.out.println("mark:");        
        student1.setScore (3, console.nextInt());

        System.out.println("name:");
        student2.setName (console.next());
        System.out.println("mark:");
        student2.setScore (1, console.nextInt());
        System.out.println("mark:");
        student2.setScore (2, console.nextInt());
        System.out.println("mark:");
        student2.setScore (3, console.nextInt());
        
        System.out.println("average of student 1 = "+student1.getAverage());
        System.out.println("highest score of student 1 = "+student1.getHighScore());
        System.out.println("average of student 2 = "+student2.getAverage());
        System.out.println("highest score of student 2 = "+student2.getHighScore());
       
    }
}

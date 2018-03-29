public class Subject
{
public static void main (String[] args) 
{        
        String[] name = new String[4];
        int[] grade = new int[4];
        
        name[0] ="cesar";     grade[0]=2;
        name[1] ="joshua";    grade[1]=2;
        name[2] ="joe";       grade[2]=2;
        name[3] ="michael";   grade[3]=2;
    
        int contact = 8;
        
        Tutor myTutor = new Tutor(name,grade,contact);
        myTutor.printNames();
        myTutor.printGrades();
        myTutor.printContactHours();
        
        System.out.println("\n\n\n\n=========================================================");
        System.out.println("I am changing the name of tutor and \ntheir" +" contact hours");
                            
        name[0] ="Domi";  grade[0] = 10;
        name[1]= "Linda"; grade[1] = 10;
        name[2]= "Sofi";  grade[2] = 10;
        name[2]= "Sasha"; grade[2] = 10;
        contact = 999;
        
        System.out.println("\n\n\n\n=========================================================");
        System.out.println("Now, lets see what happens after we make these changes");
        
        System.out.println("\n\n\n\n=========================================================");
        myTutor.printNames();
        myTutor.printGrades();
        myTutor.printContactHours();
    }
}

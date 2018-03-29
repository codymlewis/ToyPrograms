
/**
 * Write a description of class Subject here.
 *
 * @author Cody Lewis 
 * @SN. 3283349
 * @version 06-04-2017
 */
public class Subject{
    // instance variables - replace the example below with your own
    private String name;
    private int assign1,assign2,assign3,exam1,exam2,average;

    /**
     * Constructor for objects of class Subject
     */
    public Subject(){
        // initialise instance variables
        name = "No name yet";
        assign1 = 0;
        assign2 = 0;
        assign3 = 0;
        exam1 = 0;
        exam2 = 0;
    }
    public int getAverage(){
        average = ((assign1/10)+(assign2/10)+(assign3/10)+(exam1/35)+(exam2/35))/5;
        average *= 100;
        return average;
    }
    public int getTest(){
        return assign1;
    }
    public void setAssign(int aMark1,int aMark2,int aMark3){
        assign1 = aMark1;
        assign2 = aMark2;
        assign3 = aMark3;
    }
    public void setExams(int eMark1,int eMark2){
        exam1 = eMark1;
        exam2 = eMark2;
    }

}

import javax.swing.*;


public class MyFirstGUIProgram {
  private String studentName;
  
  public MyFirstGUIProgram(String name)
  {
   studentName = name; 
  }
  
  public void show()
  {
   String message = "Hello "+ "Won't you tell me your name?\n" + "My name is " + studentName + ", by the way.";
   
   JOptionPane.showInputDialog("message");
   
   JOptionPane.showMessageDialog(null, message, "Greetings from " + studentName, JOptionPane.QUESTION_MESSAGE);
  }
  
  public static void main(String[] args)
  {
    MyFirstGUIProgram app = new MyFirstGUIProgram("Cody Lewis");
    app.show();
  }
  
}

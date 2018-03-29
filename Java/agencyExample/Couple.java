public class Couple
{
    private Person he,she;
    private String same = "";
//--------------------------------------------------------------------------------------
    public Couple()
    {
        he  = new Person();
        she = new Person();

        she.age=15;
    }
//--------------------------------------------------------------------------------------
    public void addData(int option, String newName, int newAge)
    {
       
        if (option==1){ setData1(she,newName,newAge);}
        else{           setData1(he,newName,newAge); }
        
    }
    private void setData1(Person p, String name, int age)
    {
            p.setName(name);
            p.setAge(age);
            
            if(p.equal(name,age) == true);
            same = " but the same person was entered";
           
    }
//--------------------------------------------------------------------------------------    
    public String test()
    {
       if (she.getAge() < he.getAge()) return("GOOD FOR "+he.getName()+"!" +same);
       else                            return("GOOD FOR "+she.getName()+"!"+same);
    }
}
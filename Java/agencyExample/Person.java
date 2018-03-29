public class Person 
{
    private String name;
    private static String qtdPerson;
    private String equal;
    private boolean sEqual;
     int age;
//--------------------------------------------------------------------------------------  
// constructor method
//--------------------------------------------------------------------------------------
    public Person()
    {
        name = "";
        age = 0;
        qtdPerson = "";
    }
    public Person(String newName, int newAge)
    {
        name = newName;
        age = newAge;
        
    }
    public boolean equal(String name, int age){
        if((String)name+age == qtdPerson)
        sEqual = true;
        qtdPerson = (String) name+age;
        return sEqual;
    }
//--------------------------------------------------------------------------------------    
    public void setName(String newName)
    {
        name = newName;
    }
//--------------------------------------------------------------------------------------    
    public String getName()
    {
        return name;
    }
//--------------------------------------------------------------------------------------    
    public void setAge(int newAge)
    {
        age = newAge;
    }
//--------------------------------------------------------------------------------------
    public int getAge()
    {
        return age;
    }
}
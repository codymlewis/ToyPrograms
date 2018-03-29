public class Couple
{
    final private int MAX = 5;
    private static Person[] he,she;
    private int total;
//--------------------------------------------------------------------------------------
    public Couple()
    {
        he  = new Person[MAX];
        she = new Person[MAX];
        total = 0;
    }
//--------------------------------------------------------------------------------------
    public void addData(String name1, int age1, String name2, int age2)
    {
        she[total] = new Person();
        he[total] = new Person();
        setData1(she[total],name1,age1);
        setData1(he[total],name2,age2);
        total++;
    }
    private void setData1(Person p, String name, int age)
    {
            p.setName(name);
            p.setAge(age);
    }
//--------------------------------------------------------------------------------------    
    public String test(int current)
    {
       if (current!=-1)
       {
        if (she[current].getAge() < he[current].getAge()) return("GOOD FOR "+he[current].getName()+"!");
        else                                              return("GOOD FOR "+she[current].getName()+"!");
       }
       return "error";
   }
   public int getLimit(){
	   return total-1;
   } 
    public String display(int current)
    {
        return("she: "+she[current].getName()+","+she[current].getAge()+"\nhe:"+he[current].getName()+","+he[current].getAge());
    }
    public String average()
    {
	double runningAge;
	double herAverage=0;
	double hisAverage=0;
	double counter=0;
	String output;

	for(int index=0; index<total; index++){
		runningAge = she[index].getAge();
		herAverage+=runningAge;
		counter++;
	}
	herAverage=herAverage/counter;
	counter=0;
	for(int index=0; index<total; index++){
		runningAge = he[index].getAge();
		hisAverage+=runningAge;
		counter++;
	}
	hisAverage=hisAverage/counter;
	
	output = "The average age of the females is "+herAverage+" the average age of the males is "+hisAverage;

	return output;
    }
}

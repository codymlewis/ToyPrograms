/**
 * Movie.java - Assignment 2
 * Author: Cody Lewis
 * Student No: 3283349
 * Last Modified: 12-05-2017
 * Description:
 * Class that stores the name, fileSize, duration and director of a movie.
 */

public class Movie{
	private String input;
	private String name,director,duration,fileSize;
	private int time,hours,minutes,size;
	private int GB,MB,index;
	private static int numberOfMovies = 0;

	//Constructor class for the movies, defines the default values for them
	public Movie(){
		name = "No Title set";
		director = "No director set";
		duration = "0";
		fileSize = "0";
		time = 0;
		size = 0;
		numberOfMovies++;
	}
	//Second constructor to set values for movies more immediately
	public Movie(String nm,String dir,String dur,String fS,String tm,String sz){
        	try{
        		name = fixName(nm);
      			director = fixName(dir);
        		duration = fixDur(dur);
             		fileSize = fixFS(fS);
                        time = convert(tm);
                        size = convert(sz);
                        numberOfMovies++;
                } catch(Exception e) {
                                        
               	}
	}
	//Get methods for each of the details of the movies: retieves assigned values
	public String getName(){
		return name;
	}
	public String getDirector(){
		return director;
	}
	public String getDuration(){
		return duration;
	}
	public String getFileSize(){
		return fileSize;
	}
	public int getTime(){
		return time;
	}
	public int getSize(){
		return size;
	}
	public int getNumberOfMovies(){
		return numberOfMovies;
	}
	public void removeMovie(){
		numberOfMovies--;
	}
        //Setter methods
	//Set methods for each of the details of the movies: assigns values
	public void setName(String input){
		input = fixName(input);
		name = input;
	}
	public void setDirector(String input){
		input = fixName(input);
		director = input;
	}
        //Methds to make inputs follow standards
	//Method to make strings of names consistant and output correctly
	public String fixName(String input){
		input = input.trim();		//Gets rid of unnessesary spacing
		input = input.toLowerCase();		//Gets rid of unnessessary capitals
		input = input.substring(0,1).toUpperCase()+input.substring(1);		//Fixes capitalization
		index = input.indexOf(" ");		//Finds spaces to make the letter proceeding a capital
		if(index>0 && index<input.length()-2)
			input = input.substring(0,index+1)+input.substring(index+1,index+2).toUpperCase()+input.substring(index+2);
		index = input.indexOf(".");		//Finds full stops to make the letter proceeding a capital
		if(index>0 && index<input.length()-2)
			input = input.substring(0,index+1)+input.substring(index+1,index+2).toUpperCase()+input.substring(index+2);
		return input;
	}
	//Recieves the input of time
	public void setDuration(String input){
		time = convert(input);
		duration = fixDur(input);
	}
	//Recieves the input of file size
	public void setFileSize(String input){
		size = convert(input);
		fileSize = fixFS(input);
	}
	//Make string into integers then based on whether it is a time value or not it will modify it to output in a nicer appearance
	public String fixDur(String input){
		int value = convert(input);
		int hours = value/60;
		int minutes = value%60;
		String timeyWimeyStuff = hours+" hours "+minutes+" minutes";
		return timeyWimeyStuff;
	}
	//changes the value sent to the method into a string and maikes it consistant and output correctly
	public String fixFS(String input){
		int value =  convert(input);
		if(value>=1000){
			int GB = value/1000;
			int MB = value%1000;
			String FS = GB+" Gigabytes "+MB+" Megabytes";
			return FS;
		} else{	String FS = value+" Megabytes"; return FS; }
	}
	//converts integers into strings and maikes it consistant and output correctly
	public int convert(String input){
        	try{
        		return Integer.parseInt(input);
       		} catch(NumberFormatException NFE){  //If there is an error in the input 0 will be returned instead
        		return 0;
                }
	}
}

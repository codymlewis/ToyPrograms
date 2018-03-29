/**
 * MovieDatabase.java - Assignment 2
 * Student No: 3283349
 * Last Modified: 26-05-2017
 * Description:
 * Stores all current movies in the system, modified to be inclusive of arrays.
 */
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class MovieDatabase{
	private Movie[] DB;
	private static int logicalSize;
                  
        //Default constructor
        public MovieDatabase(){
        	DB = new Movie[4];
                logicalSize = 0;
       	}
       	//Methods to open and save the movie database from a text file
        //Opens the specified file, and saves the information in the file as movies based on the file's contents
	public String openFile(String fileName){
                if(!fileName.contains(".txt"))
                	fileName = fileName+".txt";
		String nm=""; String dir="";
		String dur=""; String sz="";
		String line; String err = "";
		int index,type;     
		Scanner inputStream = null;
		
		try{
			inputStream = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e){
               		return "No previous data saved";
		}
		while(inputStream.hasNextLine()){
			line = inputStream.nextLine();
			if(line.equals(""))
				continue;
			index = indexSC(line);
			type = configureFileStream(line,index);
			switch(type){
				case 1: nm = line.substring(index,line.length()); break;
				case 2: dir = line.substring(index,line.length()); break;
				case 3: dur = line.substring(index,line.length()); break;
				case 4: sz = line.substring(index,line.length()); break;
				case 5: err = "Some of the file is illegable by the reader, unable to load all of "+fileName+"\n"; break;
			}
			if(nm.length()>0 && dir.length()>0 && dur.length()>0 && sz.length()>0){
				addMovie(nm,dir,sz,dur);
				nm=""; dir=""; dur=""; sz="";
				if(inputStream.hasNextLine())
					inputStream.nextLine();
			}
		}

		inputStream.close();
		return err+"File "+fileName+" was successfully opened, the database currently holds "+logicalSize+" movies";
	}
        //Indexs a semicolon
	private int indexSC(String line){
		return line.indexOf(":")+2;
	}
        //Returns a value to identify the string input from the file stream
	private int configureFileStream(String line,int index){
		if(index!=-1){
			String choice = line.substring(0,index);
                        switch (choice) {
                        	case "Movie title: ": return 1;
                                case "Director: ": return 2;
                                case "fileSize: ": return 3;
                                case "duration: ": return 4;
                                default: break;
                        }
			return 5;
                } else {	
			return 5;
		}
	}
        //Saves a text file of the movies in the specified file
	public String saveFile(String fileName){
                if(!fileName.contains(".txt"))
                	fileName = fileName+".txt";
		String list = "";
		for(int counter = 0; counter<logicalSize; counter++){
			list = list+"Movie title: "+DB[counter].getName()+"\nDirector: "
				+DB[counter].getDirector()+"\nfileSize: "+DB[counter].getSize()+"\nduration: "+DB[counter].getTime()+"\n\n";
		}
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(fileName);
		}
		catch(FileNotFoundException e){
			return "Error opening the file "+fileName;
		}
		outputStream.println(list);
		outputStream.close();

		return logicalSize+" movies were saved to "+fileName;
	} 
        //Getter methods
	public int getTotal(){
		return logicalSize;
	}
	public Movie getMovie(int index){
		Movie movie = DB[index];
		return movie;
	}
        //Movie listing methods
	//Returns a string of the movies held in the database, uses a loop to iterate through the movies that have been added
	public String listMovies(){
		String movieList = "";
		for(int counter = 0; counter<logicalSize; counter++){
				movieList = lister(movieList,counter);
		}
		return movieList;
	}
	//Returns a string of the movies by a specified director in the database
	public String listMovies(String name,int choice){
		name = DB[0].fixName(name);
		String movieList = "";
		String term;
		for(int counter = 0; counter<logicalSize; counter++){
			if(choice==1) term=DB[counter].getName();
			else term=DB[counter].getDirector();
			if(name.equals(term))
				movieList = lister(movieList,counter);
		}
		return movieList;
	}
	public String listMovies(int value,int choice){
		String movieList = "";
		int number;
		for(int counter = 0; counter<logicalSize; counter++){
			if(choice==3) number = DB[counter].getTime();
			else number = DB[counter].getSize();
			if(number<value)
				movieList = lister(movieList,counter);
		}
		if(movieList.equals(""))
			movieList = "none";
		return movieList;
	}
	//Lists movies in the sent order choice
	//sorts the list by comparing two values, checking if the first one is smaller
	//and if the boolean returns false the values are swapped
	public String listMoviesBy(int choice){
		String movieList = "";
		int[] order = new int[logicalSize];
		for(int counter = 0; counter<order.length; counter++)
			order[counter] = counter;
		for(int repeater = 1; repeater<logicalSize; repeater++){
			for(int counter = 0; counter<logicalSize-repeater; counter++){
				switch(choice){
					//orders the list alphabetically by name
					case 1: if(getAlphOrder(DB[order[counter]].getName(),DB[order[counter+1]].getName())==false){
							int temp = order[counter]; order[counter] = order[counter+1]; order[counter+1] = temp;
						}
						break;
					//orders the list alphabetically by director
					case 2: if(getAlphOrder(DB[order[counter]].getDirector(),DB[order[counter+1]].getDirector())==false){
							int temp = order[counter]; order[counter] = order[counter+1]; order[counter+1] = temp;	
						}
						break;
					//orders the list chronologically by time
					case 3: if(DB[order[counter]].getTime()>DB[order[counter+1]].getTime()){
							int temp = order[counter]; order[counter] = order[counter+1]; order[counter+1] = temp;	
						}
						break;
					//orders the list chronologically by time
					case 4: if(DB[order[counter]].getSize()>DB[order[counter+1]].getSize()){
							int temp = order[counter]; order[counter] = order[counter+1]; order[counter+1] = temp;	
						}
						break;
				}
			}
		}
		for(int counter = 0; counter<logicalSize; counter++)
			movieList = lister(movieList,order[counter]);
		return movieList;
	}
	//Returns string with the desired format for the lists
	private String lister(String list, int counter){
		list = list+"\n\nMovie "+(counter+1)+"\nTitle:     "+DB[counter].getName()+"\nDirector:  "
			+DB[counter].getDirector()+"\nDuration:  "+DB[counter].getDuration()+"\nFile size: "+DB[counter].getFileSize();
		return list;
	}
	//Return a boolean saying which word should be ordered first. True means the first word should go first.
	private boolean getAlphOrder(String word, String nextWord){
                                    if(word.length()>4  && word.contains("The"))        //Disregards the word "The"
                                        word = word.substring(4);
                                    if(nextWord.length()>4 && nextWord.contains("The"))
                                        nextWord = nextWord.substring(4);
		int minLength;
		if(word.length()<nextWord.length()) minLength = word.length();      //Figures out which word is the shortest
		else	minLength = nextWord.length();
		for(int counter = 0; counter<minLength; counter++){
			if(getAlphValue(word,counter)<getAlphValue(nextWord,counter))       //checks each character of the strings to find 
				return true;                                                //which has the higher decimal value
			else if(getAlphValue(word,counter)>getAlphValue(nextWord,counter))
				return false;
		}
		if(word.length()<nextWord.length()) return true;
		else	return false;
	}
	//Finds the decimal value of a requested character in a string		
	private int getAlphValue(String input,int index){
		char letter = input.charAt(index);
		int alphVal = Character.getNumericValue(letter);
		return alphVal;
	}
                  //Movie altering methods
	//Adds a new movie to the database and returns a message of its being added
	public String addMovie(String name,String dir,String dur,String size){
		String tm = dur,sz = size;
		if(logicalSize>0 && movieCheck(name,dir,tm,sz) == true){
			return "That movie was already added.\nNothing added, total movies still "+logicalSize;
		}
		else{
			DB[logicalSize] = new Movie(name,dir,dur,size,tm,sz);
			logicalSize++;
			if(logicalSize>3 && (logicalSize%4)==0)	//Automatically resizes the array based on specified conditions
				DB = resizeDB(DB,logicalSize+4);
			return "Movie: "+logicalSize+" was successfully added.\nThe total of movies in the database is now "+logicalSize;
		}
	}
	//returns true if a recently added movie has already been added
	private boolean movieCheck(String name,String dir,String tm,String sz){
            
		name = DB[0].fixName(name); dir = DB[0].fixName(dir);
		int time = DB[0].convert(tm); int size = DB[0].convert(sz);
		for(int counter=0; counter<logicalSize; counter++){
			String tempName = DB[counter].getName();
			String tempDir = DB[counter].getDirector();
			int tempTime = DB[counter].getTime();
			int tempSize = DB[counter].getSize();
			if(tempName.equals(name) && tempDir.equals(dir) && tempTime == time && tempSize == size)
				return true;
		}
		return false;
	}
	//method to modify information in a movie
	public String editMovie(int movie,int edit,String input){
		switch(edit){
			case 1: DB[movie].setName(input); break;
			case 2: DB[movie].setDirector(input); break;
			case 3: DB[movie].setDuration(input); break;
			case 4: DB[movie].setFileSize(input); break;
		}
		String message = "The new edit is: "; message = lister(message,movie);
		return message;
	}
	//Deletes the movie that is inputed into the method
	public String delete(int input){
		String info = "";
		if(input>=0 && input<DB.length){
			if(input!=(logicalSize-1)){
				for(int counter = input; counter<logicalSize-1; counter++){
					info = DB[counter+1].getName();
					DB[counter].setName(info);
					info = DB[counter+1].getDirector();
					DB[counter].setDirector(info);		
					info =""+DB[counter+1].getTime();	//Causes conversion error when there is no double quotation
					DB[counter].setDuration(info);
					info =""+DB[counter+1].getSize();
					DB[counter].setFileSize(info);
					
				}
			}
			DB[0].removeMovie();
			logicalSize--;
			if(logicalSize>3 && (logicalSize%4)==0 && logicalSize<DB.length/2)
				DB = resizeDB(DB,DB.length/2);
			info = "Movie: "+(input+1)+" was successfully deleted.\nThe total of movies in the database is now "+logicalSize;
			return info;
		}
		info = "No such movie";
		return info;
	}
	//resizes the database to the specified input
	private Movie[] resizeDB(Movie[] DB,int newLength){
		Movie[] resize = new Movie[newLength];
		Movie change = new Movie();

		for(int index=0; index<DB.length && index<newLength; index++){
			change = DB[index];
			resize[index] = change;
		}
		return resize;
	}
}

/**
 * Playlist.java - Assignment 2
 * Author: Cody Lewis
 * Student No: 3283349
 * Last Modified: 29-05-2017
 * Description:
 * Stores any number of movies at a time, displays the totalDuration, totalSize
 * Must be shorter than 300 minutes or smaller than 20GB.
 */
public class Playlist{
	private Movie[] movies;
	private int[] moviesID;
	private int totalTime;
	private int totalSize;
	private int logicalSize;
	private static final int MAX_TIME = 300;
	private static final int MAX_SIZE = 20000;

        //Default constructor
	public Playlist(){
		totalTime = 0;
		totalSize = 0;
		logicalSize = 0;
                movies = new Movie[4];
                moviesID = new int[1];
        }
        //Getter methods
	public String getTime(){
		String time = ""+totalTime;
		return movies[0].fixDur(time);
	}
	public String getSize(){
		String size = ""+totalSize;
		return movies[0].fixFS(size);
	}
	public int getLogicalSize(){
		return logicalSize;
	}

	//Outputs a string with a proper format for 
	private String lister(String list, int counter){
		if(movies[counter]!=null){
			list = list+"\n\nMovie "+(moviesID[counter]+1)+"\nTitle: "+movies[counter].getName()+"\nDirector: "
				+movies[counter].getDirector()+"\nDuration: "+movies[counter].getDuration()+"\nFile size: "+movies[counter].getFileSize();
			return list;
		} else {
			return "No movies yet";
		}
	}
	public String listMovies(){
		String movieList = "";
		for(int counter = 0; counter<logicalSize; counter++){
				movieList = lister(movieList,counter);
		}
		return movieList;
	}
	//Add the inputted movie from the database to the playlist
	public String addMovie(MovieDatabase DB,int index){
		Movie movie = DB.getMovie(index);
		if(totalTime+movie.getTime()<=MAX_TIME && totalSize+movie.getSize()<=MAX_SIZE){
			movies[logicalSize] = movie;
			moviesID[logicalSize] = index;
			totalTime+=movie.getTime();
			totalSize+=movie.getSize();
			logicalSize++;
			if(logicalSize>3 && (logicalSize%4)==0){	//Automatically resizes the array based on specified conditions
				movies = resizeDB(movies,logicalSize+4);
			}
			moviesID = resizeArr(moviesID,moviesID.length+1);
			return "The movie "+(index+1)+" was added to the playlist there are now "+logicalSize+" movies in the playlist";
		} else { return "The movie is too big for the playlist, the playlist still has "+logicalSize+" movies"; }	
	}
	//Deletes an instance of the inputed movie
	public String delete(int input){
		String info = "";
		int temp;
		int index = checkMovie(input);
		if(index>-1 && index<logicalSize){
			totalTime-=movies[index].getTime();
			totalSize-=movies[index].getSize();
			if(index!=(logicalSize-1)){
				for(int counter = index; counter<logicalSize-1; counter++){
					info = movies[counter+1].getName();
					movies[counter].setName(info);
					info = movies[counter+1].getDirector();
					movies[counter].setDirector(info);		
					info =""+movies[counter+1].getTime();	//Causes conversion error when there is no double quotation
					movies[counter].setDuration(info);
					info =""+movies[counter+1].getSize();
					movies[counter].setFileSize(info);
				}
			}
			logicalSize--;
			if(logicalSize>3 && (logicalSize%4)==0 && logicalSize<movies.length/2)
				movies = resizeDB(movies,movies.length/2);
			info = "Movie: "+(moviesID[index]+1)+" was successfully removed from the playlist.\nThe total of movies in the database is now "+logicalSize;
			for(int counter = index; counter<moviesID.length-1; counter++){
				temp = moviesID[counter+1];
				moviesID[counter] = temp;
			}
			moviesID = resizeArr(moviesID,moviesID.length-1);
			return info;
		}
		info = "No such movie in the playlist";
		return info;
	}
	//Deletes all instances of a chosen movie in the playlist
	public String deleteAll(int input){
		int movieCount = logicalSize;
		for(int counter = 0; counter<logicalSize-2; counter++){
			if(checkMovie(input)>-1){
				delete(input);
				counter--;
			} else {
				break;
			}
		}
		if(checkMovie(input)>-1){	//Causes an infinite loop if embedded in the previous code
			delete(input);
		}

		return "Deleted "+(movieCount-logicalSize)+" instances of movie "+(input+1);
	}
	//Fixes the movies held within the playlist upon a deletion operation
	public void init(MovieDatabase DB){
		Movie movie = new Movie();
		for(int counter=0; counter<logicalSize; counter++){
			movie = DB.getMovie(moviesID[counter]);	
			movies[counter] = movie;
		}
	}
	//Finds the input movie with reference to the movie database and the integer
	//returned is with reference to the playlist
	private int checkMovie(int input){
		for(int counter = 0; counter<moviesID.length; counter++)
			if(input==moviesID[counter])
				return counter;
		return -1;
	}
	//Resizes the movie array to the sent in integer for length
	private Movie[] resizeDB(Movie[] movies,int newLength){
		Movie[] resize = new Movie[newLength];
		Movie change = new Movie();

		for(int index=0; index<movies.length && index<newLength; index++){
			change = movies[index];
			resize[index] = change;
		}
		return resize;
	}
	//Resizes an integer array to the sent in integer for length
	private int[] resizeArr(int[] arr,int newLength){
		int[] resize = new int[newLength];
		int change;
		for(int index=0; index<arr.length && index<newLength; index++){
			change = arr[index];
			resize[index] = change;
		}
		return resize;
	}
	//Renumbers the movie ID array for the occurance of a movie being deleted
	public void reorderArr(int input){
		int[] reorder = new int[logicalSize];
		int change;
		for(int index=0; index<logicalSize; index++){
			if(input<moviesID[index]){
				change = moviesID[index]-1;
				moviesID[index]=change;
			}	
		}
	}
}

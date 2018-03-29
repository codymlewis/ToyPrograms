/**
 * Interface.java - Assignment 2
 * Author: Cody Lewis
 * Student No: 3283349
 * Last Modified: 29-05-2017
 * Description:
 * Provides the user interface, Can store up to 2 playlist objects and the movie database
 * Modified to be inclusive of arrays and use of the files.
 */

import java.util.*;

public class Interface { 
	static Scanner console = new Scanner(System.in);	//Scanner for all user operations within the program
	static MovieDatabase DB = new MovieDatabase();		//Create the movie database which manipulates and displays the movies used in the program
	static Playlist[] playlists = new Playlist[2];
	static int pLogicalSize = 0;		//logical size of the playlists array
        static String fileName="movieDatabase.txt";       //static variable used to keep the file name for saving and loading
        
	public static void main(String[] args) {
		Interface intFace = new Interface();
		intFace.run();
	}
	
	private void run() {
		//Controls the flow of the program
		//and hold all code for accessing the playlists
		//and the movie database
		try{
                        openFile();
			mainInt();
		} catch(Exception e){       //Exception for the occurance of a critical error in the software
			System.out.println("Sorry, an error occurred");
			System.out.println();
			int option;
			do{
				System.out.println("Would you like to restart the program(1), save the database(2) or exit the program(9)");
				option = Integer.parseInt(console.next());
				switch(option){
					case 1: Interface intFace = new Interface(); intFace.run(); break;
					case 2: saveFile(); break;
					case 9: exitProgram(); break;
					default: System.out.println("Invalid input, please try again");
				}
			}while(option!=0 || option!=9);
		} 
			
	}
	//Main interface dialogue and options
	private void mainInt(){
		for(;;){	//Creates an infinite loop in the runtime of the program
			System.out.println("\n\nWelcome to the Movie Interface, please choose one of the following options: ");
			System.out.println("List movies(0), List movies by...(1), List playlists(2), Add movie(3), Edit movie(4), Create Playlist(5), \nEdit playlist(6), Delete movie(7), Delete playlist(8), Load Database(9), Save(10), Save and Exit(11): ");
			int option = Integer.parseInt(console.next());
			console.nextLine();	//Removes the line break created by the previous input
			switch(option){
				case 0: listMovies(); break;
				case 1: listMoviesBy(); break;
				case 2: listPlaylists(); break;
				case 3: addMovie(); break;
				case 4: listMovies(); editMovie(); break;
				case 5: createPlaylist(); break;
				case 6: listPlaylists(); editPlaylist(); break;
				case 7: listMovies(); deleteMovie(); break;
				case 8: listPlaylists(); deletePlaylist(); break;
				case 9: openFile(); break;
                        	case 10: saveFile(); break;
                        	case 11: exitProgram(); break;
				default: System.out.println("Invalid input"); break;
			}
		}
	}
        //Movie listing methods
	//Lists the movies in the order they were added
	private void listMovies(){
		if(DB.getTotal()>0)
			System.out.println("The movies are: \n"+DB.listMovies());
		else
			System.out.println("No movies in the database yet");
	}
	//Allows the user to chose a form of listing for the movies, then outputs a generated list based on the previous input
	private void listMoviesBy(){
		if(DB.getTotal()>1){
			System.out.print("List movies by Specified Name(1), List movies by Specified Director(2), Under Specified Duration(3), Under Specified File Size(4)\nName(5), Director(6), Duration(7), File Size(8): ");
			int choice = Integer.parseInt(console.next());
			switch(choice){
				case 1: System.out.print("Name: "); console.nextLine(); String name = console.nextLine(); System.out.println("The movies titled "+name+" are "+DB.listMovies(name,choice)); break;
				case 2: System.out.print("Director: "); console.nextLine(); String sName = console.nextLine(); System.out.println("The movies by "+sName+" are "+DB.listMovies(sName,choice)); break;
				case 3: System.out.print("Duration in minutes: "); int value = console.nextInt(); System.out.println("The movies shorter than "+value+" minutes are "+DB.listMovies(value,choice)); break;
				case 4: System.out.print("File Size in MegaBytes: "); int sValue = console.nextInt(); System.out.println("The movies smaller than "+sValue+" MegaBytes are "+DB.listMovies(sValue,choice)); break;
				case 5: System.out.println("Listing alphabetically by name"); System.out.println(DB.listMoviesBy(1)); break;	
				case 6: System.out.println("Listing alphabetically by director"); System.out.println(DB.listMoviesBy(2)); break;
				case 7: System.out.println("Listing chronologically by time"); System.out.println(DB.listMoviesBy(3)); break;
				case 8: System.out.println("Listing chronologically by file size"); System.out.println(DB.listMoviesBy(4)); break;
				default: System.out.println("Invalid option"); break;
			}
		} else {
			System.out.println("Not enough movies in the database yet");
		}
	}
        //Movie information editing methods
	//Adds a movie
	private void addMovie(){
		Scanner console = new Scanner(System.in);	//Resets the scanner for new input
		System.out.println("Adding movie "+(DB.getTotal()+1));
		System.out.print("Name: "); String nm = console.nextLine();
		System.out.print("Director: "); String dir = console.nextLine();
		System.out.print("Duration: "); String dur = console.next();
		System.out.print("File size: "); String fz = console.next();
		System.out.println(DB.addMovie(nm,dir,dur,fz));	
	}
	//Edits the chosen movie
	private void editMovie(){
		int movie;
		int edit;
		String input;
		System.out.print("Choose the movie you want to edit: "); movie = Integer.parseInt(console.next());
		if(movie>0 && movie<=DB.getTotal()){	//Gives choice dialog for editing the specified movie
			System.out.print("Name(1), Director(2), Duration(3), File size(4)\nYou may exit the program with 9 or return to the interface with any unused number\nWhat do you want to edit? "); edit = console.nextInt();
			console.nextLine();
			if(edit>0 && edit<5){
				System.out.print("Input your edit: "); input = console.nextLine();
				System.out.println(DB.editMovie(movie-1,edit,input));
			}
			else if(edit == 9){
				exitProgram();
			} else{ mainInt(); }
		} else{	mainInt(); }
	}
        //Playlist listing methods
	//Lists all playlists
	private void listPlaylists(){
		if(playlists[0]!=null && pLogicalSize>0){
			System.out.println("\nThe playlists are ");
			for(int counter = 0; counter<pLogicalSize; counter++){
				if(playlists[counter].getLogicalSize()>0){
					listAPlaylist(counter);
					System.out.println();
				}
				else{
					System.out.println("Found empty playlist "+(counter+1)+" proceeding to delete it");
					deletePL(counter);	//deletes empty playlists that may have stayed in the system
					counter--;
				}
			}
		} else { 
			System.out.println("No playlists created yet");
		}
	}
	//Gives information on the chosen playlist
	private void listAPlaylist(int counter){
		System.out.println("\nPlaylist "+(counter+1)+"\nTotal Duration: "+playlists[counter].getTime()+"\nTotal Size: "+playlists[counter].getSize()+"\nHolding:"+playlists[counter].listMovies());
      	}
        //Playlist information adding methods
	//Creates a new playlist and recieves data for the playlist class
	private void createPlaylist(){
		if(DB.getTotal()>0){
			int index;
			playlists[pLogicalSize] = new Playlist();
			listMovies();
			System.out.println("Adding to playlist "+(pLogicalSize+1));
			System.out.println("You may stop adding by entering 0");
			do{
				System.out.print("Movie: ");
				index = console.nextInt(); index--;
				if(index<0 || index>=DB.getTotal()) continue;
				System.out.println(playlists[pLogicalSize].addMovie(DB,index));
			}while(index>-1 && index<DB.getTotal());
			pLogicalSize++;
			if(pLogicalSize>1 && (pLogicalSize%2)==0){	//Automatically resizes the array based on specified conditions
				playlists = resizePL(playlists,pLogicalSize+2);
			}
			System.out.println("Finished adding movies, returning to interface");
		} else {
			System.out.println("No movies to add to playlist");
		}
	}
	//Allow the user to change the movies in the chosen playlist
	private void editPlaylist(){
		int option; int movie;
		System.out.print("Choose the playlist you want to edit: ");
		int index = console.nextInt()-1;
		if(playlists[index]==null)
			noPlaylist(index);
		do{
			System.out.println("Add movie(1), Remove movie(2)");
			option = console.nextInt();
				
			switch(option){
				case 1: listMovies();
					System.out.print("Movie: "); 
					movie = console.nextInt()-1;
					System.out.println(playlists[index].addMovie(DB,movie)); 
					break;
				case 2: if(playlists[index]!=null){
						if(playlists[index].getLogicalSize()>1){
							listAPlaylist(index); 
							System.out.print("Movie: "); 
							movie = console.nextInt()-1;
							System.out.println(playlists[index].delete(movie));
						} else {
							System.out.println("No movies left in playlist, completely deleting it and returning to the interface");
							deletePL(index);
							mainInt();
						}
						break;
					} else {
						System.out.println("No such playlist to delete, returning to the interface");
						mainInt();
						break;
					}
				default: System.out.println("Invalid option"); break;
			}
		System.out.println("Would you like to continue editing playlist "+(index+1)+"(1) or stop editing(2)");
		option = console.nextInt();
		}while(option==1);	
	}
	//provides dialogue when there is no playlist when attempting to edit
	private void noPlaylist(int index){
		int choice;
		do{
			System.out.println("Playlist "+(index+1)+" does not exist\nWould you like to create a new playlist(1), return to the interface(2) or exit the program(9)");
			choice = console.nextInt();
			switch(choice){
				case 1: createPlaylist();	//Break not needed, returns to interface without repeating code
				case 2: mainInt(); break;
				case 9: exitProgram(); break;
				default: System.out.println("Invalid input"); break;
			}
		}while(choice>2 || choice<1 || choice!=9);
	}
        //Delete methods
	//Deletes chosen movie
	private void deleteMovie(){
		if(DB.getTotal()>0){
			System.out.print("Warning, you are about to delete a movie, you may return to the interface with 0\nType the number of the movie you want to delete: "); 
			int choice = console.nextInt(); choice--;
			if(choice<0 || choice>=DB.getTotal()){
				System.out.print("\nNo movie deleted, returning to interface");
				mainInt();
			}
			if(playlists[0]!=null){
				for(int counter=0; counter<pLogicalSize; counter++){
					System.out.println("\n"+playlists[counter].deleteAll(choice)+" from playlist "+(counter+1));
					if(playlists[counter].getLogicalSize()<1){
						System.out.println("No movies left in playlist "+counter+", completely deleting it");
						deletePL(counter);
					}
					playlists[counter].reorderArr(choice); //Fixes array numbers
				}
			}
			System.out.println(DB.delete(choice)); 

		} else {
			System.out.println("There are no movies to delete");
		}
		for(int counter=0; counter<pLogicalSize; counter++)
			playlists[counter].init(DB);
	}
	//Recieves a chosen playlist and send the information so it can be deleted
	private void deletePlaylist(){
		System.out.print("Enter the playlist you want to delete: ");
		int index = console.nextInt()-1;
		deletePL(index);
	}
	//Deletes the playlist at the recieved index
	private void deletePL(int index){
		Playlist info = new Playlist();
		if(index>=0 && index<pLogicalSize){
			if(index!=(pLogicalSize-1)){
				for(int counter = index; counter<pLogicalSize-1; counter++){
					info = playlists[counter+1];
					playlists[counter] = info;
				}
			}
			pLogicalSize--;
			if(pLogicalSize>1 && (pLogicalSize%2)==0 && pLogicalSize<playlists.length/2)
				playlists = resizePL(playlists,playlists.length/2);
			System.out.println("Playlist: "+(index+1)+" was successfully deleted.\nThe total of playlists is now "+pLogicalSize);
		} else {
			System.out.println("No such playlist");
		}
	}
	//Resizes the playlist array
	private Playlist[] resizePL(Playlist[] PLs,int newLength){
		Playlist[] resize = new Playlist[newLength];
		Playlist change = new Playlist();

		for(int index=0; index<PLs.length && index<newLength; index++){
			change = PLs[index];
			resize[index] = change;
		}
		return resize;
	}
        //File based methods
	//Executes the save file method in Movie Database
	private void saveFile(){
                                    System.out.println("Would you like to save to a new file(1) or overwrite "+fileName+"(2)");
		System.out.println("You may return to the interface 0 or exit without saving(9)");
                                    int option = Integer.parseInt(console.next());
                                    switch(option){
                                        case 0: System.out.println("Returning to the interface");
                                                      mainInt();
                                                      break;
                                        case 1: System.out.print("File: "); fileName=console.next();
                                                     System.out.println(DB.saveFile(fileName));
                                                     break;
                                        case 2: System.out.println(DB.saveFile(fileName));
                                                     break;
                                        case 9: System.out.println("Exiting program without saving");
                                                     System.exit(0);
                                                     break;
                                    }
	}
	//Executes the open file method in Movie Database
	private void openFile(){
		boolean loaded = false;
        	System.out.println("Would you like to not load a file(1), choose a file to load from(2) or use the default file(3)");
                System.out.println("You may return to the interface with 0 or exit with 9");
                int option = Integer.parseInt(console.next());
                switch(option){
                	case 0: System.out.println("Returning to the interface"); 
                                mainInt();
                                break;
			case 1: System.out.println("No file loaded"); break;
                	case 2: System.out.print("File: "); fileName=console.next(); 
                                if(DB.openFile(fileName).equals("No previous data saved")) {
                                	System.out.println("Could not find specified file, loading default");
                                        fileName="movieDatabase.txt";
                                }
                                System.out.println(DB.openFile(fileName)); 
				loaded = true;
                                break;
                        case 3: fileName="movieDatabase.txt"; 
                                System.out.println(DB.openFile(fileName));
				loaded = true;
                                break;
                        case 9: exitProgram(); 
                                break;
                        default: System.out.println("Invalid input, try again"); 
                                 openFile();
                }
		if(loaded==true && playlists[0]!=null)
			for(int index=pLogicalSize; index>0; index--)
				deletePL(pLogicalSize-1); 
	}
	//Exits program
	private void exitProgram(){
                saveFile();
		System.out.println("Exiting program");
		System.exit(0);
	}
}

import java.util.*;
import java.io.*;


public class TextFileRW implements Serializable{
	public static void main(String[] args){
		PrintWriter outputStream = null;
		try{
			outputStream = openOutputTextFile("movieDatabase.txt");
		} catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(outputStream);
	}
//	public static String readFile(){
//		try{
//			FileOutputStream fos = new FileOutputStream("movieDatabase.txt");
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//		}
//		return content;
//	}
	public static PrintWriter openOutputTextFile(String fileName) throws FileNotFoundException,IOException{
		PrintWriter toFile = new PrintWriter(fileName);
		return toFile;
	}
}

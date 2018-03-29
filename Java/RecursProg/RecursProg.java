/**
 * RecursProg.java - Knowledge Summary
 * Last Modified: 30/05/2017
 * Description:
 * Program that uses recursion to write a copy of itself.
 */
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class RecursProg{
	static int counter=2;
	static final String fileName = "RecursProg"+counter+".java";
	public static void main(String[] args){
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(fileName);
		} catch(FileNotFoundException e){
			System.err.println("Error,  Saving the file "+fileName);
		}
		outputStream.println("import java.util.scanner;");
		outputStream.println("import java.io.File;");
		outputStream.println("import java.io.PrintWriter;");
		outputStream.println("import java.io.FileNotFoundException;");
		outputStream.println("public class RecursProg"+counter+"{");
		outputStream.println("	int counter="+(counter+1)+";");
		outputStream.println("	static final String fileName = \"RecursProg\"+counter+\".java;\"");
		outputStream.println("	public static void main(String[] args){");
		outputStream.println("		PrintWriter outputStream = null;");
		outputStream.println("		try{");
		outputStream.println("			outputStream = new PrintWriter(fileName);");
		outputStream.println("		} catch(FileNotFoundException e){");
		outputStream.println("			System.err.println(\"Error, Saving the file \"+fileName);");
		outputStream.println("		}");
		outputStream.println("	outputStream.println(\"import java.util.scanner;\");");
		outputStream.println("	outputStream.println(\"import java.io.File;\");");
		outputStream.println("	outputStream.println(\"import java.io.PrintWriter;\");");
		outputStream.println("	outputStream.println(\"import java.io.FileNotFoundException;\");");
		outputStream.println("	outputStream.println(\"public class RecursProg\"+counter+\"{\");");
		outputStream.println("	outputStream.println(\"	int counter=\"+(counter+1)+\";\");");
		outputStream.println("	outputStream.println(\"	static final String fileName = \"RecursProg\"+counter+\".java\";);");
		outputStream.println("	outputStream.println(\"	public static void main(String[] args){\"););");
		outputStream.println("	outputStream.println(\"		PrintWriter outputStream = null;\");");
		outputStream.println("	outputStream.println(\"		try{\");");
		outputStream.println("	outputStream.println(\"			outputStream = new PrintWriter(fileName);\");");
		outputStream.println("	outputStream.println(\"		} catch(FileNotFoundException e){\");");
		outputStream.println("	outputStream.println(\"			System.err.println(\"Error, Saving the file \"+fileName);\");");
		outputStream.println("	outputStream.println(\"		}\");");
		outputStream.println("	outputStream.close();");
		outputStream.println("	System.out.println(\"The program \"+fileName+\" was created\");");
		outputStream.println("	}");
		outputStream.println("}");
		outputStream.close();
		System.out.println("The program "+fileName+" was created");
	}
}

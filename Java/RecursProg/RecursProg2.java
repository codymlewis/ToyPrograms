import java.util.scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class RecursProg2{
	int counter=3;
	static final String fileName = "RecursProg"+counter+".java;"
	public static void main(String[] args){
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(fileName);
		} catch(FileNotFoundException e){
			System.err.println("Error, Saving the file "+fileName);
		}
	outputStream.println("import java.util.scanner;");
	outputStream.println("import java.io.File;");
	outputStream.println("import java.io.PrintWriter;");
	outputStream.println("import java.io.FileNotFoundException;");
	outputStream.println("public class RecursProg"+counter+"{");
	outputStream.println("	int counter="+(counter+1)+";");
	outputStream.println("	static final String fileName = "RecursProg"+counter+".java";);
	outputStream.println("	public static void main(String[] args){"););
	outputStream.println("		PrintWriter outputStream = null;");
	outputStream.println("		try{");
	outputStream.println("			outputStream = new PrintWriter(fileName);");
	outputStream.println("		} catch(FileNotFoundException e){");
	outputStream.println("			System.err.println("Error, Saving the file "+fileName);");
	outputStream.println("		}");
	outputStream.close();
	System.out.println("The program "+fileName+" was created");
	}
}

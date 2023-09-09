package telran.io.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyAppl {
	
	
		// args[0] - source file
		// args[2] - destination file
		// args[3] - "overwrite"
		// TODO write application for copying from source file to destination file
		// Implementation Requirement: to use while cycle with read call
		// main must not contain throws declaration
	//	String[] arguments = {"C:/Users/Daniel/Desktop/test/111.txt", "C:/Users/Daniel/Desktop/test/222.txt", "overwrite"};
	public static void copyApp(String [] args)	{
		if (args.length < 2) {
			System.out.println("Too few arguments");
			return;
		}
		if (!Files.exists(Path.of(args[0]))) {
			System.out.printf("source file %s must exist", args[0]);
			return;
		}
		if (!Files.exists(Path.of(args[1]).getParent())) {

			System.out.printf("The directory %s must exist",
					Path.of(args[1]).getName(Path.of(args[1]).getNameCount() - 2));
			return;
		}
		if (Files.exists(Path.of(args[1])) && (args.length < 3 || !args[2].equals("overwrite"))) {
			System.out.printf("File %s cannot be overwritten", args[1]);
			return;
		}
		copyMethod(args);
	}

	private static void copyMethod(String[] args) {
		int fileLength = 0;
		try (InputStream input = new FileInputStream(args[0])) {	
			fileLength = input.available();
			int outputPoint = 0;
			byte[] buffer = new byte[1024 * 1024];
			int bytesRead = 0;
			while ((bytesRead= input.read(buffer)) != -1) {				
				outputMethod(buffer, outputPoint, bytesRead, args[1]);
				outputPoint += bytesRead;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
printResult(fileLength, args[0], args[1]);
	}

	private static void printResult(int fileLength, String args0, String args1) {
		System.out.printf("successful copying of %s bytes have been copying\n"
				+ "from the file %s to the file %s. Time \n", fileLength,args0, args1);
		
	}

	private static void outputMethod(byte[] buffer, int startPoint, int length, String destinationPath) {
		try (OutputStream output = new FileOutputStream(destinationPath, true)) {
			output.write(buffer, startPoint, length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
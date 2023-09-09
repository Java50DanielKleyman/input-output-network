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

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String[] myArgs = { "C:/Users/Daniel/Desktop/test/111.doc", "C:/Users/Daniel/Desktop/test/222.doc",
				"overwrite" };
		if (myArgs.length < 2) {
			System.out.println("Too few arguments");
			return;
		}
		if (!Files.exists(Path.of(myArgs[0]))) {
			System.out.printf("source file %s must exist", myArgs[0]);
			return;
		}
		if (!Files.exists(Path.of(myArgs[1]).getParent())) {

			System.out.printf("The directory %s must exist",
					Path.of(myArgs[1]).getName(Path.of(myArgs[1]).getNameCount() - 2));
			return;
		}
		if (Files.exists(Path.of(myArgs[1])) && (myArgs.length < 3 || !myArgs[2].equals("overwrite"))) {
			System.out.printf("File %s cannot be overwritten", myArgs[1]);
			return;
		}
		copyMethod(myArgs);
		long endTime = System.currentTimeMillis();
		System.out.printf("\nTime %s milliseconds.", endTime - startTime);
	}

	private static void copyMethod(String[] args) {
		int fileLength = 0;
		try (InputStream input = new FileInputStream(args[0]); OutputStream output = new FileOutputStream(args[1])) {
			fileLength = input.available();
			byte[] buffer = new byte[1024 * 1024];
			int bytesRead = 0;
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printResult(fileLength, args[0], args[1]);
	}

	private static void printResult(int fileLength, String args0, String args1) {
		System.out.printf("successful copying of %s bytes have been copying\n" + "from the file %s to the file %s. ",
				fileLength, args0, args1);

	}

}
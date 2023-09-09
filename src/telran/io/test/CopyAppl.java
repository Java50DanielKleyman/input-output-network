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
		// args[0] - source file
		// args[2] - destination file
		// args[3] - "overwrite"
		// TODO write application for copying from source file to destination file
		// Implementation Requirement: to use while cycle with read call
		// main must not contain throws declaration
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
		if (Files.exists(Path.of(args[1])) && (args.length < 3 || args[2].equals("overwrite"))) {
			System.out.printf("File %s cannot be overwritten", args[1]);
		}
		copyMethod(args);
	}

	private static void copyMethod(String[] args) {
		try (InputStream input = new FileInputStream(args[0])) {			
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
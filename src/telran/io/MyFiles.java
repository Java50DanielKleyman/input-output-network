package telran.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

public class MyFiles {
	public static void displayDir(String path, int maxDepth) throws IOException {
		// TODO
		// Throwing IllegalArgumentException in the case the given is not a directory
		// Printing out the directory content using appropriate offsets showing which
		// node belong to which directory
		// printing type of node, for example
		// input-output-network - dir
		// src - dir
		// telran - dir
		// io - dir
		// test - dir
		// FileSystemTests.java - file
		while (maxDepth > 0) {
			try {
				Files.walk(Path.of(path).toAbsolutePath().normalize(), maxDepth).forEach(p -> printAll(p, maxDepth));
			} catch (IOException e) {

			}
		}

	}

	private static void printAll(Path p, int maxDepth) {
		boolean isDirectory = Files.isDirectory(p);
		if (isDirectory) {
			try {
				Iterator<Path> it = p.iterator();
				if (it.hasNext()) {
					displayDir(p.toString(), maxDepth - 1);
				} else {
					System.out.printf("%s - dir", p.getFileName());
				}
			} catch (IOException e) {

			}
		} else {
			System.out.printf("%s - file", p.getFileName());
		}
	}
}
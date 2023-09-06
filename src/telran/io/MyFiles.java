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
		while (maxDepth > 1) {
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
				boolean isEmpty = isDirectoryEmpty(p);
				if (!isEmpty && maxDepth > 1) {
					displayDir(p.toString(), maxDepth - 1);
				} else {
					System.out.printf("%s - dir\n", p.getFileName());
				}
			} catch (IOException e) {

			}
		} else {
			System.out.printf("%s - file\n", p.getFileName());
		}
	}

	private static boolean isDirectoryEmpty(Path directory) throws IOException {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
			return !dirStream.iterator().hasNext();
		}
	}
}
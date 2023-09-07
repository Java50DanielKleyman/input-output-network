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
		if (!Files.isDirectory(Paths.get(path))) {
			throw new IllegalArgumentException("The provided path is not a directory.");
		}

		Files.walk(Path.of(path).toAbsolutePath().normalize(), maxDepth).forEach(p -> {
			try {
				displayDirRecursively(p, 0, maxDepth - 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private static void displayDirRecursively(Path path, int currentDepth, int maxDepth) throws IOException {
		boolean isDirectory = Files.isDirectory(path);

		if (currentDepth <= maxDepth) {
			String indent = "  ".repeat(currentDepth);
			System.out.printf("%s%s - %s%n", indent, path.getFileName(), isDirectory ? "dir" : "file");

			if (isDirectory) {
				try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
					for (Path subPath : stream) {
						displayDirRecursively(subPath.toAbsolutePath().normalize(), currentDepth + 1, maxDepth-1);
					}
				}
			}
		}
	}
}
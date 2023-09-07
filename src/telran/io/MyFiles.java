package telran.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;
import java.util.stream.Stream;

public class MyFiles {
	public static void displayDir(String path, int maxDepth) throws IOException {
		Path directory = Paths.get(path).toAbsolutePath().normalize();
		if (!Files.isDirectory(directory)) {
			throw new IllegalArgumentException("The provided path is not a directory.");
		}
		displayDirRecursively(directory, maxDepth, 0);
	}

	private static void displayDirRecursively(Path path, int maxDepth, int currentDepth) throws IOException {
		 boolean isDirectory = Files.isDirectory(path);

	        if (isDirectory) {
	            System.out.printf("%s - dir%n", path.getFileName());
	        } else {
	            System.out.printf("%s - file%n", path.getFileName());
	        }

	        if (currentDepth < maxDepth) {
	            try (Stream<Path> stream = Files.walk(path, 1)) { // Limit to depth 1
	                stream.forEach(subpath -> {
	                    if (!subpath.equals(path)) { // Avoid processing the current directory itself
	                        try {
	                            displayDirRecursively(subpath, maxDepth-1, currentDepth + 1);
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                });
	            }
	        }
	    }
	}
	




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

	        displayDirRecursively(Paths.get(path).toAbsolutePath().normalize(), maxDepth);
	    }

	    private static void displayDirRecursively(Path path, int maxDepth) {
	        boolean isDirectory = Files.isDirectory(path);

	        if (isDirectory) {
	            System.out.printf("%s - dir%n", path.getFileName());

	            if (maxDepth > 0) {
	                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
	                    for (Path entry : dirStream) {
	                        displayDirRecursively(entry, maxDepth - 1);
	                    }
	                } catch (IOException e) {
	                    
	                }
	            }
	        } else {
	            System.out.printf("%s - file%n", path.getFileName());
	        }
	    }
	}
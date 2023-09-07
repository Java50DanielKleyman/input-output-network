package telran.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class MyFiles {
//	public static void displayDir(String path, int maxDepth) throws IOException {
//		Path directory = Paths.get(path).toAbsolutePath().normalize();
//		if (!Files.isDirectory(directory)) {
//			throw new IllegalArgumentException("The provided path is not a directory.");
//		}
//		displayDirRecursively(directory, maxDepth, 0);
//	}
//
//	private static void displayDirRecursively(Path path, int maxDepth, int currentDepth) throws IOException {
//		boolean isDirectory = Files.isDirectory(path);
//		String spaces = "   ".repeat(currentDepth);
//		if (isDirectory) {
//			System.out.printf("%s%s - dir%n", spaces, path.getFileName());
//		} else {
//			System.out.printf("%s%s - file%n", spaces, path.getFileName());
//		}
//
//		if (currentDepth < maxDepth) {
//			try (Stream<Path> stream = Files.walk(path, 1)) { // Limit to depth 1
//				stream.forEach(subpath -> {
//					try {
//						displayDirRecursively(subpath, maxDepth-1, currentDepth + 1);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				});
//			}
//		}
//	}
	 public static void displayDir(String path, int maxDepth) throws IOException {
	        Path directory = Paths.get(path).toAbsolutePath().normalize();
	        if (!Files.isDirectory(directory)) {
	            throw new IllegalArgumentException("The provided path is not a directory.");
	        }

	        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
	            private int currentDepth = 0;

	            @Override
	            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	                boolean isRootDirectory = dir.equals(directory);
	                if (!isRootDirectory && currentDepth >= maxDepth) {
	                    return FileVisitResult.SKIP_SUBTREE; // Skip subdirectories beyond the max depth
	                }

	                String spaces = "  ".repeat(currentDepth); // Two spaces per level
	                System.out.printf("%s%s - dir%n", spaces, dir.getFileName());
	                currentDepth++;

	                return FileVisitResult.CONTINUE;
	            }

	            @Override
	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	                String spaces = "  ".repeat(currentDepth); // Two spaces per level
	                System.out.printf("%s%s - file%n", spaces, file.getFileName());

	                return FileVisitResult.CONTINUE;
	            }

	            @Override
	            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
	                currentDepth--;

	                return FileVisitResult.CONTINUE;
	            }
	        });
	    }
}

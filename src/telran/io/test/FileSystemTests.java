package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.io.MyFiles;

class FileSystemTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void test() throws IOException {
		Path current = Path.of(".");
		System.out.printf("current.getFileName() -> %s\n", current.getFileName());
		assertTrue(Files.isDirectory(current));
		assertFalse(current.isAbsolute());
		current = current.toAbsolutePath();
		assertTrue(current.isAbsolute());
		System.out.printf("current.getFileName() -> %s\n", current.getFileName());
		current = current.normalize();
		
		System.out.printf("current.toRealPath() -> %s\n", current.toRealPath());
		System.out.printf("current.getNameCount() -> %s\n", current.getNameCount());
		System.out.printf("current.getName(5) -> %s\n", current.getName(5));
		System.out.printf("current.getFileName() -> %s\n", current.getFileName());
		//Files.list(current).map(p -> p.getFileName()).forEach(p -> System.out.println(p));
		//Files.walk(current).map(p -> p.getFileName()).forEach(p -> System.out.println(p));
		Files.walk(current, 2).map(p -> p.getFileName()).forEach(p -> System.out.println(p));
		//C:\Tel Ran\Exclipse_Java_Projects\input-output-network
		
		
		
	}
	@Test
	void displayDirTest() throws IOException {
		MyFiles.displayDir("C:\\Tel Ran\\Exclipse_Java_Projects\\input-output-network\\src\\telran", 1);
	}
	

}

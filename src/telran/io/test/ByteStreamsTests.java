package telran.io.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertEquals;

import java.io.*;

public class ByteStreamsTests {
	
	@Test
	void copyAppTest (){
		String[] arguments = {"C:/Users/Daniel/Desktop/test/111.txt", "C:/Users/Daniel/Desktop/test/222.txt", "overwrite"};
		CopyAppl.copyApp(arguments);
	}
	@Test
void ifTest() {
		String[] arguments = {"C:/Users/Daniel/Desktop/test/111.txt"};
		CopyAppl.copyApp(arguments);
		String[] arguments1 = {"C:/Users/Daniel/Desktop/test/111.txt", "C:/Users/Daniel/Desktop/test/222.txt", "ovesdrwrite"};
		CopyAppl.copyApp(arguments1);
		String[] arguments2 = {"C:/Users/Daniel/Desktop/test/111.txt", "C:/Users/Daniel/Desktop/test/222.txt"};
		CopyAppl.copyApp(arguments2);
	}
}
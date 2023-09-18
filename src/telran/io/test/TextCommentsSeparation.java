package telran.io.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class TextCommentsSeparation {

	public static void main(String[] args) throws IOException {
		String pattern = "^\\s*//";
		String[] arguments = { "C:/Users/Daniel/Desktop/test/originalFileWithComments.txt",
				"C:/Users/Daniel/Desktop/test/onlyComments.txt", "C:/Users/Daniel/Desktop/test/onlyText.txt" };

		try (BufferedReader reader = new BufferedReader(new FileReader(arguments[0]));
				BufferedWriter writer = new BufferedWriter(new FileWriter(arguments[1]));
				BufferedWriter writer1 = new BufferedWriter(new FileWriter(arguments[2]))) {
			reader.lines().forEach(line -> {
				try {
					if (Pattern.matches(pattern, line)) {
						writer.write(line);
						writer.newLine();
					} else {
						writer1.write(line);
						writer1.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
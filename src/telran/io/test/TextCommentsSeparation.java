package telran.io.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextCommentsSeparation {

    public static void main(String[] args) throws IOException {
        String pattern = "^\\s*//";
        String[] arguments = {
            "C:/Users/Daniel/Desktop/test/originalFileWithComments.txt",
            "C:/Users/Daniel/Desktop/test/onlyComments.txt",
            "C:/Users/Daniel/Desktop/test/onlyText.txt"
        };

        try (
            BufferedReader reader = new BufferedReader(new FileReader(arguments[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(arguments[1]));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(arguments[2]))
        ) {
            // Separate comments and non-comments using collect
            String comments = reader.lines()
                    .filter(line -> Pattern.matches(pattern, line))
                    .collect(Collectors.joining(System.lineSeparator()));
            
            String text = reader.lines()
                    .filter(line -> !Pattern.matches(pattern, line))
                    .collect(Collectors.joining(System.lineSeparator()));
            
            // Write the comments and text to their respective files
            writer.write(comments);
            writer1.write(text);
        }
    }
}
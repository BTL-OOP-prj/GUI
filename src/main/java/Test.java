package main.java;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import main.java.Core.main_dict.Word;
import main.java.Core.main_dict.WordsManager;
import main.java.Core.main_dict.dbToManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Test {

    /**
     * db modifier.
     */
    public static void dbmod() {
        try {
            String path = dbToManager.pathGetter("/src/main/resources/Eng.txt");
            System.err.println(path);
            File DBFile = new File(path);
            System.err.println(DBFile.exists() ? "file found" : "file not found");
            Scanner scanner = new Scanner(DBFile, "UTF-8");
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_8));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                while (line.indexOf("~~") != -1) {
                    line = line.replace("~~", "~@~");
                }
                if (line.endsWith("~")) {
                    line = line + "@";
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * suggestions test.
     */
    public static void suggestionsTest() {
        List<Word> words = WordsManager.suggestions("al");
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("output.txt"),
                            StandardCharsets.UTF_8));
            for (Word word : words) {
                writer.write(word.getPronunciation().equals("@") ? word.getContent()
                        : word.getContent() + " " + word.getPronunciation());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // dbmod();
        dbToManager.scan("/src/main/resources/Eng.txt");
        dbToManager.export("/src/main/resources/Eng.txt");
        suggestionsTest();
    }
}

package main.java.Core.main_dict;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import main.java.Core.main_dict.Word;
import main.java.Core.main_dict.WordsManager;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class dbToManager {

    /**
     * split.
     */
    public static String[] split(String str, String delimiter) {
        String[] result = new String[5];
        result = str.split(delimiter);
        return result;
    }

    /**
     * Get the path of the database.
     */
    public static String pathGetter(String raw_path) {
        File pathGetter = new File("");
        String path = pathGetter.getAbsolutePath();
        path = path + raw_path;
        return path;
    }

    /**
     * turn the string into a list-like string.
     */
    private static String ListyString(String rawText) {
        String[] resArr = rawText.split("|");
        String res = "";
        for (int i = 0; i < resArr.length; i++) {
            if (i > 2) { // takes only 3 lines
                break;
            }
            String tmp = resArr[i];
            if (tmp.indexOf("+") != -1) {
                tmp = tmp.replace("+", ":");
            }
            char c = tmp.charAt(0);
            if ('a' <= c && c <= 'z') {
                // System.out.println("need Uppercase: " + tmp.charAt(0));
                c = (char) (c - 'a' + 'A');
                tmp = c + tmp.substring(1);
            }
            res = res + "- " + tmp + "\n";
        }
        return res;
    }

    /**
     * Scan the database and add all words to the manager.
     * #import
     */
    public static void scan(String path) {
        try {
            path = pathGetter(path);
            // System.out.println(path);
            File DBF = new File(path);
            Scanner sc = new Scanner(DBF, "UTF-8");
            if (!sc.hasNextLine()) {
                System.err.println("File is empty!");
                sc.close();
                return;
            }
            while (sc.hasNextLine()) {
                String curLine = sc.nextLine();
                String[] curWord = curLine.split("\t");
                String Content = curWord[0];
                String Pronunciation = curWord[3];
                String Type = curWord[1];
                String Meaning = curWord[2];
                String Example = curWord[4];
                Meaning = ListyString(Meaning);
                Example = ListyString(Example);
                if (Example != null && Example.length() > 0) {
                    Example = "Examples:\n" + Example;
                }
                if (Type.equals("NULL")) {
                    Type = "";
                }
                if (Pronunciation.equals("NULL")) {
                    Pronunciation = "";
                }
                // System.err.println(Content + "\n" + Type + "\n" + Meaning + "\n" +
                // Pronunciation + "\n" + Example);
                Word word = new Word(Content, Type, Meaning, Pronunciation, Example);
                WordsManager.insertWord(word);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            // e.printStackTrace();
        }

    }

    /**
     * export.
     * export to path (.txt file)
     */
    public static void export(String path) {
        try {
            path = pathGetter(path);
            List<Word> words = WordsManager.getAllWords();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
            for (Word word : words) {
                writer.write(word.toExport());
                if (word.toExport().length() > 0)
                    writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
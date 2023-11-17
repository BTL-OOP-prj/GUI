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
        if (rawText.length() < 2) {
            return "";
        }
        String res = rawText.substring(1, rawText.length() - 1);
        String[] resArr = res.split(", ");
        res = "";
        for (int i = 0; i < resArr.length; i++) {
            if (resArr[i].length() < 2)
                continue;
            String tmp = resArr[i];
            while (tmp.startsWith("\"") || tmp.startsWith("\'")) {
                tmp = tmp.substring(1);
            }
            while (tmp.endsWith("\"") || tmp.endsWith("\'")) {
                tmp = tmp.substring(0, tmp.length() - 1);
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
                return;
            }
            while (sc.hasNextLine()) {
                String curLine = sc.nextLine();
                String[] curWord = split(curLine, "~");
                String Content = curWord[0];
                String Type = curWord[1];
                String Meaning = curWord[2];
                String Pronunciation = curWord[3];
                String Example = curWord[4];
                if (Content.indexOf(" ") != -1 || Content.indexOf("-") != -1 || Content.indexOf("'") != -1
                        || Content.indexOf(".") != -1 || Content.indexOf(",") != -1 || Content.indexOf("?") != -1
                        || Content.indexOf("!") != -1 || Content.indexOf(":") != -1 || Content.indexOf(";") != -1
                        || Content.indexOf("\"") != -1 || Content.indexOf("(") != -1 || Content.indexOf(")") != -1
                        || Content.indexOf("[") != -1 || Content.indexOf("]") != -1 || Content.indexOf("{") != -1
                        || Content.indexOf("}") != -1 || Content.indexOf("/") != -1 || Content.indexOf("\\") != -1
                        || Content.indexOf("<") != -1 || Content.indexOf(">") != -1 || Content.indexOf("|") != -1
                        || Content.indexOf("*") != -1 || Content.indexOf("&") != -1 || Content.indexOf("^") != -1
                        || Content.indexOf("%") != -1 || Content.indexOf("$") != -1 || Content.indexOf("#") != -1
                        || Content.indexOf("`") != -1 || Content.indexOf("+") != -1 || Content.indexOf("=") != -1
                        || Content.indexOf("-") != -1 || Content.indexOf("_") != -1 || Content.indexOf("0") != -1
                        || Content.indexOf("1") != -1 || Content.indexOf("2") != -1 || Content.indexOf("3") != -1
                        || Content.indexOf("4") != -1 || Content.indexOf("5") != -1 || Content.indexOf("6") != -1
                        || Content.indexOf("7") != -1 || Content.indexOf("8") != -1 || Content.indexOf("9") != -1) {
                    continue;
                }
                Meaning = ListyString(Meaning);
                Example = ListyString(Example);
                if (Example != null && Example.length() > 0) {
                    Example = "Examples:\n" + Example;
                }
                if (Type.equals("@")) {
                    Type = "";
                }
                if (Pronunciation.equals("@")) {
                    Pronunciation = "";
                }
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
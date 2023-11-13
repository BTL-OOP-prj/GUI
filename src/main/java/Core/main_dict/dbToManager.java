package main.java.Core.main_dict;

import java.io.*;
import java.util.Scanner;
import main.java.Core.main_dict.Word;
import main.java.Core.main_dict.WordsManager;
import java.net.*;

public class dbToManager {

    /**
     * split.
     */
    public static String[] split(String str, String delimiter) {
        String[] result = new String[5];
        int index = str.indexOf(delimiter);
        // check content
        if (index == 0) {
            result[0] = "";
            str = str.substring(index + 1);
        } else {
            result[0] = str.substring(0, index);
            str = str.substring(index + 1);
        }
        // check type
        index = str.indexOf(delimiter);
        if (index == 0) {
            result[1] = "";
            str = str.substring(index + 1);
        } else {
            result[1] = str.substring(0, index);
            str = str.substring(index + 1);
        }
        // check meaning
        index = str.indexOf(delimiter);
        if (index == 0) {
            result[2] = "";
        } else {
            result[2] = str.substring(0, index);
            str = str.substring(index + 1);
        }
        // check pronunciation
        index = str.indexOf(delimiter);
        if (index == 0) {
            result[3] = "";
            str = str.substring(index + 1);
        } else {
            result[3] = str.substring(0, index);
            str = str.substring(index + 1);
        }
        // check example
        result[4] = str;
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
     * Scan the database and add all words to the manager.
     */
    public static void scan(String path) {
        try {
            path = pathGetter(path);
            System.out.println(path);
            File DBF = new File(path);
            Scanner sc = new Scanner(DBF, "UTF-8");
            if (!sc.hasNextLine()) {
                System.out.println("File is empty!");
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
                if (Content.indexOf(" ") != -1 || Content.indexOf("-") != -1 || Content.indexOf("'") != -1 ||
                        Content.indexOf(".") != -1 || Content.indexOf(",") != -1 || Content.indexOf("?") != -1 ||
                        Content.indexOf("!") != -1 || Content.indexOf(":") != -1 || Content.indexOf(";") != -1 ||
                        Content.indexOf("\"") != -1 || Content.indexOf("(") != -1 || Content.indexOf(")") != -1 ||
                        Content.indexOf("[") != -1 || Content.indexOf("]") != -1 || Content.indexOf("{") != -1 ||
                        Content.indexOf("}") != -1 || Content.indexOf("/") != -1 || Content.indexOf("\\") != -1 ||
                        Content.indexOf("<") != -1 || Content.indexOf(">") != -1 || Content.indexOf("|") != -1 ||
                        Content.indexOf("*") != -1 || Content.indexOf("&") != -1 || Content.indexOf("^") != -1 ||
                        Content.indexOf("%") != -1 || Content.indexOf("$") != -1 || Content.indexOf("#") != -1 ||
                        Content.indexOf("@") != -1 || Content.indexOf("~") != -1 || Content.indexOf("`") != -1 ||
                        Content.indexOf("+") != -1 || Content.indexOf("=") != -1 || Content.indexOf("-") != -1 ||
                        Content.indexOf("_") != -1 || Content.indexOf("0") != -1 || Content.indexOf("1") != -1 ||
                        Content.indexOf("2") != -1 || Content.indexOf("3") != -1 || Content.indexOf("4") != -1 ||
                        Content.indexOf("5") != -1 || Content.indexOf("6") != -1 || Content.indexOf("7") != -1 ||
                        Content.indexOf("8") != -1 || Content.indexOf("9") != -1) {
                    continue;
                }
                Word word = new Word(Content, Type, Meaning, Pronunciation, Example);
                WordsManager.insertWord(word);
                // System.out.println(word.toString());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            // e.printStackTrace();
        }

    }

    // /**
    // * main.
    // */
    // public static void main(String[] args) {
    // System.out.println(pathGetter("test.txt"));
    // }

}
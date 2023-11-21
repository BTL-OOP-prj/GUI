package main.java.Core.MCQ;
public class Question {
    private String word;
    private String meaning;

    public Question(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }
}
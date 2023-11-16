package main.java.Core.main_dict;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import main.java.Core.main_dict.Word;

public class WordsManager {
    /**
     * TrieNode.
     */
    public static class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private boolean isWord;
        private Word word;

        /**
         * Constructor.
         */
        public TrieNode() {
            isWord = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }

        /**
         * getChildren.
         */
        public TrieNode[] getChildren() {
            return children;
        }

        /**
         * isWord Getter.
         */
        public boolean isWord() {
            return isWord;
        }

        /**
         * isWord Setter.
         */
        public void setIsWord(boolean isWord) {
            this.isWord = isWord;
        }

        /**
         * word Getter.
         */
        public Word getWord() {
            return word;
        }

        /**
         * word Setter.
         */
        public void setWord(Word word) {
            this.word = word;
        }
    }

    public static TrieNode root = new TrieNode();

    /**
     * insertWord.
     */
    static Word normalizeWord(Word word) {
        Word newWord = new Word(word.getContent(), word.getType(), word.getMeaning(), word.getPronunciation(),
                word.getExample());
        newWord.setContent(newWord.getContent().toLowerCase());
        newWord.setType(newWord.getType().toLowerCase().replace("@", ""));
        newWord.setMeaning(newWord.getMeaning().toLowerCase().replace("@", ""));
        newWord.setPronunciation(newWord.getPronunciation().toLowerCase().replace("@", ""));
        newWord.setExample(newWord.getExample().toLowerCase().replace("@", ""));
        return newWord;
    }

    /**
     * insertWord.
     */
    public static void insertWord(Word raw_word) {
        if (raw_word == null) {
            return;
        }
        Word word = normalizeWord(raw_word);

        TrieNode current = root;
        for (int i = 0; i < word.getContent().length(); i++) {
            char c = word.getContent().charAt(i);
            int index = c - 'a';
            if (current.getChildren()[index] == null) {
                current.getChildren()[index] = new TrieNode();
            }
            current = current.getChildren()[index];
        }
        current.setWord(raw_word);
        current.setIsWord(true);
    }

    /**
     * searchWord.
     * 
     * @param Content String
     * @return Word or null
     */
    public static Word searchWord(String raw_Content) {
        if (raw_Content == null) {
            return null;
        }
        String Content = raw_Content.toLowerCase();
        TrieNode current = root;
        for (int i = 0; i < Content.length(); i++) {
            char c = Content.charAt(i);
            int index = c - 'a';
            if (current.getChildren()[index] == null) {
                return null;
            }
            current = current.getChildren()[index];
        }
        if (!current.isWord()) {
            return null;
        }
        return current.getWord();
    }

    /**
     * suggestions.
     * default prefix = ""
     */
    public static List<Word> suggestions() {
        return suggestions("");
    }

    /**
     * Suggestions.
     */
    public static List<Word> suggestions(String prefix) {
        List<Word> list = new ArrayList<Word>();
        prefix = prefix.toLowerCase();
        Queue<TrieNode> Nodes = new LinkedList<>();
        Nodes.add(root);
        int pIndex = 0;
        while (!Nodes.isEmpty()) {
            TrieNode cur = Nodes.remove();
            if (pIndex < prefix.length()) {
                char c = prefix.charAt(pIndex++);
                int index = c - 'a';
                if (cur.getChildren()[index] == null) {
                    System.err.println("No matching word!");
                    return list;
                } else {
                    Nodes.add(cur.getChildren()[index]);
                }
            } else {
                if (cur.isWord()) {
                    // System.out.println(cur.getWord().getContent());
                    list.add(cur.getWord());
                }
                for (int i = 0; i < 26; i++) {
                    if (cur.getChildren()[i] != null) {
                        Nodes.add(cur.getChildren()[i]);
                    }
                }
            }
        }
        System.err.println("End of Suggestions!");
        return list;
    }

    /**
     * getAllWords.
     */
    public static List<Word> getAllWords() {
        return suggestions();
    }

    /**
     * isEmpty.
     */
    public static boolean isEmpty(TrieNode current) {
        for (int i = 0; i < 26; i++) {
            if (current.getChildren()[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * deleteWord.
     */
    public void deleteWord(TrieNode current, String Content, int depth) {
        if (current == null) {
            return;
        }

        if (depth == Content.length() - 1) {
            if (!current.isWord()) {
                return;
            }
            current.setIsWord(false);
            if (isEmpty(current)) {
                current = null;
            }
            return;
        }

        int index = Content.charAt(depth) - 'a';
        deleteWord(current.getChildren()[index], Content, depth + 1);

        if (isEmpty(current) && current.isWord() == false) {
            current = null;
        }
    }

    /**
     * main.
     */
    // public static void main(String[] args) {
    // Word word = new Word("Hello", "Noun", "Xin chao", "Hello World");
    // WordsManager.insertWord(word);
    // Word word2 = searchWord("Hello");
    // WordsManager.insertWord(new Word("alllo", "Noun", "Xin chao", "Hello
    // World"));
    // WordsManager.insertWord(new Word("bllo", "Noun", "Xin chao", "Hello World"));
    // WordsManager.insertWord(new Word("hellllo", "Noun", "Xin chao", "Hello
    // World"));
    // WordsManager.suggestions("Hel");
    // }
}
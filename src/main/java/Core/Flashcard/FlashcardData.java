package main.java.Core.Flashcard;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FlashcardData {
    private List<Flashcard> flashcards = new ArrayList<>();
    private String filePath = "./src/main/resources/FlashcardData.txt";

    public FlashcardData(String filePath) {
        this.filePath = filePath;
        loadFlashcards();
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
        Collections.sort(flashcards, Comparator.comparing(Flashcard::getQuestion));
        saveFlashcards();
    }

    private void loadFlashcards() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    Flashcard flashcard = new Flashcard(parts[0], parts[1]);
                    flashcards.add(flashcard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFlashcards() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Flashcard flashcard : flashcards) {
                writer.write(flashcard.getQuestion() + ":" + flashcard.getAnswer());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFlashcards(List<Flashcard> flashcards) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Flashcard flashcard : flashcards) {
                writer.write(flashcard.getQuestion() + ":" + flashcard.getAnswer());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

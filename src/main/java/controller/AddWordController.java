package main.java.controller;

import java.net.URL;
import java.util.ResourceBundle;
import main.java.Core.main_dict.Word;
import main.java.Core.main_dict.WordsManager;
import main.java.Core.main_dict.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddWordController implements Initializable {
    @FXML
    private TextField content;

    @FXML
    private TextArea example;

    @FXML
    private TextArea meaning;

    @FXML
    private TextField pronunciation;

    @FXML
    private ChoiceBox<String> type;

    @FXML
    void HandleAddBtn(ActionEvent event) {
        System.out.println(content.getText());
        System.out.println(type.getValue());
        System.out.println(pronunciation.getText());
        System.out.println(meaning.getText());
        System.out.println(example.getText());
        if(content.getText() != null && type.getValue() != null && meaning.getText() != null && pronunciation.getText() != null && example.getText() != null) {
            Word word = new Word(content.getText(), type.getValue(), meaning.getText(), pronunciation.getText(), example.getText());
            DBManager.addWord(word);
            content.setText(null);
            type.setValue(null);
            pronunciation.setText(null);
            meaning.setText(null);
            example.setText(null);
        } else {
            System.out.println("Dữ liệu nhập vào không đạt điều kiện");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] typeWord = {"Noun", "Verb", "Adjective", 
                    "Pronoun", "Adverb", "Preposition",
                    "Conjunction", "Interjection"};
        type.getItems().addAll(typeWord);
    }
    
}

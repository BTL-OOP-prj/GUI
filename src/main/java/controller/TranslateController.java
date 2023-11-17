package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TranslateController implements Initializable {
    @FXML
    private ChoiceBox<String> languageFrom;

    @FXML
    private ChoiceBox<String> languageTo;

    @FXML
    private Label resultPane;

    @FXML
    private TextArea writePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] language = {"en", "vi", "ko", "ja", "ru"};
        String[] languageChoices = {"Tiếng Anh", "Tiếng Việt", "Tiếng Hàn", 
                                    "Tiếng Nhật", "Tiếng Nga"};
        languageFrom.getItems().addAll(languageChoices);
        languageTo.getItems().addAll(languageChoices);        
    }
}

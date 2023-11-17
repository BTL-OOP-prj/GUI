package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;
import com.sun.net.httpserver.Authenticator.Result;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import main.java.Core.API.APITranslator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class TranslateController implements Initializable {
    private String[] language = {"en", "vi", "ko", "ja", "ru"};
    private String[] languageChoices = {"Tiếng Anh", "Tiếng Việt", "Tiếng Hàn", 
                                    "Tiếng Nhật", "Tiếng Nga"};

    @FXML
    private ChoiceBox<String> languageFrom;

    @FXML
    private ChoiceBox<String> languageTo;

    @FXML
    private Label resultPane;

    @FXML
    private TextArea writePane;

    @FXML
    void HandleClickTranslateBtn(ActionEvent event) throws IOException {
        String text = writePane.getText();
        String langFrom = languageFrom.getValue();
        for (int i = 0; i < languageChoices.length; i++) {
            if(languageChoices[i].equals(langFrom)) {
                langFrom = language[i];
                break;
            }
        }
        String langTo = languageTo.getValue();
        for (int i = 0; i < languageChoices.length; i++) {
            if(languageChoices[i].equals(langTo)) {
                langTo = language[i];
                break;
            }
        }
        System.out.println(langFrom + ' ' + langTo + ' ' + text);
        resultPane.setText(APITranslator.translate(langFrom, langTo, text));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        languageFrom.getItems().addAll(languageChoices);
        languageTo.getItems().addAll(languageChoices);
        languageFrom.setValue("Tiếng Anh");
        languageTo.setValue("Tiếng Việt");
    }
}

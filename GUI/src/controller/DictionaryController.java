package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import CML.Java.main_dict.Word;
import CML.Java.main_dict.WordsManager;
import CML.Java.main_dict.dbToManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class DictionaryController implements Initializable{
    
    @FXML
    private Label contentLable;

    @FXML
    private Label exampleLable;

    @FXML
    private Label meaningLable;

    @FXML
    private Label pronunciationLable;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchBtn;

    @FXML
    private ImageView soundBtn;

    @FXML
    private Label typeLable;

    public void displayContent(Word word) {
        contentLable.setText(word.getContent());
    }

    public void displayType(Word word) {
        typeLable.setText(word.getType());
    }

    public void displayPronunciation(Word word) {
        pronunciationLable.setText(word.getPronunciation());
    }

    public void displayMeaning(Word word) {
        meaningLable.setText(word.getMeaning());
    }

    public void displayExample(Word word) {
        pronunciationLable.setText(word.getExample());
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    public void HandleSearchBtn(ActionEvent e) throws IOException {
        String target = searchBox.getText();
        System.out.println(target);
        Word word = WordsManager.searchWord(target);
        System.out.println(word.getMeaning());
        displayContent(word);
        displayType(word);
        displayPronunciation(word);
        displayMeaning(word);
        displayExample(word);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WordsManager.suggestions("al");
        System.out.println("hehe");
    }
}

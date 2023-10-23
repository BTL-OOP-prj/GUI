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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    @FXML
    private ScrollPane examplePane;

    public void displayContent(Word word) {
        contentLable.setText(word.getContent());
    }

    public void displayType(Word word) {
        typeLable.setText(word.getType());
    }

    public void displayPronunciation(Word word) {
        pronunciationLable.setWrapText(true);
        pronunciationLable.setText(word.getPronunciation());
    }

    public void displayMeaning(Word word) {
        meaningLable.setMaxHeight(500);
        meaningLable.setWrapText(true);
        System.out.println("before: " + meaningLable.getHeight());
        meaningLable.setText(word.getMeaning());
        System.out.println("after: " + meaningLable.getHeight());
    }

    public void displayExample(Word word) {
        AnchorPane.setTopAnchor(examplePane, meaningLable.getLayoutY() + meaningLable.getHeight());
        System.out.println(meaningLable.getLayoutY() + " - " +  meaningLable.getHeight());
        System.out.println(examplePane.getLayoutY());
        exampleLable.setText(word.getExample());
        exampleLable.setWrapText(true);
        exampleLable.setMaxHeight(10000);
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

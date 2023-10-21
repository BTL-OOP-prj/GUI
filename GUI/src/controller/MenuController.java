package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import CML.Java.main_dict.Word;
import CML.Java.main_dict.WordsManager;
import CML.Java.main_dict.dbToManager;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MenuController implements Initializable{
    @FXML
    private AnchorPane container;

    @FXML
    private Button dictionaryBtn;

    @FXML
    private Button flashcardBtn;

    @FXML
    private Button translateBtn;

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add(node);
    }
    
    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            setNode(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToDictionary(ActionEvent e) throws IOException {
        showComponent("../views/DictionaryUI.fxml");
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToTranslate(ActionEvent e) throws IOException {
        showComponent("../views/Translate.fxml");
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToFlashcard(ActionEvent e) throws IOException {
        showComponent("../views/Flashcard.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showComponent("../views/DictionaryUI.fxml");
        dbToManager.scan("/GUI/src/CML/DB/Eng.csv");
    }
}


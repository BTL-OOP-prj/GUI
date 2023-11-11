package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import main.java.Core.main_dict.Word;
import main.java.Core.main_dict.WordsManager;
import main.java.Core.main_dict.dbToManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
        showComponent("../java_dict/DictionaryUI.fxml");
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToTranslate(ActionEvent e) throws IOException {
        showComponent("../java_dict/TranslateUI.fxml");
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToFlashcard(ActionEvent e) throws IOException {
        showComponent("../java_dict/FlashcardUI.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbToManager.scan("/GUI/src/main/java/Core/DB/Eng.csv");
        showComponent("../java_dict/DictionaryUI.fxml");
    }
}


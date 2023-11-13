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
            System.out.println(getClass().getResource(path));
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
        showComponent("../../resources/assets/DictionaryUI.fxml");
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToTranslate(ActionEvent e) throws IOException {
        showComponent("../../resources/assets/TranslateUI.fxml");
    }

    /**
     * @param event
     * @throws IOException
     */
    public void switchToFlashcard(ActionEvent e) throws IOException {
        showComponent("../../resources/assets/FlashcardUI.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(getClass().getResource("src/main/resources/Eng.txt"));
        dbToManager.scan("/src/main/resources/Eng.txt");
        System.out.println(getClass().getResource("../resources/assets/DictionaryUI.fxml"));
        showComponent("../../resources/assets/DictionaryUI.fxml");
    }
}


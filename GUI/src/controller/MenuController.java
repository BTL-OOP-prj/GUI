package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
    private Button Menu;

    @FXML
    private Button MenuBack;

    @FXML
    private AnchorPane container;

    @FXML
    private JFXButton dictionaryBtn;

    @FXML
    private Tooltip dictionaryTooltip;

    @FXML
    private JFXButton flashcardBtn;

    @FXML
    private Tooltip flashcardTooltip;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton translateBtn;

    @FXML
    private Tooltip translateTooltip;
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
        showComponent("../views/Dictionary.fxml");
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
        showComponent("../views/Dictionary.fxml");
        dictionaryTooltip.setShowDelay(Duration.seconds(0.5));
        translateTooltip.setShowDelay(Duration.seconds(0.5));
        flashcardTooltip.setShowDelay(Duration.seconds(0.5));

        slider.setTranslateY(1000);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToY(0);
            slide.play();

            //slider.setTranslateY(1000);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToY(1000);
            slide.play();

            slider.setTranslateY(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });
    }
}


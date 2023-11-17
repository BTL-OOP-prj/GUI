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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TranslateController implements Initializable {
    private static final String VOICE_KEY = "freetts.voices";
    private static final String VOICE_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String VOICE_NAME = "kevin16";

    @FXML
    private TextArea writePane;

    @FXML
    private Label resultPane;

    @FXML
    void HandleClickSoundBtn1(MouseEvent event) {
        writePane.setOnKeyReleased(e -> {
            if (!writePane.getText().isEmpty()) {
                System.out.println(writePane.getText());
                // String target = writePane.getText();
                // System.setProperty(VOICE_KEY, VOICE_VALUE);
                // Voice voice = VoiceManager.getInstance().getVoice(VOICE_NAME);
                // if (voice != null) {
                //     voice.allocate();
                //     voice.speak(target);
                //     voice.deallocate();
                // } else {
                //     throw new IllegalStateException("Cannot find voice: " + VOICE_NAME);
                // }
            }
        });
    }

    // @FXML
    // void HandleClickSoundBtn2(MouseEvent event) {
    //     if (!resultPane.getText().isEmpty()) {
    //         String target = resultPane.getText();
    //         System.setProperty(VOICE_KEY, VOICE_VALUE);
    //         Voice voice = VoiceManager.getInstance().getVoice(VOICE_NAME);
    //         if (voice != null) {
    //             voice.allocate();
    //             voice.speak(target);
    //             voice.deallocate();
    //         } else {
    //             throw new IllegalStateException("Cannot find voice: " + VOICE_NAME);
    //         }
    //     }
    // }

    // @FXML
    // void HandleClickSpeechBtn(MouseEvent event) {

    // }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(writePane.getText().isEmpty());
        if(!writePane.getText().isEmpty()) {
            System.out.println(writePane.getText());
        }

        writePane.setOnKeyReleased(e -> {
            if (!writePane.getText().isEmpty()) {
                System.out.println(writePane.getText());
            }
        });
    }
}

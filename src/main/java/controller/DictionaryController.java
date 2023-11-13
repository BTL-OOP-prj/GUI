package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import main.java.Core.main_dict.Word;
import main.java.Core.main_dict.WordsManager;
import main.java.Core.main_dict.dbToManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DictionaryController implements Initializable{
    private static final String VOICE_KEY = "freetts.voices";
    private static final String VOICE_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String VOICE_NAME = "kevin16";
    
    @FXML
    private Label contentLable;

    @FXML
    private Label pronunciationLable;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button editBtn;

    @FXML
    private ImageView soundBtn;

    @FXML
    private Label typeLable;

    @FXML
    private TextArea explainArea;

    @FXML
    private ListView<String> suggestion;

    private String target = "";

    ObservableList<String> list = FXCollections.observableArrayList();

    public void displayContent(Word word) {
        contentLable.setText(word.getContent());
    }

    public void displayType(Word word) {
        typeLable.setText(word.getType());
    }

    public void displayPronunciation(Word word) {
        pronunciationLable.setText(word.getPronunciation());
    }

    public void displayExplain(Word word) {
        String explain = word.getMeaning() + '\n' + '\n' + word.getExample();
        explainArea.setText(explain);
    }

    private void displayWord(Word word) {
        displayContent(word);
        displayType(word);
        displayPronunciation(word);
        displayExplain(word);
    }

    private void handleOnKeyTyped() {
        list.clear();
        String searchWord = searchBox.getText();
        List<Word> recWordList = WordsManager.suggestions(searchWord);;


        for (Word word : recWordList) {
            list.add(word.getContent());
        }
//        for (int i = 0; i <= NUM_OF_WORDS; i++) {
//            if (i < recWordList.size()) {
//                list.add(recWordList.get(i).getWordTarget());
//            }
//        }

        if (list.isEmpty()) {
            //notAvailable.setVisible(true);
            suggestion.setItems(list);
        } else {
            //notAvailable.setVisible(false);
            suggestion.setItems(list);

        }
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    private void HandleSearchBtn(ActionEvent e) throws IOException {
        if(!searchBox.getText().isEmpty()) {
            target = searchBox.getText();
        }
        System.out.println(target);
        Word word = WordsManager.searchWord(target);
        System.out.println(word.getMeaning());
        displayWord(word);
    }

    @FXML
    private void HandleMouseClickListView(MouseEvent e) {
        String selectedWord = suggestion.getSelectionModel().getSelectedItem();
        Word word = WordsManager.searchWord(selectedWord);
        target = word.getContent();
        displayWord(word);
//        System.out.println(word.getWordTarget() + " " + word.isFavorite());
    }

    @FXML
    private void HandleClickEditBtn(ActionEvent e) {
        explainArea.setEditable(true);
        editBtn.setVisible(false);
        saveBtn.setVisible(true);
    }

    @FXML
    void HandleMouseClickSoundBtn(MouseEvent event) {
        System.setProperty(VOICE_KEY, VOICE_VALUE);
        Voice voice = VoiceManager.getInstance().getVoice(VOICE_NAME);
        if (voice != null) {
            voice.allocate();
            voice.speak(target);
            voice.deallocate();
        } else {
            throw new IllegalStateException("Cannot find voice: " + VOICE_NAME);
        }
    }

    @FXML
    void HandleClickSaveBtn(ActionEvent event) {
        explainArea.setEditable(false);
        saveBtn.setVisible(false);
        editBtn.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        explainArea.setEditable(false);
        saveBtn.setVisible(false);
        handleOnKeyTyped();
        searchBox.setOnKeyReleased(e -> {
            if (searchBox.getText().isEmpty()) {
                //setListDefault();
            } else {
                handleOnKeyTyped();
            }
        });
        // WordsManager.suggestions("al");
        // System.out.println("hehe");
    }
}

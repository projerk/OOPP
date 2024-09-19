package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.nio.file.Paths;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.File;
import org.json.JSONObject;
import model.DBHelper;
import model.Word;
import model.Type;
import model.Meaning;
import model.Example;
import tts.Speaker;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import model.Trie;
import app.Projerk;


public class DictionaryController {
    @FXML
    private TextField word;

    @FXML
    private HBox wordPhonetic;

    @FXML
    private VBox wordContent;

    @FXML
    private VBox border;    

    @FXML
    private HBox bookmarkVoice;

    private Speaker speaker = Speaker.getInstance();

    private Trie trie = Trie.getInstance();

    private Projerk app = Projerk.getInstance();

    @FXML
    private void initialize() {
        List<String> allSuggestions = new ArrayList<>();
        AtomicReference<AutoCompletionBinding<String>> autoCompletionBinding = new AtomicReference<>(
                TextFields.bindAutoCompletion(word, allSuggestions));

        word.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                autoCompletionBinding.get().dispose(); 
                autoCompletionBinding.set(TextFields.bindAutoCompletion(word, new ArrayList<>()));  
            } else {
                autoCompletionBinding.get().dispose();
                List<String> suggestions = new ArrayList<>();
                autoCompletionBinding.set(TextFields.bindAutoCompletion(word, suggestions));
                suggestions = trie.getTopWordsWithPrefix(newValue);
            
                autoCompletionBinding.set(TextFields.bindAutoCompletion(word, suggestions));  // Tạo binding mới với danh sách gợi ý đã lọc
                autoCompletionBinding.get().setUserInput(newValue); 
            }
        });
    }

    @FXML
    public void handleSearchButton() {
        String target = word.getText();

        wordPhonetic.getChildren().clear();
        wordContent.getChildren().clear();
        bookmarkVoice.getChildren().clear();
        border.setStyle("-fx-pref-height: 1px; -fx-background-color: #494d51;");
        JSONObject json = DBHelper.searchWord(target);

        if (json.has("word")) {
            Word word = parse(json);
            ArrayList<Type> types =  word.getTypeArray();
            Button speechButtonUK = new Button();
            Button speechButtonUS = new Button();
            String path = Paths.get("src", "main", "resources","view","images","speaker.png").toAbsolutePath().toString();
            File file = new File(path);
            Image image = new Image(file.toURI().toString());
            ImageView imageViewUS = new ImageView(image);
            ImageView imageViewUK = new ImageView(image);
            imageViewUK.setFitWidth(30);
            imageViewUK.setFitHeight(30);
            speechButtonUK.setGraphic(imageViewUK);
            speechButtonUK.setText("UK");
            speechButtonUK.setStyle("-fx-background-color: transparent; -fx-text-fill: #e9e9ea; -fx-font-family: 'Verdana';");
            speechButtonUK.setOnAction(event -> {speaker.speak(word.getWord());});
            
            imageViewUS.setFitWidth(30);
            imageViewUS.setFitHeight(30);
            speechButtonUS.setGraphic(imageViewUS);
            speechButtonUS.setText("US");
            speechButtonUS.setStyle("-fx-background-color: transparent; -fx-text-fill: #e9e9ea; -fx-font-family: 'Verdana';");
            speechButtonUS.setOnAction(event -> {speaker.speak(word.getWord());});

            Label wordText = new Label();
            Label phoneticText = new Label();
            wordText.setText(word.getWord());
            phoneticText.setText(word.getPhonetic());
            wordText.setStyle("-fx-font-size: 22px; -fx-text-fill: #e9e9ea; -fx-font-family: 'Verdana'; ");
            phoneticText.setStyle("-fx-font-size: 17px; -fx-text-fill: #bebec2; -fx-font-family: 'Verdana'; ");

            bookmarkVoice.getChildren().add(speechButtonUK);
            bookmarkVoice.getChildren().add(speechButtonUS);
            wordPhonetic.getChildren().add(wordText);
            wordPhonetic.getChildren().add(phoneticText);
            for(Type type : types) {
                Label typeText = new Label();
                typeText.setText(type.getType());
                typeText.setStyle("-fx-background-color: #148761; -fx-font-family: 'Verdana'; -fx-padding: 5px; -fx-text-fill: #e9e9ea; -fx-font-size: 14px; -fx-background-radius: 12;");
                wordContent.getChildren().add(typeText);

                ArrayList<Meaning> meanings = type.getMeaningArray();

                for (Meaning meaning : meanings) {
                    Label meaningText = new Label();

                    HBox meaningContainer = new HBox();
                    HBox coloredBlank = new HBox();
                    coloredBlank.setStyle("-fx-background-color: #148761; -fx-pref-width: 10px");
                    meaningContainer.setSpacing(10);

                    meaningText.setText(meaning.getMeaning());
                    meaningText.setStyle("-fx-font-family: 'Verdana'; -fx-padding: 5px; -fx-text-fill: #e9e9ea; -fx-font-size: 15px;");
                    meaningContainer.getChildren().add(coloredBlank);
                    meaningContainer.getChildren().add(meaningText);
                    wordContent.getChildren().add(meaningContainer);

                    ArrayList<Example> examples = meaning.getExampleArray();

                    for (Example example : examples) {
                        Label exampleTextEnglish = new Label();
                        Label exampleTextVietnamese = new Label();
                        HBox exampleEnglishContainer = new HBox();
                        HBox exampleVietnameseContainer = new HBox();
                        HBox transparentBlank1 = new HBox();
                        HBox transparentBlank2 = new HBox();
                        transparentBlank1.setStyle("-fx-pref-width: 35px;");
                        transparentBlank2.setStyle("-fx-pref-width: 35px;");

                        exampleEnglishContainer.getChildren().add(transparentBlank1);
                        exampleVietnameseContainer.getChildren().add(transparentBlank2);

                        exampleTextEnglish.setText(example.getEnglishPart());
                        exampleTextVietnamese.setText(example.getVietnamesePart());

                        exampleTextVietnamese.setStyle("-fx-font-size: 12px; -fx-text-fill: #bebec2; -fx-font-family: 'Verdana';");
                        exampleTextEnglish.setStyle("-fx-font-size: 12px; -fx-text-fill: #e9e9ea; -fx-font-family: 'Verdana';");

                        exampleEnglishContainer.getChildren().add(exampleTextEnglish);
                        exampleVietnameseContainer.getChildren().add(exampleTextVietnamese);

                        wordContent.getChildren().add(exampleEnglishContainer);
                        wordContent.getChildren().add(exampleVietnameseContainer);
                    }
                }
            }
        }
        else {
            System.out.println("Error");
        }
    }

    /**
     * This function is used to parse the data from meaning in database,
     * as the types, meanings and examples are not separate.
     * 
     * @param target A json object contain 3 key: word, phonetic and meanings
     * @return An instance of Word, which contain data of word, phonetic,
     * list of types, list of meanings, and list of examples.
     */
    private Word parse(JSONObject target) {
        Word word = new Word();
        word.setWord(target.getString("word"));
        word.setPhonetic(target.getString("pronunciation"));
        Word currWord = word;
        Type currType = null;
        Meaning currMeaning = null;
        String[] lines = target.getString("meaning").split("\n");

        for (String line : lines) {
            if (line.startsWith("*")) {
                String res = extractType(line);
                Type type = new Type();
                if (res.equals("�ộng từ")) {
                    res = "động từ";
                }
                type.setType(res);
                currType = type;
                currWord.addType(type);
            }
            else if (line.startsWith("-")) {
                String res = extractMeaning(line);
                Meaning meaning = new Meaning();
                meaning.setMeaning(res);
                currMeaning = meaning;
                currType.addMeaning(meaning);
            }
            else if (line.startsWith("=")) {
                String[] res = extractExample(line);
                Example example = new Example();
                if (res.length > 0) {
                    example.setEnglishPart(res[0]);
                }
                if (res.length > 1) {
                    example.setVietnamesePart(res[1]);
                }
                currMeaning.addExample(example);
            }
        }
        return word;
    }

    private String extractType(String line) {
        return line.substring(1).trim();
    }

    private String extractMeaning(String line) {
        return line.substring(1).trim();
    }

    private String[] extractExample(String line) {
        String[] parts = line.substring(1).split("\\+", 2);
        String englishExample = parts[0].trim();
        String vietnameseExample = parts.length > 1 ? parts[1].trim() : "";
        return new String[]{englishExample, vietnameseExample};
    }
}
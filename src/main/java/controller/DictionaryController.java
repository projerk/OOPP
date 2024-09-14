package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.nio.file.Paths;
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

    @FXML
    private void initialize() {

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
            Button speechButton = new Button();
            String path = Paths.get("src", "main", "resources","view","images","speaker.png").toAbsolutePath().toString();
            File file = new File(path);
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            speechButton.setGraphic(imageView);
            speechButton.setStyle("-fx-background-color: transparent");
            speechButton.setOnAction(event -> {speaker.speak(word.getWord());});

            Label wordText = new Label();
            Label phoneticText = new Label();
            wordText.setText(word.getWord());
            phoneticText.setText(word.getPhonetic());
            wordText.setStyle("-fx-font-size: 22px; -fx-text-fill: #e9e9ea; -fx-font-family: 'Verdana'; ");
            phoneticText.setStyle("-fx-font-size: 17px; -fx-text-fill: #bebec2; -fx-font-family: 'Verdana'; ");

            bookmarkVoice.getChildren().add(speechButton);
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
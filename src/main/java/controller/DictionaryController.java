package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;
import model.Person;
import model.DBHelper;
import model.Word;
import model.Type;
import model.Meaning;
import model.Example;
import java.util.ArrayList;

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
    private void initialize() {

    }

    @FXML
    public void handleSearchButton() {
        String target = word.getText();

        wordPhonetic.getChildren().clear();
        wordContent.getChildren().clear();
        border.setStyle("-fx-pref-height: 1px; -fx-background-color: #494d51;");
        JSONObject json = DBHelper.searchWord(target);

        if (json.has("word")) {
            Word word = parse(json);
            ArrayList<Type> types =  word.getTypeArray();

            Label wordText = new Label();
            Label phoneticText = new Label();
            wordText.setText(word.getWord());
            phoneticText.setText(word.getPhonetic());
            wordText.setStyle("-fx-font-size: 20px; -fx-text-fill: #e9e9ea; -fx-font-family: 'Verdana'; ");
            phoneticText.setStyle("-fx-font-size: 16px; -fx-text-fill: #bebec2; -fx-font-family: 'Verdana'; ");

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
                    coloredBlank.setStyle("-fx-backgound-color: #148761; -fx-pref-width: 20px");
                    meaningContainer.setSpacing(10);

                    meaningText.setText(meaning.getMeaning());
                    meaningText.setStyle("-fx-font-family: 'Verdana'; -fx-padding: 5px; -fx-text-fill: #e9e9ea; -fx-font-size: 12px;");
                    meaningContainer.getChildren().add(coloredBlank);
                    meaningContainer.getChildren().add(meaningText);
                    wordContent.getChildren().add(meaningContainer);

                    ArrayList<Example> examples = meaning.getExampleArray();

                    for (Example example : examples) {
                        Label exampleTextEnglish = new Label();
                        Label exampleTextVietnamese = new Label();
                        exampleTextEnglish.setText(example.getEnglishPart());
                        exampleTextVietnamese.setText(example.getVietnamesePart());

                        wordContent.getChildren().add(exampleTextEnglish);
                        wordContent.getChildren().add(exampleTextVietnamese);
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
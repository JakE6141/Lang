package lang.langlearn;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HelloController {
    @FXML
    private TextField langTextBox;
    @FXML
    private TextField ansBox;
    @FXML
    private AnchorPane background;
    @FXML
    private Text label;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    private double nextY = 100;
    private double y = 100;
    private ArrayList<String> enteredWords = new ArrayList<>();
    private ArrayList<String> translatedWords = new ArrayList<>();

    //switch to spelling screen
    @FXML
    public void switchToSpellingScreen(MouseEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("spelling-screen.fxml"));
        Parent root = loader.load();
        String css = getClass().getResource("style.css").toExternalForm(); //with css
        scene = new Scene(root,1000, 700);
        scene.getStylesheets().add(css);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //switch to translation screen
    public void switchToTranslationScreen(MouseEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("translation-screen.fxml"));
        Parent root = loader.load();
        String css = getClass().getResource("style.css").toExternalForm();
        scene = new Scene(root,1000, 700);
        scene.getStylesheets().add(css);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //user enter pressed
    @FXML
    private void enterPressed(javafx.scene.input.KeyEvent event){
        String text = langTextBox.getText().trim();
        if (event.getCode() == KeyCode.ENTER) {
            if(!text.isEmpty()) {
                createSquare(text); //makes english square
                storeWords(text); //store in arr list
                langTextBox.clear();
            }

        }
    }

    //break the two words into array and see if translation matches(submit button)
    public void parseAndStore(){
        String parts[] = ansBox.getText().split(":");
        if(parts.length == 2){
            String englishWord = parts[0].trim();
            String translationWord = parts[1].trim();

            if (translated(englishWord).equalsIgnoreCase(translationWord)) {

                label.setText("Correct");
                label.setFill(Color.GREEN);
            }
            else{
                label.setText("Incorrect, try again");
                label.setFill(Color.RED);
            }

        }
    }

    //general translate method calling API
    private String translated(String englishWord){//general translation
        String translatedText = LangCall.getTranslation(englishWord, "spa");
        return translatedText;
    }



    //translate each word in array list and display when button clicked
    @FXML
    private void translateAndStore(){
        translatedWords.clear();
        for(String word:enteredWords){
            String translatedText = LangCall.getTranslation(word, "spa");//api
            storeTranslatedWord(translatedText);
        }
        displayTranslatedWords();
    }

    //add word to array list
    @FXML
    private void storeWords(String word){
        enteredWords.add(word);
    }
    //add translated words to array list
    @FXML
    private void storeTranslatedWord(String translatedWord){
        translatedWords.add(translatedWord);
    }

    //creating each square
    private void createSquare(String text) {
        Rectangle square = new Rectangle(200, 40, Color.LIGHTBLUE);
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(2);

        Text textNode = new Text(text);
        textNode.setFill(Color.BLACK);
        textNode.setFont(new Font(16));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(square, textNode); //for stacking text on rectangle

        stackPane.setLayoutX(20); // adjust as needed for horizontal positioning

        stackPane.setLayoutY(nextY); // set the Y position directly
        nextY += 50; // increment nextY for the next square

        background.getChildren().add(stackPane); //add to bg
    }

    //make square for translated words
    private void createTranslatedSquare(String text) {
            Rectangle square = new Rectangle(200, 40, Color.LIGHTBLUE);
            square.setStroke(Color.BLACK);
            square.setStrokeWidth(2);


            Text textNode = new Text(text);
            textNode.setFill(Color.BLACK);
            textNode.setFont(new Font(16));

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(square, textNode);//stack square and text on top


            stackPane.setLayoutX(700);
            stackPane.setLayoutY(y);//100

            background.getChildren().add(stackPane);


            y += 50; // adjust the increment as needed for spacing

    }

    //display words randomly across from english words
    private void displayTranslatedWords(){
        Collections.shuffle(translatedWords);//random order
        for (String translatedWord:translatedWords){
            createTranslatedSquare(translatedWord);
        }
    }


}



package lang.langlearn;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SpellingScreen {
    @FXML
    private TextField wordBox;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Text englishWord;
    @FXML
    private TextField wordBox1;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text translatedWord;


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


    public void switchToMatching(MouseEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        String css = getClass().getResource("style.css").toExternalForm();
        scene = new Scene(root,1000, 700);
        scene.getStylesheets().add(css);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private String translated(String englishWord){//general translation
        String translatedText = LangCall.getTranslation(englishWord, "spa");
        return translatedText;
    }
    @FXML //set text
    public void getText() {
        String input = wordBox.getText();
        englishWord.setText(input);
    }

    int attempt = 0;//above so not 0 every button press
    public void translateAns() {
        String input = wordBox.getText();
        String ans = translated(input);
        String attemptInput = wordBox1.getText();


        if (attemptInput.equalsIgnoreCase(ans)) {
            text1.setText("Correct!");
            text1.setFill(Color.GREEN);
            text2.setText("Correct!");
            text2.setFill(Color.GREEN);
        }
        else {

                attempt++;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.setLength(0);//reset length
                for (int i = 0; i < attempt; i++) {
                    stringBuilder.append(ans.charAt(i));
                }
                translatedWord.setText(stringBuilder.toString());//show one letter at a time

                String check = translatedWord.getText();// if full word is displayed
                if(check.equalsIgnoreCase(ans)){
                    text1.setText("Wrong");
                    text1.setFill(Color.RED);
                    text2.setText("Wrong");
                    text2.setFill(Color.RED);
                }
        }

    }




}

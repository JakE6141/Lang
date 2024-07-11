package lang.langlearn;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TranslationScreen {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private TextField wordBox;
    @FXML
    private Text displayText;

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

    public void translateWord(){
        String input = wordBox.getText();
        String translation = translated(input);
        displayText.setText(translation);
    }

    @FXML
    public void switchToSpellingScreen(MouseEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("spelling-screen.fxml"));
        Parent root = loader.load();
        String css = getClass().getResource("style.css").toExternalForm();
        scene = new Scene(root,1000, 700);
        scene.getStylesheets().add(css);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

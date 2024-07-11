module lang.langlearn {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens lang.langlearn to javafx.fxml;
    exports lang.langlearn;
}
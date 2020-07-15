package eu.javaspecialists.courses.juppies2.anagrams.ui.fxml;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.*;

/**
 * Main window of the Anagram Game application.
 */
public class Anagrams extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Anagrams");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("anagrams.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("styles.css");

        stage.setScene(scene);
        stage.show();
    }

}

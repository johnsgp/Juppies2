/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/* Anagram Game Application */

package eu.javaspecialists.courses.juppies2.anagrams.ui;

import eu.javaspecialists.courses.juppies2.anagrams.lib.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * Main window of the Anagram Game application.
 */
public class AnagramsFx extends Application {

    private GridPane mainPane;
    private TextField scrambledWord;
    private TextField guessedWord;
    private Label feedbackLabel;
    private Button guessButton;
    private Button nextTrial;

    private static final int GAP = 12;

    private int wordIdx = 0;
    private WordLibrary wordLibrary;

    /**
     * Creates new form Anagrams
     */
    public AnagramsFx() {
        wordLibrary = WordLibraries.createDefaultWordLibrary();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Anagrams");

        var mainMenu = getMenu();
        mainPane = getMain();
        var buttonsPane = getButtons();

        var layout = new BorderPane();
        layout.setTop(mainMenu);
        layout.setCenter(mainPane);
        layout.setBottom(buttonsPane);

        var scene = new Scene(layout, 360, 200);
        scene.getStylesheets().add("styles.css");

        scrambledWord.setText(wordLibrary.getScrambledWord(wordIdx));
        guessedWord.requestFocus();
        guessButton.setDefaultButton(true);

        stage.setScene(scene);
        stage.show();
    }

    private MenuBar getMenu() {
        var mainMenu = new MenuBar();

        var fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);

        var about = new Label("_About");
        about.setMnemonicParsing(true);
        CustomMenuItem aboutMenuItem = new CustomMenuItem(about);
        aboutMenuItem.setMnemonicParsing(true);
        Tooltip.install(aboutMenuItem.getContent(), new Tooltip("About"));
        aboutMenuItem.setOnAction(evt -> aboutMenuItemActionPerformed(evt));
        fileMenu.getItems().add(aboutMenuItem);

        var exit = new Label("_Exit");
        exit.setMnemonicParsing(true);
        CustomMenuItem exitMenuItem = new CustomMenuItem(exit);
        exitMenuItem.setMnemonicParsing(true);
        Tooltip.install(exitMenuItem.getContent(), new Tooltip("Quit Team, Quit!"));
        exitMenuItem.setOnAction(evt -> exitMenuItemActionPerformed(evt));
        fileMenu.getItems().add(exitMenuItem);

        mainMenu.getMenus().add(fileMenu);
        return mainMenu;
    }

    private GridPane getMain() {
        var mainPane = new GridPane();
        mainPane.setPadding(new Insets(GAP));
        mainPane.setVgap(GAP);
        var column1 = new ColumnConstraints(110);
        var column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        mainPane.getColumnConstraints().addAll(column1, column2);

        var scrambledLabel = new Label("Scrambled Word:");
        mainPane.add(scrambledLabel, 0, 0);

        scrambledWord = new TextField();
        scrambledWord.setPrefColumnCount(20);
        scrambledWord.setEditable(false);
        scrambledWord.setFocusTraversable(false);
        mainPane.add(scrambledWord, 1, 0);

        var guessLabel = new Label("_Your Guess:");
        guessLabel.setMnemonicParsing(true);
        guessLabel.setLabelFor(guessedWord);
        mainPane.add(guessLabel, 0, 1);

        guessedWord = new TextField();
        guessedWord.setPrefColumnCount(20);
        mainPane.add(guessedWord, 1, 1);

        feedbackLabel = new Label();
        mainPane.add(feedbackLabel, 1, 2);

        return mainPane;
    }

    private HBox getButtons() {
        var buttonsPane = new HBox();
        buttonsPane.setPadding(new Insets(GAP));
        buttonsPane.setSpacing(GAP);
        buttonsPane.setAlignment(Pos.CENTER_RIGHT);

        guessButton = new Button();
        guessButton.setText("_Guess");
        guessButton.setMnemonicParsing(true);
        guessButton.setTooltip(new Tooltip("Guess the scrambled word."));
        guessButton.setOnAction(evt -> guessedWordActionPerformed(evt));
        buttonsPane.getChildren().add(guessButton);

        nextTrial = new Button();
        nextTrial.setText("_New Word");
        nextTrial.setMnemonicParsing(true);
        nextTrial.setTooltip(new Tooltip("Fetch a new word."));
        nextTrial.setOnAction(evt -> nextTrialActionPerformed(evt));
        buttonsPane.getChildren().add(nextTrial);

        return buttonsPane;
    }

    private void aboutMenuItemActionPerformed(ActionEvent evt) {
        new AboutFx(mainPane.getBackground())
                .showAndWait();
    }

    private void nextTrialActionPerformed(ActionEvent evt) {
        wordIdx = (wordIdx + 1) % wordLibrary.getSize();

        feedbackLabel.setText(" ");
        scrambledWord.setText(wordLibrary.getScrambledWord(wordIdx));
        guessedWord.setText("");
        guessButton.setDefaultButton(true);
        nextTrial.setDefaultButton(false);

        guessedWord.requestFocus();
    }

    private void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void guessedWordActionPerformed(ActionEvent evt) {
        if (wordLibrary.isCorrect(wordIdx, guessedWord.getText())) {
            feedbackLabel.setText("Correct! Try a new word!");
            guessButton.setDefaultButton(false);
            nextTrial.setDefaultButton(true);
            nextTrial.requestFocus();
        } else {
            feedbackLabel.setText("Incorrect! Try again!");
            guessedWord.setText("");
            guessedWord.requestFocus();
        }
    }
}

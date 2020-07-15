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

package eu.javaspecialists.courses.juppies2.anagrams.ui.fxml;

import eu.javaspecialists.courses.juppies2.anagrams.lib.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class AnagramsController implements Initializable {

    @FXML
    private GridPane mainPane;
    @FXML
    private TextField scrambledWord;
    @FXML
    private TextField guessedWord;
    @FXML
    private Label feedbackLabel;
    @FXML
    private Button guessButton;
    @FXML
    private Button nextTrial;

    private int wordIdx = 0;
    private WordLibrary wordLibrary;

    public AnagramsController() {
        wordLibrary = WordLibraries.createDefaultWordLibrary();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrambledWord.setText(wordLibrary.getScrambledWord(wordIdx));
        guessedWord.requestFocus();
        guessButton.setDefaultButton(true);
    }

    @FXML
    private void aboutMenuItemActionPerformed(ActionEvent evt) throws IOException {
        new About(mainPane.getBackground())
                .showAndWait();
    }

    @FXML
    private void nextTrialActionPerformed(ActionEvent evt) {
        wordIdx = (wordIdx + 1) % wordLibrary.getSize();

        feedbackLabel.setText(" ");
        scrambledWord.setText(wordLibrary.getScrambledWord(wordIdx));
        guessedWord.setText("");
        guessButton.setDefaultButton(true);
        nextTrial.setDefaultButton(false);

        guessedWord.requestFocus();
    }

    @FXML
    private void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    @FXML
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

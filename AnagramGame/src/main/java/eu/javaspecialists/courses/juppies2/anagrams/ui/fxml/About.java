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

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.*;

/**
 * About dialog of the Anagram Game application.
 */
public class About extends Dialog<Void> {
    /**
     * Creates new form About
     *
     * @param background
     */
    public About(Background background) throws IOException {
        setTitle("About Anagrams");
        setResizable(true);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));

        setDialogPane(fxmlLoader.load());
        getDialogPane().getStylesheets().add("styles.css");

        fxmlLoader.<AboutController>getController().setBackground(background);
    }
}

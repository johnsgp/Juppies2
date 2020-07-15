module eu.javaspecialists.courses.juppies2.anagrams {
    requires java.desktop;
    requires java.logging;

    requires javafx.controls;
    requires javafx.fxml;

    opens eu.javaspecialists.courses.juppies2.anagrams.ui.fx to javafx.graphics;
    opens eu.javaspecialists.courses.juppies2.anagrams.ui.fxml to javafx.graphics, javafx.fxml;
}
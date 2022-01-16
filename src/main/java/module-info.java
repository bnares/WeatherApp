module Maven {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    //requires java.json;
    requires org.json;

    //opens controller to javafx.fxml;
    //exports controller;
    opens controller;
    opens view;
    opens launch;
}
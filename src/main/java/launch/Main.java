package launch;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.View;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        View view = new View();
        view.showMainWindow();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

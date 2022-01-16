package view;

import controller.BaseController;
import controller.DailyForecast;
import controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class View {

    private ArrayList<Scene> sceneList = new ArrayList<>();
    private ArrayList<Stage> stageList = new ArrayList<>();
    //private MainWindowController mainWindowController = null;

    public ArrayList<Stage> getStageList(){
        return stageList;
    }

    public ArrayList<Scene> getSceneList(){
        return this.sceneList;
    }

    private void dailyWeatherForecastWindow(BaseController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/"+controller.getFxmlFile()));
        loader.setController(controller);
        Parent parent = null;
        try{
            parent = loader.load();

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.setScene(scene);
        scene.getStylesheets().add("daily.css");
        stage.setFullScreen(false);
        stage.setTitle("DailyForecast");
        stage.setResizable(false);
        stage.show();
        sceneList.add(scene);
        stageList.add(stage);
    }

    private void initializageStage(BaseController controller) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/"+controller.getFxmlFile()));
        loader.setController(controller);
        Parent parent = null;
        try{
            parent = loader.load();

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();


        //scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());
        scene.getStylesheets().add("main.css");
        try {
            String css = this.getClass().getResource("main.css").toExternalForm();
            System.out.println("css: " + css);
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }



        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setWidth(800);
        stage.setHeight(400);
        stage.setTitle("Weahter App");
        stage.setResizable(false);
        stage.show();
        sceneList.add(scene);
        stageList.add(stage);

    }



    private void setController(BaseController baseController, FXMLLoader loader){
        loader.setController(baseController);
    }

    public void showMainWindow(){
        BaseController controller = new MainWindowController(this, "MainWindow.fxml");
        initializageStage(controller);
        //System.out.println("just before setting mainWIndowController");
        //this.mainWindowController = (MainWindowController) controller;

    }

    public void showDailyForecastWindow(MainWindowController mainWindowController){
        BaseController controller = new DailyForecast(this, "dailyForecast.fxml", mainWindowController);
        dailyWeatherForecastWindow(controller);

    }





}

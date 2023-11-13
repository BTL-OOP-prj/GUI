package main.java;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            System.out.println(getClass().getResource("../resources/assets/AppUI.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("../resources/assets/AppUI.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
            System.out.println();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}   


package uji.al415648.interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller extends Application {//pasarlo como controlador

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {  //FALTA ACABARLO
        primaryStage.setTitle("Song Recommender");
        View vista=new View();
        primaryStage.show();
    }

}

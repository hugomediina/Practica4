package uji.al415648.interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        generateGUI();
    }
    public VBox generateGUI(){//pasar argumento desde el controlador
        Label title1=new Label("Recommendarion Type");
        Label title2=new Label("Distance Type");
        Label title3=new Label("Song Titles");
        RadioButton button1=new RadioButton("Recommend based on song features");
        RadioButton button2=new RadioButton("Recommend based on guessed genre");
        RadioButton button3=new RadioButton("Euclidean");
        RadioButton button4=new RadioButton("Manhattan");
        ToggleGroup toggleGroup1=new ToggleGroup();
        ToggleGroup toggleGroup2=new ToggleGroup();
        button1.setToggleGroup(toggleGroup1);
        button2.setToggleGroup(toggleGroup1);
        button3.setToggleGroup(toggleGroup2);
        button4.setToggleGroup(toggleGroup2);

        ListView lista=new ListView<>();//aqui va el scroll panel

        Button normalButton= new Button("Recommend...");

        VBox vBox = new VBox(title1,
                button1,
                button2,
                title2,
                button3,
                button4,
                title3,
                normalButton);
        Stage s=new Stage();
        Scene scene=new Scene(vBox);
        s.setScene(scene);
        s.show();
        return vBox;
    }

}

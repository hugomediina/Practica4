package uji.al415648.interfaz;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View extends Application {
    Model myModel =new Model();
    List<String> canciones =new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        generateGUI();
    }
    public void generateGUI() throws IOException {//pasar argumento desde el controlador
        //Escena 1

        //Etiquetas
        Label title1=new Label("Recommendation Type");
        title1.setFont(new Font("Times New Roman",18));
        Label title2=new Label("Distance Type");
        title2.setFont(new Font("Times New Roman",18));
        Label title3=new Label("Song Titles");
        title3.setFont(new Font("Times New Roman",18));
        Tooltip tooltip=new Tooltip("Double Click for recommendations based on songs");


        //Listas
        ObservableList<String> searchType= FXCollections.observableArrayList("Euclidean","Manhattan");
        ComboBox comboBox=new ComboBox<>(searchType);
        ObservableList<String> recommendationType= FXCollections.observableArrayList("Recommend based on song features","Recommend based on guessed genre");
        ComboBox comboBox1=new ComboBox<>(recommendationType);
        canciones= myModel.readNames("lib/songs_test_names.csv");
        ObservableList<String> listSongs= FXCollections.observableArrayList(canciones);
        ListView<String> listView=new ListView<>(listSongs);
        ListView<String> listView1=new ListView<>(listSongs);
        listView.setTooltip(tooltip);
        Button button= new Button("Recommend...");
        button.setDisable(true);
        listView.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalue) ->{
            if(newvalue!=null) {
                button.setDisable(false);
                button.setText("Recommend on song "+ newvalue + "...");
            }
            else {
                button.setDisable(true);
            }
        });



        //FALTA EL DOUBLE CLICK HACERLO EN EL CONTROLADOR


        //Botón
        VBox vBox = new VBox(title1,
                comboBox1,
                title2,
                comboBox,
                title3,
                listView,
                button);
        Scene scene1=new Scene(vBox,300,500);
        Stage primary=new Stage();
        primary.setScene(scene1);
        primary.show();
       //Escena 2
        Label label=new Label("Number of recommendations:    ");
        Label label1=new Label("Aqui va la label del controlador");
        Spinner<Double> spinner=new Spinner<>();
        HBox hBox=new HBox(label,spinner);
        VBox vBox1=new VBox(hBox,label1,listView1);
        Scene scene2=new Scene(vBox1,300,500);
        Stage secundary=new Stage();
        secundary.setScene(scene2);
        secundary.show();
    }

}

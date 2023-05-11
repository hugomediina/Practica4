package uji.al415648.interfaz.vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uji.al415648.interfaz.controlador.Controlador;
import uji.al415648.interfaz.controlador.Controller;
import uji.al415648.interfaz.modelo.Model;
import uji.al415648.interfaz.modelo.Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View implements Vista {
    private Modelo modelo;
    private Controlador controller;
    private List<String> canciones =new ArrayList<>();
    private Stage stage;
    private Label title1,title2,title3;
    private Tooltip tooltip;
    private ComboBox comboBox,comboBox1;
    private ListView<String> listView;
    private Button button;
    private VBox vBox;


    public View(Stage stage){
        this.stage=stage;
    }
    @Override
    public void setModelo(Modelo modelo) {
        this.modelo=modelo;
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controller=controlador;
    }
    public void generateGUI() throws IOException {//pasar argumento desde el controlador
        makeLabels();
        makeCombobox();
        setList();
        makeButton();
        makeVBox();

        listView.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalue) ->{
            if(newvalue!=null) {
                button.setDisable(false);
                button.setText("Recommend on song "+ newvalue + "...");
            }
            else {
                button.setDisable(true);
            }
        });
        button.setOnAction(buttonActivation->{
            try {
                controller.openRecomendations(listView.getSelectionModel().getSelectedItem(),
                        (String) comboBox1.getSelectionModel().getSelectedItem(),
                        (String) comboBox.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //FALTA EL DOUBLE CLICK HACERLO EN EL CONTROLADOR
        Scene scene1=new Scene(vBox,300,500);
        stage=new Stage();
        stage.setScene(scene1);
        stage.show();
    }
    public void makeLabels(){
        title1=new Label("Recommendation Type");
        title2=new Label("Distance Type");
        title3=new Label("Song Titles");
        title1.setFont(new Font("Times New Roman",18));
        title2.setFont(new Font("Times New Roman",18));
        title3.setFont(new Font("Times New Roman",18));
    }
    public void makeCombobox(){
        ObservableList<String> searchType= FXCollections.observableArrayList("Euclidean","Manhattan");
        ObservableList<String> recommendationType= FXCollections.observableArrayList("Recommend based on song features","Recommend based on guessed genre");
        comboBox=new ComboBox<>(searchType);
        comboBox1=new ComboBox<>(recommendationType);
    }
    public void setList() throws IOException {
        canciones= modelo.readNames("lib/songs_test_names.csv");
        ObservableList<String> listSongs= FXCollections.observableArrayList(canciones);
        listView=new ListView<>(listSongs);
        listView.setTooltip(tooltip);
        tooltip=new Tooltip("Double Click for recommendations based on songs");

    }
    public void makeButton(){
        button= new Button("Recommend...");
        button.setDisable(true);
    }
    public void makeVBox(){
        vBox = new VBox(title1,
                comboBox1,
                title2,
                comboBox,
                title3,
                listView,
                button);
    }

}

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
import uji.al415648.interfaz.controlador.InterfaceController;
import uji.al415648.interfaz.modelo.InterfaceModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View implements InterfaceView {
    private InterfaceModel interfaceModel;
    private InterfaceController controller;
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
    public void setModelo(InterfaceModel interfaceModel) {
        this.interfaceModel = interfaceModel;
    }

    @Override
    public void setControlador(InterfaceController interfaceController) {
        this.controller= interfaceController;
    }
    public void generateGUI() throws IOException {
        makeLabels();
        makeCombobox();
        setList();
        makeButton();
        makeVBox();

        listView.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalue) ->{
            if(newvalue!=null && comboBox.getSelectionModel().getSelectedItem()!=null && comboBox1.getSelectionModel().getSelectedItem()!=null) {
                button.setDisable(false);
                button.setText("Recommend on song "+ newvalue + "...");
            }
            else {
                button.setDisable(true);
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2 && comboBox.getSelectionModel().getSelectedItem()!=null && comboBox1.getSelectionModel().getSelectedItem()!=null) {

                try {
                    controller.openRecomendations(listView.getSelectionModel().getSelectedItem(),
                            (String) comboBox1.getSelectionModel().getSelectedItem(),
                            (String) comboBox.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                        e.printStackTrace();
                }


            }});
        button.setOnAction(buttonActivation->{
            try {
                controller.openRecomendations(listView.getSelectionModel().getSelectedItem(),
                        (String) comboBox1.getSelectionModel().getSelectedItem(),
                        (String) comboBox.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Scene scene1=new Scene(vBox,300,500);
        stage=new Stage();
        stage.setTitle("Song Recommeder");
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
        canciones= interfaceModel.readNames("lib/songs_test_names.csv");
        ObservableList<String> listSongs= FXCollections.observableArrayList(canciones);
        listView=new ListView<>(listSongs);
        tooltip=new Tooltip("Double Click for recommendations based on songs");
        listView.setTooltip(tooltip);
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

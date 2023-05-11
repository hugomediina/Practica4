package uji.al415648.interfaz.vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uji.al415648.distancias.Distance;
import uji.al415648.distancias.EuclideanDistance;
import uji.al415648.distancias.ManhattanDistance;
import uji.al415648.interfaz.controlador.Controlador;
import uji.al415648.interfaz.modelo.Modelo;

import java.util.List;

public class ViewSecundary implements Vista{
    private Controlador controller;
    private Modelo modelo;
    private ListView<String> recommendedSongs;
    private List<String> recomend;
    private Stage stage;
    private Label label,label1;
    private Spinner<Integer> spinner;
    private HBox hBox;
    private VBox vBox;
    private Button button;
    private String typeRecommendation,typeDistance,nameSong;
    public ViewSecundary(Stage stage, String typeRecommendation, String typeDistance, String nameSong) throws Exception {
        recommendedSongs=new ListView<>();
        this.nameSong=nameSong;
        this.stage=stage;
        this.typeDistance=typeDistance;
        this.typeRecommendation=typeRecommendation;
    }
    @Override
    public void generateGUI() throws Exception {
        typeSearch();
        makeLabels();
        makeButton();
        stage=new Stage();
        button.setOnAction(buttonActivation->{
            stage.close();
        });
        recomend=modelo.getRecsys().recommend(nameSong, spinner.getValue());
        setList();

        spinner.valueProperty().addListener((observableValue, integer, t1) -> {
            recomend=modelo.getRecsys().recommend(nameSong, t1);
            setList();
        });

        makeVBox();
        Scene scene=new Scene(vBox,300,500);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setModelo(Modelo modelo) {
        this.modelo=modelo;
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controller=controlador;
    }

    @Override
    public void makeLabels() {
        label=new Label("Number of recommendations");
        label1=new Label("If you liked "+nameSong+" you might like");
    }

    @Override
    public void setList() {
        ObservableList<String> listSongs=FXCollections.observableArrayList(recomend);
        recommendedSongs.setItems(listSongs);
    }

    @Override
    public void makeButton() {
        spinner=new Spinner<>();
        spinner.setEditable(true);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,modelo.getRecsys().getSize(nameSong),5));
        button=new Button("Close");
    }

    @Override
    public void makeVBox() {
        hBox=new HBox(label,spinner);
        vBox=new VBox(hBox,label1,recommendedSongs,button);
    }
    public void typeSearch() throws Exception {
        String method;
        Distance searchmethod;
        if(typeRecommendation.equals("Recommend based on song features")){
            method="knn";
        }else
            method="kmeans";
        if(typeDistance.equals("Euclidean"))
            searchmethod=new EuclideanDistance();
        else
            searchmethod=new ManhattanDistance();
        modelo.recommender(method,searchmethod);
    }

}

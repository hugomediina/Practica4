package uji.al415648.interfaz.vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uji.al415648.distancias.Distance;
import uji.al415648.distancias.EuclideanDistance;
import uji.al415648.distancias.ManhattanDistance;
import uji.al415648.interfaz.controlador.InterfaceController;
import uji.al415648.interfaz.modelo.InterfaceModel;

import java.util.List;

public class ViewSecundary implements InterfaceView {
    private InterfaceController controller;
    private InterfaceModel interfaceModel;
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
        recomend=interfaceModel.createRecommendation(nameSong,spinner.getValue());
        setList();
        spinner.valueProperty().addListener((observableValue, integer, t1) -> {
            recomend= interfaceModel.createRecommendation(nameSong,t1);
            setList();
        });
        makeVBox();
        Scene scene=new Scene(vBox,400,300);
        stage.setTitle("A recommendation based on " + nameSong);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setModelo(InterfaceModel interfaceModel) {
        this.interfaceModel = interfaceModel;
    }

    @Override
    public void setControlador(InterfaceController interfaceController) {
        this.controller= interfaceController;
    }

    @Override
    public void makeLabels() {
        label=new Label("Number of recommendations (MAX "+ interfaceModel.getRecsys().getSize(nameSong)+")");
        label.setPadding(new Insets(10,10,10,10));
        label.setLineSpacing(5);
        label1=new Label("If you liked "+nameSong+" you might like");
        label1.setPadding(new Insets(10,10,10,10));
        label1.setLineSpacing(5);
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
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, interfaceModel.getRecsys().getSize(nameSong),5));
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
        interfaceModel.recommender(method,searchmethod);
    }

}

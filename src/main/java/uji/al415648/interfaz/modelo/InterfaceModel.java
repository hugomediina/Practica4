package uji.al415648.interfaz.modelo;

import javafx.stage.Stage;
import uji.al415648.algoritmos.RecSys;
import uji.al415648.distancias.Distance;
import uji.al415648.interfaz.vista.InterfaceView;

import java.io.IOException;
import java.util.List;

public interface InterfaceModel {
    public void setVista(InterfaceView interfaceView) throws Exception;
    public void recommender(String method, Distance distance) throws Exception;
    public List<String> readNames(String fileOfItemNames) throws IOException;
    public RecSys getRecsys();
    public List<String> createRecommendation(String nameSong, int spinner);
    public InterfaceView createNewView(Stage stage, String algorithm, String distance, String song) throws Exception;
    public void secondViewThrow() throws Exception;
}

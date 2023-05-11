package uji.al415648.interfaz.modelo;

import uji.al415648.algoritmos.RecSys;
import uji.al415648.distancias.Distance;
import uji.al415648.interfaz.vista.Vista;

import java.io.IOException;
import java.util.List;

public interface Modelo {
    public void setVista(Vista vista);
    public void recommender(String method, Distance distance) throws Exception;
    public List<String> readNames(String fileOfItemNames) throws IOException;
    public RecSys getRecsys();
}

package uji.al415648.interfaz.controlador;

import uji.al415648.interfaz.modelo.Model;
import uji.al415648.interfaz.modelo.Modelo;
import uji.al415648.interfaz.vista.View;
import uji.al415648.interfaz.vista.Vista;

import java.io.IOException;

public interface Controlador {
    public void setVista(Vista vista);
    public void setModelo(Modelo modelo);
    public void openRecomendations(String song, String algorithm, String distance) throws Exception;
}

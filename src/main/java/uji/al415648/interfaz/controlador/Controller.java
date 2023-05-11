package uji.al415648.interfaz.controlador;

import javafx.stage.Stage;
import uji.al415648.interfaz.modelo.Model;
import uji.al415648.interfaz.modelo.Modelo;
import uji.al415648.interfaz.vista.ViewSecundary;
import uji.al415648.interfaz.vista.Vista;

import java.io.IOException;

public class Controller implements Controlador {
    private Vista vista;
    private Modelo model;
    @Override
    public void setVista(Vista vista) {
        this.vista=vista;
    }
    @Override
    public void setModelo(Modelo modelo) {
        this.model=modelo;
    }
    public void openRecomendations(String song, String algorithm, String distance) throws Exception {
        vista=new ViewSecundary(new Stage(),algorithm,distance,song);
        vista.setModelo(model);
        vista.setControlador(this);
        model.setVista(vista);
        vista.generateGUI();
    }
}

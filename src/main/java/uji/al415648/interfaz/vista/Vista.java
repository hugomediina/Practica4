package uji.al415648.interfaz.vista;

import uji.al415648.interfaz.controlador.Controlador;

import uji.al415648.interfaz.modelo.Model;
import uji.al415648.interfaz.modelo.Modelo;

import java.io.IOException;

public interface Vista {
    public void generateGUI() throws Exception;
    public void setModelo(Modelo modelo);
    public void setControlador(Controlador controlador);
    public void makeLabels();
    public void setList() throws IOException;
    public void makeButton();
    public void makeVBox();
}

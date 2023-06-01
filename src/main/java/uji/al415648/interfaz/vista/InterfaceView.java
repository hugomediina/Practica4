package uji.al415648.interfaz.vista;

import uji.al415648.interfaz.controlador.InterfaceController;

import uji.al415648.interfaz.modelo.InterfaceModel;

import java.io.IOException;

public interface InterfaceView {
    public void generateGUI() throws Exception;
    public void setModelo(InterfaceModel interfaceModel);
    public void setControlador(InterfaceController interfaceController);
    public void makeLabels();
    public void setList() throws IOException;
    public void makeButton();
    public void makeVBox();
}

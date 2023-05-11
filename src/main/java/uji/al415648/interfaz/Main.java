package uji.al415648.interfaz;

import javafx.application.Application;
import javafx.stage.Stage;
import uji.al415648.interfaz.controlador.Controller;
import uji.al415648.interfaz.modelo.Model;
import uji.al415648.interfaz.vista.View;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Controller controlador = new Controller();
        Model modelo = new Model();
        View vista = new View(stage);
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.generateGUI();
    }

}

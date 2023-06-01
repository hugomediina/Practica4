package uji.al415648.interfaz.controlador;

import javafx.stage.Stage;
import uji.al415648.interfaz.modelo.InterfaceModel;
import uji.al415648.interfaz.vista.InterfaceView;


public class Controller implements InterfaceController {
    private InterfaceView interfaceView;
    private InterfaceModel model;
    @Override
    public void setVista(InterfaceView interfaceView) {
        this.interfaceView = interfaceView;
    }
    @Override
    public void setModelo(InterfaceModel interfaceModel) {
        this.model= interfaceModel;
    }
    public void openRecomendations(String song, String algorithm, String distance) throws Exception {
        interfaceView= model.createNewView(new Stage(),algorithm,distance,song);
        interfaceView.setModelo(model);
        interfaceView.setControlador(this);
        model.setVista(interfaceView);
        model.secondViewThrow();
    }
}

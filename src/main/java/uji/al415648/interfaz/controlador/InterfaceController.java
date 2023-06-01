package uji.al415648.interfaz.controlador;

import uji.al415648.interfaz.modelo.InterfaceModel;
import uji.al415648.interfaz.vista.InterfaceView;

public interface InterfaceController {
    public void setVista(InterfaceView interfaceView);
    public void setModelo(InterfaceModel interfaceModel);
    public void openRecomendations(String song, String algorithm, String distance) throws Exception;
}

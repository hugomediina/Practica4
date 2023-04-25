import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uji.al415648.datos.Row;
import uji.al415648.datos.Table;

public class Hello extends Application {
    private Table table;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inicio de sesión");
        ObservableList<Row> list= FXCollections.observableArrayList(table.getRowAt(8),table.getRowAt(2),table.getRowAt(1));
        ComboBox lista=new ComboBox<>(list);
    }
}

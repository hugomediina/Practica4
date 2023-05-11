package uji.al415648.interfaz.modelo;

import uji.al415648.algoritmos.Algorithm;
import uji.al415648.algoritmos.KNN;
import uji.al415648.algoritmos.Kmeans;
import uji.al415648.algoritmos.RecSys;
import uji.al415648.datos.Table;
import uji.al415648.distancias.Distance;
import uji.al415648.interfaz.controlador.Controlador;
import uji.al415648.interfaz.vista.View;
import uji.al415648.interfaz.vista.Vista;
import uji.al415648.lectura.CSV;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements Modelo{
    private Vista vista;

    private RecSys recsys;

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void recommender(String method, Distance distance) throws Exception {
        String sep = System.getProperty("file.separator");
        String ruta = "lib";

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train","songs_train.csv");
        filenames.put("knn"+"test","songs_test.csv");
        filenames.put("kmeans"+"train","songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test","songs_test_withoutnames.csv");

        // Algorithms
        Map<String, Algorithm> algorithms = new HashMap<>();
        algorithms.put("knn",new KNN(distance));
        algorithms.put("kmeans",new Kmeans(15, 200, 4321,distance));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }

        // Names of items
        List<String> names = readNames(ruta+sep+"songs_test_names.csv");

        // Start the RecSys
        this.recsys = new RecSys(algorithms.get(method));
        this.recsys.train(tables.get(method+"train"));
        this.recsys.run(tables.get(method+"test"), names);


    }
    public List<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }

    public RecSys getRecsys() {
        return recsys;
    }
}

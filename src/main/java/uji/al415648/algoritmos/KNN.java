package uji.al415648.algoritmos;

import uji.al415648.datos.RowWithLabel;
import uji.al415648.datos.TableWithLabels;
import uji.al415648.distancias.Distance;
import uji.al415648.distancias.DistanceClient;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels>{
    private TableWithLabels table;
    private Distance distance;
    public KNN(Distance distance){
        this.distance=distance;
    }
    public void train(TableWithLabels data){
        this.table=data;
    }
    public Integer estimate(List<Double> data){
        double euclidea;
        double distMin=-1;
        int numberClass=-1;
        for(int i=0;i<table.getRows().size();i++){
            RowWithLabel row=(RowWithLabel) table.getRows().get(i);
             euclidea=distance(row.getData(),data);
             if(euclidea<distMin || distMin==-1){
                 distMin=euclidea;
                 numberClass=table.getRowAt(i).getNumberClass();
             }
        }
        return numberClass;
    }
    public double distance(List<Double> data_source, List<Double> data){
        return distance.calculateDistance(data,data_source);
    }
    public void setDistance(Distance distance){
        this.distance=distance;
    }
}

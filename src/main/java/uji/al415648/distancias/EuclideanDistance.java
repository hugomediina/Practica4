package uji.al415648.distancias;

import java.util.List;

public class EuclideanDistance implements Distance{
        public double calculateDistance(List<Double> data, List<Double> listaValores) {
        double actual = 0;
        for (int j = 0; j < listaValores.size(); j++) {
            actual += Math.pow(data.get(j) - listaValores.get(j), 2);//Distancia euclídea
        }
        return Math.sqrt(actual);
    }
}

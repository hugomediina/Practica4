package uji.al415648.distancias;

import java.util.List;

public class ManhattanDistance implements Distance{
    public double calculateDistance(List<Double> data, List<Double> listaValores) {
        double actual = 0;
        for (int j = 0; j < listaValores.size(); j++) {
            actual += Math.abs(data.get(j) - listaValores.get(j)); //Distancia manhattan
        }
        return actual;
    }
}

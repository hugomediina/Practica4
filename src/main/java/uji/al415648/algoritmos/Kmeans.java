package uji.al415648.algoritmos;

import uji.al415648.datos.Row;
import uji.al415648.datos.Table;
import uji.al415648.distancias.Distance;

import java.util.*;

public class Kmeans implements Algorithm<Table>{
    private int numClusters;
    private int numIterations;
    private long seed;
    private Map<Integer,List<Row>> groups;
    private List<Row> points;
    private Table globalData;
    private Distance distance;

    public Kmeans(int numClusters,int numIterations, long seed, Distance distance){
        this.numClusters=numClusters;
        this.numIterations=numIterations;
        this.seed=seed;
        this.groups=new HashMap<>();
        this.points=new ArrayList<>();
        this.distance=distance;
    }

    @Override
    public void train(Table datos) throws TooMuchGroupsException {
        globalData=datos;
        if(numClusters>numIterations)
            throw new TooMuchGroupsException();
        makeCentroide();
        for(int j=0;j<numIterations;j++) {
            for (int i = 0; i < datos.getRows().size(); i++) {
                int id = estimate(datos.getRowAt(i).getData());
                if(!groups.get(id).contains(datos.getRowAt(i)))
                    groups.get(id).add(datos.getRowAt(i));
            }
            points.clear();
            for (int i = 0; i < numClusters; i++) {
                points.add(meanCentroide(groups.get(i)));
                if(j!=numIterations-1){
                    groups.get(i).clear();
                }
            }
        }
    }
    @Override
    public Integer estimate(List<Double> dato){
        int id=-1,count=0;
        double distMin=Double.MAX_VALUE;
        for(Row element:points){
            double distActual=calculateDistance(dato,element.getData());
            if(distMin > distActual){
                distMin=distActual;
                id=count;
            }
            count++;
        }
        return id;
    }

    private double calculateDistance(List<Double> dato, List<Double> data) {
        return distance.calculateDistance(dato,data);
    }

    private void makeCentroide(){
        Random random=new Random(seed);
        for(int i=0;i<numClusters;i++){
            int numRandom=random.nextInt(globalData.getRows().size()-1);
            if(!points.contains(globalData.getRowAt(numRandom))){
                points.add(globalData.getRowAt(numRandom));
                groups.put(i,new ArrayList<>());
            }
            else{
                i--;
            }
        }
    }
    private Row meanCentroide(List<Row> rows) {
        Row centroide=new Row();
        List<Double> auxiliar=new ArrayList<>();
        for(int i=0;i<globalData.getHeaders().size();i++){
            auxiliar.add(0.0);
        }
        for(Row element:rows){
            List<Double> auxiliar2=element.getData();
            for(int i=0;i<auxiliar2.size();i++){
                auxiliar.set(i,auxiliar2.get(i)+auxiliar.get(i));
            }
        }
        for(int i=0;i<auxiliar.size();i++){
            auxiliar.set(i,auxiliar.get(i)/rows.size()-1);
        }
        centroide.addRowList(auxiliar);
        return centroide;
    }

    public List<Row> getPoints() {
        return points;
    }

    public Map<Integer, List<Row>> getGroups() {
        return groups;
    }
    public void setDistance(Distance distance){
        this.distance=distance;
    }
}

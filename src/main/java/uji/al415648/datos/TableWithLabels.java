package uji.al415648.datos;

import java.util.HashMap;
import java.util.Map;

public class TableWithLabels extends Table {
    private Map<String,Integer> labelsToIndex;
    private int numberClass;
    public TableWithLabels(){
        super();
        this.labelsToIndex=new HashMap<>();
        numberClass=0;
    }
    @Override
    public RowWithLabel getRowAt(int n) {
        return (RowWithLabel) super.getRowAt(n);
    }
    public void addLabelsToIndex(String label, RowWithLabel row){
        if (!this.getLabelsToIndex().containsKey(label)) {
            numberClass++;
            row.addNumberClass(numberClass);
            labelsToIndex.put(label,numberClass);
        } else {
            row.addNumberClass(getLabelsToIndex().get(label));
        }
    }
    public Map<String, Integer> getLabelsToIndex() {
        return labelsToIndex;
    }
}
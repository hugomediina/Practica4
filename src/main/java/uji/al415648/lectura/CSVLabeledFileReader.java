package uji.al415648.lectura;

import uji.al415648.datos.Row;
import uji.al415648.datos.RowWithLabel;
import uji.al415648.datos.Table;
import uji.al415648.datos.TableWithLabels;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader {
    private TableWithLabels table;
    public CSVLabeledFileReader(String file) {
        super(file);
        table=new TableWithLabels();
    }

    @Override
    void processData(String data) {
        String[] line =data.split(",");
        RowWithLabel row = new RowWithLabel();
        String label = line[line.length - 1];
        for (int i = 0; i < line.length - 1; i++) {
            row.addRow(Double.parseDouble(line[i]));
        }
        table.addLabelsToIndex(label,row);
        table.addLine(row);
    }
    @Override
    public TableWithLabels getTable() {
        return table;
    }
}

package uji.al415648.lectura;

import uji.al415648.datos.Row;
import uji.al415648.datos.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    private Scanner sc;
    private Table table;
    public CSVUnlabeledFileReader(String file) {
        super(file);
        table=new Table();
    }

    @Override
    void openSource(String source) throws FileNotFoundException {
        String separator= File.separator;
        sc=new Scanner(new File("lib"+separator+source));
    }

    @Override
    void processHeaders(String headers) {
        String[] line=headers.split(",");
        getTable().addHeaders(line);
    }

    @Override
    void processData(String data) {
        Row row=new Row();
        String[] line=data.split(",");
        for(String element:line){
            row.addRow(Double.valueOf(element));
        }
        table.addLine(row);
    }

    @Override
    void closeSource() {
        sc.close();
    }

    @Override
    boolean hasMoreData() {
        return sc.hasNext();
    }

    @Override
    String getNextData() {
        return sc.nextLine();
    }

    @Override
    public Table getTable() {
        return table;
    }
}

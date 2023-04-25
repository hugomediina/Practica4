package uji.al415648.lectura;

import uji.al415648.datos.Row;
import uji.al415648.datos.RowWithLabel;
import uji.al415648.datos.Table;
import uji.al415648.datos.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSV {
    public Table readTable(String file) throws FileNotFoundException {
        String separator= File.separator;
        Table table=new Table();
        Scanner sc=new Scanner(new File("lib"+separator+file));
        int count=0;
        while(sc.hasNext()){
            String[] choppedLine = sc.nextLine().split(",");
            if(count==0){
                table.addHeaders(choppedLine);
                count++;
            }
            else{
                Row row=new Row();
                for(String element:choppedLine){
                    row.addRow(Double.valueOf(element));
                }
                table.addLine(row);
            }
        }
        sc.close();
        return table;
    }

    public TableWithLabels readTableWithLabels(String file) throws FileNotFoundException{
        String separator=File.separator;
        TableWithLabels table= new TableWithLabels();
        Scanner sc = new Scanner(new File("lib"+separator+file));
        int count = 0;
        int numberClass=0;
        while (sc.hasNext()){
            String [] choppedLine= sc.nextLine().split(",");
            if(count==0){
                table.addHeaders(choppedLine);
                count++;
            }else{
                RowWithLabel row = new RowWithLabel();
                String label = choppedLine[choppedLine.length - 1];
                for(int i=0;i<choppedLine.length-1;i++){
                    row.addRow(Double.parseDouble(choppedLine[i]));
                }
                table.addLabelsToIndex(label,row);
                table.addLine(row);
            }
        }
        sc.close();
        return table;
    }
}
package uji.al415648.lectura;

import uji.al415648.datos.Table;

import java.io.FileNotFoundException;

public abstract class ReaderTemplate {
    private String fileName;
    abstract void openSource(String source) throws FileNotFoundException;
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData(); // comprueba si hay más datos; en nuestro caso, si hay mas línea(s) en el fichero CSV
    abstract String getNextData(); // obtener el siguiente dato; una línea del fichero CSV en nuestro caso
    public abstract Table getTable();

    public ReaderTemplate(String file) {
        this.fileName=file;
    }

    public final Table readTableFromSource() throws FileNotFoundException {
        openSource(fileName);
        int count=0;
        while(hasMoreData()){
            if(count==0){
                processHeaders(getNextData());
                count++;
            }
            else{
                processData(getNextData());
            }
        }
        closeSource();
        return getTable();
    }
}

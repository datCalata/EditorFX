package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcala on 15/06/2017.
 */
public class FileStructure {
    private List<String> lineas;
    private File file;
    private boolean exist;

    public FileStructure(){
        file = null;
        lineas = null;
        exist = false;
    }

    public FileStructure(File file) throws FileNotFoundException{
        if(file == null) {
            throw new FileNotFoundException();
        }
        this.file = file;
        lineas = new ArrayList<>();
        exist = true;
    }

    public void readFile() throws IOException {

        if(file == null){
            return;
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader textReader = new BufferedReader(fileReader);
        String linea = textReader.readLine();

        while(linea != null){
            lineas.add(linea);
            linea = textReader.readLine();
        }
        textReader.close();
    }

    public void setLineas(List<String> lineas){
        this.lineas = lineas;
    }

    public List<String> getLineas(){
        return lineas;
    }

    public void saveFile() throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        BufferedWriter textWritter = new BufferedWriter(new OutputStreamWriter(fout));

        for(String linea : lineas){
            textWritter.write(linea);
            textWritter.newLine();
        }
        exist = true;
        textWritter.close();
    }

    public boolean isExist() {
        return exist;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

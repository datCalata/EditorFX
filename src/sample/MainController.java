package sample;

import com.sun.glass.ui.SystemClipboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.SAAJMetaFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;



public class MainController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem new_;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem saveAs;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem cut;
    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem paste;
    @FXML
    private MenuItem search;
    @FXML
    private MenuItem searchAndChange;
    @FXML
    private TextArea editor;


    private Desktop desktop = Desktop.getDesktop();
    private FileStructure myFile;

    @FXML
    public void initialize(){
        newFile();
    }

    @FXML
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if(file == null){
            return;
        }
        try {
            myFile = new FileStructure(file);
            myFile.readFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        putText();
        editor.setEditable(true);
    }

    @FXML
    private void saveFile(){
        if(!myFile.isExist()){
            saveFileAs();
            return;
        }
        saveText();
    }

    @FXML
    private void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(anchorPane.getScene().getWindow());
        if(file == null){
            return;
        }
        myFile.setFile(file);
        saveText();
    }

    @FXML
    private void newFile(){
        myFile = new FileStructure();
        editor.setText("");
        editor.setEditable(true);
    }

    @FXML
    private void closeFile(){
        myFile = null;
        editor.setText("");
        editor.setEditable(false);
    }

    private void saveText(){
        myFile.setLineas(readText());
        try {
            myFile.saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putText(){
        editor.setText("");
        for(String linea : myFile.getLineas()){
            editor.appendText(linea.concat("\n"));
        }
    }

    private List<String> readText(){
        List<String> lineas  = new ArrayList<>();
        String texto = editor.getText();
        for(String linea : texto.split("\\n")){
            lineas.add(linea);
        }
        return lineas;
    }


    @FXML
    private void findAndReplace() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FindAndReplaceDialog.fxml"));
        Parent root = loader.load();
        FindAndReplaceDialogController controller = loader.getController();
        controller.setLines(editor.getText());
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(anchorPane.getScene().getWindow());
        stage.showAndWait();
        editor.setText(controller.getText());
    }

    @FXML
    private void copyText(){
        editor.copy();
    }
    @FXML
    private void cutText(){
        editor.cut();
    }
    @FXML
    private void pasteText(){
        editor.paste();
    }

}

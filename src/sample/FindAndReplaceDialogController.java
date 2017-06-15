package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jcala on 15/06/2017.
 */
public class FindAndReplaceDialogController implements Initializable{

    @FXML
    private Button replaceOne;

    @FXML
    private Label detailsLabel1;

    @FXML
    private Label detailsLabel;

    @FXML
    private Button replaceAll;

    @FXML
    private HBox actionParent;

    @FXML
    private Button cancel;

    @FXML
    private HBox okParent;

    @FXML
    private TextField searchField;

    @FXML
    private TextField replaceField;

    @FXML
    private GridPane pane;

    private String text;


    public String getText(){
        return text;
    }
    public void setLines(String text){
        this.text = text;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void ReplaceAll(){
        String search = searchField.getText();
        String replace = replaceField.getText();
        text = text.replaceAll(search,replace);
        closing();
    }
    @FXML
    public void replaceOne(){
        String search = searchField.getText();
        String replace = replaceField.getText();
        text = text.replaceFirst(search,replace);
        closing();
    }

    @FXML
    public void closing(){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
}

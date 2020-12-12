package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<?> componentTable;
    @FXML
    private Button engineBtn;
    @FXML
    private Button wheelBtn;
    @FXML
    private Button electronicBtn;
    @FXML
    private Button bodyBtn;
    @FXML
    private Button carBtn;
    @FXML
    private ComboBox<String> engineList;
    @FXML
    private ComboBox<String> electronicList;
    @FXML
    private ComboBox<String> wheelList;
    @FXML
    private ComboBox<String> bodyList;
    @FXML
    private ComboBox<String> carList;
    @FXML
    private ProgressBar enginePB;
    @FXML
    private ProgressBar electronicPB;
    @FXML
    private ProgressBar carPB;
    @FXML
    private ProgressBar wheelPB;
    @FXML
    private ProgressBar bodyPB;
    @FXML
    private Label engineLabel;
    @FXML
    private Label wheelLabel;
    @FXML
    private Label electronicLabel;
    @FXML
    private Label bodyLabel;
    @FXML
    private Label carLabel;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

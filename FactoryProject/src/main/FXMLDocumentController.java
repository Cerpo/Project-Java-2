package main;

import carbuilder.CarBuilder;
import carbuilder.CarRequirement;
import components.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import stations.BodyStation;
import stations.ElectronicsStation;
import stations.EngineStation;
import stations.WheelStation;
import storage.ComponentStorage;
import threading.WorkerStation;
import util.JsonUtils;

public class FXMLDocumentController implements Initializable {

    private Label label;
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
    @FXML
    private Label engineCount;
    

    private static WorkerStation<Body> bodyWorker;
    private static WorkerStation<Electronics> electronicsWorker;
    private static WorkerStation<Engine> engineWorker;
    private static WorkerStation<Wheel> wheelWorker;

    private static CarBuilder carBuilder = new CarBuilder();

    private static ComponentStorage storage = new ComponentStorage();


    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpStations();

        ArrayList<String> possibleTypes;
        possibleTypes = bodyWorker.getStation().getPossibleTypes();
        bodyList.setItems(FXCollections.observableArrayList(possibleTypes));
        possibleTypes = electronicsWorker.getStation().getPossibleTypes();
        electronicList.setItems(FXCollections.observableArrayList(possibleTypes));
        possibleTypes = engineWorker.getStation().getPossibleTypes();
        engineList.setItems(FXCollections.observableArrayList(possibleTypes));
        possibleTypes = wheelWorker.getStation().getPossibleTypes();
        wheelList.setItems(FXCollections.observableArrayList(possibleTypes));
    }


    public void startEngine(ActionEvent event) throws InterruptedException {
        System.out.println(storage.getComponents());
        engineWorker.execute();
        Thread.sleep(2000);
        List<Engine> results = engineWorker.cancel(); 
        for (Engine engine : results) 
            storage.addComponent(engine);
        System.out.println(storage.getComponents());
        
    }
    
    private static void setUpStations() {
        List<Body> possibleBodies;
        List<Electronics> possibleElectronics;
        List<Engine> possibleEngines;
        List<Wheel> possibleWheels;
        List<CarRequirement> requirements;

        try {
            possibleBodies = JsonUtils.getComponents(new File("src/resources/bodies.json"), Body.class);
            possibleElectronics = JsonUtils.getComponents(new File("src/resources/electronics.json"), Electronics.class);
            possibleEngines = JsonUtils.getComponents(new File("src/resources/engines.json"), Engine.class);
            possibleWheels = JsonUtils.getComponents(new File("src/resources/wheels.json"), Wheel.class);
            requirements = JsonUtils.getCarRequirements(new File("src/resources/carrequirements.json"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find json files, with working directory: " +
                    System.getProperty("user.dir"));
            e.printStackTrace();
            return;
        }

        bodyWorker = new WorkerStation<>(new BodyStation(possibleBodies));
        electronicsWorker = new WorkerStation<>(new ElectronicsStation(possibleElectronics));
        engineWorker = new WorkerStation<>(new EngineStation(possibleEngines));
        wheelWorker = new WorkerStation<>(new WheelStation(possibleWheels));
        for (CarRequirement requirement : requirements)
            carBuilder.addCarRequirement(requirement);
    }
  
}


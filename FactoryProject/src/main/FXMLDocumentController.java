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
import main.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stations.BodyStation;
import stations.ElectronicsStation;
import stations.EngineStation;
import stations.WheelStation;
import storage.ComponentStorage;
import threading.WorkerStation;
import util.JsonUtils;

public class FXMLDocumentController implements Initializable {

    private static final Logger logger = LogManager.getLogger("FXMLDocumentController");

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
    @FXML
    private TableView<TypeNameModel> engineTable;
    @FXML
    private TableColumn<TypeNameModel, String> engineNameCol;
    @FXML
    private TableView<TypeNameModel> bodyTable;
    @FXML
    private TableColumn<TypeNameModel, String> bodyNameCol;
    @FXML
    private TableView<TypeNameModel> electronicTable;
    @FXML
    private TableColumn<TypeNameModel, String> electronicNameCol;
    @FXML
    private TableView<TypeNameModel> wheelTable;
    @FXML
    private TableColumn<TypeNameModel, String> wheelNameCol;
    @FXML
    private TableView<TypeNameModel> carTable;
    @FXML
    private TableColumn<TypeNameModel, String> carNameCol;

    ObservableList<TypeNameModel> eng = FXCollections.observableArrayList();
    ObservableList<TypeNameModel> car = FXCollections.observableArrayList();
    ObservableList<TypeNameModel> body = FXCollections.observableArrayList();
    ObservableList<TypeNameModel> wheel = FXCollections.observableArrayList();
    ObservableList<TypeNameModel> elec = FXCollections.observableArrayList();
    private static WorkerStation<Body> bodyWorker;
    private static WorkerStation<Electronics> electronicsWorker;
    private static WorkerStation<Engine> engineWorker;
    private static WorkerStation<Wheel> wheelWorker;

    private static CarBuilder carBuilder = new CarBuilder();

    private static ComponentStorage storage = new ComponentStorage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bodyNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        wheelNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        electronicNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        engineNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        carNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

        engineTable.getItems().setAll(eng);
        carTable.getItems().setAll(car);
        electronicTable.getItems().setAll(elec);
        bodyTable.getItems().setAll(body);
        wheelTable.getItems().setAll(wheel);
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
        possibleTypes = carBuilder.getPossibleTypes();
        carList.setItems(FXCollections.observableArrayList(possibleTypes));
    }

    boolean startEn = true;

    public void startEngine(ActionEvent event) throws InterruptedException {
        if (startEn) {
            int i = 0;
            for (String name : engineList.getItems()) {
                if (name.equals(engineList.getValue())) {
                    engineWorker.getStation().setCurrentType(i);
                }
                i++;
            }
            engineWorker.execute(); // itt indítod el az állomást

            engineWorker.setProgressBar(enginePB);
            startEn = false;
            engineBtn.setText("Stop");
        } else {
            List<Engine> results = engineWorker.cancel();
            for (Engine engine : results) {
                storage.addComponent(engine);
            }
            updateTable();
            startEn = true;
            engineBtn.setText("Start");
            enginePB.setProgress(0);
        }
    }
    boolean startW = true;

    public void startWheel(ActionEvent event) throws InterruptedException {
        if (startW) {
            int i = 0;
            for (String name : wheelList.getItems()) {
                if (name.equals(wheelList.getValue())) {
                    wheelWorker.getStation().setCurrentType(i);
                }
                i++;
            }
            wheelWorker.execute(); // itt indítod el az állomást
            wheelWorker.setProgressBar(wheelPB);
            startW = false;
            wheelBtn.setText("Stop");
        } else {
            List<Wheel> results = wheelWorker.cancel();
            for (Wheel wheel : results) {
                storage.addComponent(wheel);
            }
            updateTable();
            startW = true;
            wheelBtn.setText("Start");
            wheelPB.setProgress(0);
        }

    }
    boolean startB = true;
    public void startBody(ActionEvent event) throws InterruptedException {
        if (startB) {
            int i = 0;
            for (String name : bodyList.getItems()) {
                if (name.equals(bodyList.getValue())) {
                    bodyWorker.getStation().setCurrentType(i);
                }
                i++;
            }
            bodyWorker.execute(); // itt indítod el az állomást
            bodyWorker.setProgressBar(bodyPB);
            startB = false;
            bodyBtn.setText("Stop");
        } else {
            List<Body> results = bodyWorker.cancel();
            for (Body body : results) {
                storage.addComponent(body);
            }
            updateTable();
            startB = true;  
            bodyBtn.setText("Start");
            bodyPB.setProgress(0);
        }

    }
    
    boolean startEl = true;
    public void startElectronic(ActionEvent event) throws InterruptedException {
      if (startEl) {
            int i = 0;
            for (String name : electronicList.getItems()) {
                if (name.equals(electronicList.getValue())) {
                    electronicsWorker.getStation().setCurrentType(i);
                }
                i++;
            }
            electronicsWorker.execute(); // itt indítod el az állomást
            electronicsWorker.setProgressBar(electronicPB);
            startEl = false;
            electronicBtn.setText("Stop");
        } else {
            List<Electronics> results = electronicsWorker.cancel();
            for (Electronics el : results) {
                storage.addComponent(el);
            }
            updateTable();
            startEl = true;  
            electronicBtn.setText("Start");
            electronicPB.setProgress(0);
        }

    }
    

    public void startCar(ActionEvent event) throws InterruptedException {
        int i = 0;
        for (String name : carBuilder.getPossibleTypes()) {
            if (name.equals(carList.getValue())) {
                carBuilder.setCurrentCarRequirement(i);
            }
            i++;
        }
        if(carBuilder.build(storage)){
            TypeNameModel car = new TypeNameModel(carList.getValue());
            carTable.getItems().add(car);
            carLabel.setVisible(false);
            updateTable();
            logger.info("Created new car, with name: " + carList.getValue());
        }else{
            carLabel.setText("Some of the requirements are missing!");
            carLabel.setVisible(true);
            logger.warn("Missing components, could not create car with name: " + carList.getValue());
        }
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
            System.out.println("Could not find json files, with working directory: "
                    + System.getProperty("user.dir"));
            e.printStackTrace();
            return;
        }

        bodyWorker = new WorkerStation<>(new BodyStation(possibleBodies));
        electronicsWorker = new WorkerStation<>(new ElectronicsStation(possibleElectronics));
        engineWorker = new WorkerStation<>(new EngineStation(possibleEngines));
        wheelWorker = new WorkerStation<>(new WheelStation(possibleWheels));
        for (CarRequirement requirement : requirements) {
            carBuilder.addCarRequirement(requirement);
        }
    }

    public void updateTable(){
        wheelTable.getItems().clear();
        bodyTable.getItems().clear();
        engineTable.getItems().clear();
        electronicTable.getItems().clear();

        for(Component comp : storage.getComponents()){
            if(comp.getCategory().equals("Body")){
                TypeNameModel b = new TypeNameModel(comp.getTypeCode());
                bodyTable.getItems().add(b);
            }else if(comp.getCategory().equals("Engine")){
                TypeNameModel eng = new TypeNameModel(comp.getTypeCode());
                engineTable.getItems().add(eng);
            }else if(comp.getCategory().equals("Electronics")){
                TypeNameModel e = new TypeNameModel(comp.getTypeCode());
                electronicTable.getItems().add(e);
            }else if(comp.getCategory().equals("Wheel")){
                TypeNameModel w = new TypeNameModel(comp.getTypeCode());
                wheelTable.getItems().add(w);
            }
        }
    }
}

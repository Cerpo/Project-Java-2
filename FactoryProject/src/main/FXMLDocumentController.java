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
    @FXML
    private TableView<EngineT> engineTable;
    @FXML
    private TableColumn<EngineT, String> engineNameCol;
    @FXML
    private TableView<BodyT> bodyTable;
    @FXML
    private TableColumn<BodyT, String> bodyNameCol;
    @FXML
    private TableView<ElectronicT> electronicTable;
    @FXML
    private TableColumn<ElectronicT, String> electronicNameCol;
    @FXML
    private TableView<WheelT> wheelTable;
    @FXML
    private TableColumn<WheelT, String> wheelNameCol;
    @FXML
    private TableView<CarT> carTable;
    @FXML
    private TableColumn<CarT, String> carNameCol;


    ObservableList<String> bodyO = FXCollections.observableArrayList();
    ObservableList<String> wheelO = FXCollections.observableArrayList();
    ObservableList<String> engineO = FXCollections.observableArrayList();
    ObservableList<String> electorincsO = FXCollections.observableArrayList();
    ObservableList<EngineT> eng = FXCollections.observableArrayList();
    ObservableList<CarT> car = FXCollections.observableArrayList();
    ObservableList<BodyT> body = FXCollections.observableArrayList();
    ObservableList<WheelT> wheel = FXCollections.observableArrayList();
    ObservableList<ElectronicT> elec = FXCollections.observableArrayList();
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
    }


    public void startEngine(ActionEvent event) throws InterruptedException {
        System.out.println(storage.getComponents());
        engineWorker.execute(); // itt indítod el az állomást
        Thread.sleep(500); // ez azt szimulálja hogy a felhasználó vár
        List<Engine> results = engineWorker.cancel(); // itt állítod le az állomást
        for (Engine engine : results) // az állomás végtermékeit meg beleteszed az tárolóba
            storage.addComponent(engine);
        List<Component> comps = storage.getComponents();
        for(Component comp : comps){
            System.out.println(comp.getTypeCode());
        }
        EngineT e = new EngineT(engineList.getValue());
        engineTable.getItems().add(e);

    }

    public void startWheel(ActionEvent event) throws InterruptedException {
        WheelT w = new WheelT(wheelList.getValue());
        wheelTable.getItems().add(w);

    }

    public void startBody(ActionEvent event) throws InterruptedException {
        BodyT b = new BodyT(bodyList.getValue());
        bodyTable.getItems().add(b);

    }

    public void startElectronic(ActionEvent event) throws InterruptedException {
        ElectronicT e = new ElectronicT(electronicList.getValue());
        electronicTable.getItems().add(e);

    }

    public void startCar(ActionEvent event) throws InterruptedException {
        CarT c = new CarT(carList.getValue());
        carTable.getItems().add(c);
        
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


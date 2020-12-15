package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import util.JsonUtils;
import carbuilder.CarBuilder;
import carbuilder.CarRequirement;
import components.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stations.BodyStation;
import stations.ElectronicsStation;
import stations.EngineStation;
import stations.WheelStation;
import storage.ComponentStorage;
import threading.WorkerStation;

public class FactoryProject extends Application {

    private static WorkerStation<Body> bodyWorker;
    private static WorkerStation<Electronics> electronicsWorker;
    private static WorkerStation<Engine> engineWorker;
    private static WorkerStation<Wheel> wheelWorker;

    private static CarBuilder carBuilder = new CarBuilder();

    private static ComponentStorage storage = new ComponentStorage();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new URL("file:src/fxml/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //setUpStations();

        // PÉLDA
       /* 
        System.out.println(storage.getComponents());
        bodyWorker.execute(); // itt indítod el az állomást
        Thread.sleep(2000); // ez azt szimulálja hogy a felhasználó vár
        List<Body> results = bodyWorker.cancel(); // itt állítod le az állomást
        for (Body body : results) // az állomás végtermékeit meg beleteszed az tárolóba
            storage.addComponent(body);
        System.out.println(storage.getComponents());
*/
        launch(args);
    }

    private static void setUpStations() {
        List<Body> possibleBodies;
        List<Electronics> possibleElectronics;
        List<Engine> possibleEngines;
        List<Wheel> possibleWheels;
        List<CarRequirement> requirements;

        try {
            possibleBodies = JsonUtils.getComponents("resources/bodies.json", Body.class);
            possibleElectronics = JsonUtils.getComponents("resources/electronics.json", Electronics.class);
            possibleEngines = JsonUtils.getComponents("resources/engines.json", Engine.class);
            possibleWheels = JsonUtils.getComponents("resources/wheels.json", Wheel.class);
            requirements = JsonUtils.getCarRequirements("resources/carrequirements.json");
        } catch (FileNotFoundException e) {
            System.err.println("Could not find json files, with working directory: " +
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

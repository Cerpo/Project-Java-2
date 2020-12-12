/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Util.JsonUtils;
import components.Body;
import components.Electronics;
import components.Engine;
import components.Wheel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stations.BodyStation;
import stations.ElectronicsStation;
import stations.EngineStation;
import stations.WheelStation;

/**
 *
 * @author Csanád
 */
public class FactoryProject extends Application {

    private static BodyStation bodyStation;
    private static ElectronicsStation electronicsStation;
    private static EngineStation engineStation;
    private static WheelStation wheelStation;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new URL("file:src/FXML/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        setUpStations();

        System.out.println(bodyStation.getPossibleTypes());
        System.out.println(electronicsStation.getPossibleTypes());
        System.out.println(engineStation.getPossibleTypes());
        System.out.println(wheelStation.getPossibleTypes());

        bodyStation.startProducing();
        Thread.sleep(2000); //simulate user waiting
        bodyStation.stopProducing();

        launch(args);
    }

    private static void setUpStations() {
        List<Body> possibleBodies;
        List<Electronics> possibleElectronics;
        List<Engine> possibleEngines;
        List<Wheel> possibleWheels;
        try {
            possibleBodies = JsonUtils.getComponents(new File("src/resources/bodies.json"), Body.class); //TODO: different in other ides
            possibleElectronics = JsonUtils.getComponents(new File("src/resources/electronics.json"), Electronics.class);
            possibleEngines = JsonUtils.getComponents(new File("src/resources/engines.json"), Engine.class);
            possibleWheels = JsonUtils.getComponents(new File("src/resources/wheels.json"), Wheel.class);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find json files, with working directory: " +
                    System.getProperty("user.dir"));
            e.printStackTrace();
            return;
        }

        bodyStation = new BodyStation(possibleBodies);
        electronicsStation = new ElectronicsStation(possibleElectronics);
        engineStation = new EngineStation(possibleEngines);
        wheelStation = new WheelStation(possibleWheels);
    }
}

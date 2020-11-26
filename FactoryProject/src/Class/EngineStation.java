/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.ArrayList;

/**
 *
 * @author Cerpo
 */
public class EngineStation {
    private ArrayList<Engine> possibleEngines;
    private int currentType;
    
    
    public void setCurrentType(int index){
        this.currentType = index;
    }
    /*Lista feltöltő függvény hiányzik*/
    public ArrayList<String> getPossibleTypes(){
        ArrayList<String> possibleTypes = new ArrayList();
        possibleEngines.forEach((item) -> {
            possibleTypes.add(item.typeCode);
        });
        return possibleTypes;
    }
    
    public Engine startProduce(){
        /*Ide szálkezelés*/
        return new Engine(/*Másolat*/);
    }
    
    public void StopProduce(){
        /*Leállítás*/
    }
}

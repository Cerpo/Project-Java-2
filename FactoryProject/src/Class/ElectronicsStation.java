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
public class ElectronicsStation {
    private ArrayList<Electronics> possibleElectronics;
    private int currentType;
    
    /*Lista feltöltő függvény hiányzik*/
    public void setCurrentType(int index){
        this.currentType = index;
    }
    
    public ArrayList<String> getPossibleTypes(){
        ArrayList<String> possibleTypes = new ArrayList();
        possibleElectronics.forEach((item) -> {
            possibleTypes.add(item.typeCode);
        });
        return possibleTypes;
    }
    
    public Electronics startProduce(){
        /*Ide szálkezelés*/
        return new Electronics(/*Másolat*/);
    }
    
    public void StopProduce(){
        /*Leállítás*/
    }
}

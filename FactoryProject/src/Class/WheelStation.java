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
public class WheelStation {
    private ArrayList<Wheel> possibleWheel;
    private int currentType;
    
    /*Lista feltöltő függvény hiányzik*/
    public void setCurrentType(int index){
        this.currentType = index;
    }
    
    public ArrayList<String> getPossibleTypes(){
        ArrayList<String> possibleTypes = new ArrayList();
        possibleWheel.forEach((item) -> {
            possibleTypes.add(item.typeCode);
        });
        return possibleTypes;
    }
    
    public Wheel startProduce(){
        /*Ide szálkezelés*/
        return new Wheel(/*Másolat*/);
    }
    
    public void StopProduce(){
        /*Leállítás*/
    }
}

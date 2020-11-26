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
public class BodyStation {
    private ArrayList<Body> possibleBody;
    private int currentType;
    
    /*Lista feltöltő függvény hiányzik*/
    public void setCurrentType(int index){
        this.currentType = index;
    }
    
    public ArrayList<String> getPossibleTypes(){
        ArrayList<String> possibleTypes = new ArrayList();
        possibleBody.forEach((item) -> {
            possibleTypes.add(item.typeCode);
        });
        return possibleTypes;
    }
    
    public Body startProduce(){
        /*Ide szálkezelés*/
        return new Body(/*Másolat*/);
    }
    
    public void StopProduce(){
        /*Leállítás*/
    }
}

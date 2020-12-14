/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Csanad
 */
public class EngineT {
    private SimpleStringProperty name;
    private SimpleIntegerProperty count;
    
    public EngineT(String name, int count){
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleIntegerProperty(count);
    }
    
    public String getName(){
        return name.get();
    }
    public int getCount(){
        return count.get();
    }
    public void setName(String name){
        this.name= new SimpleStringProperty(name);
    }
    public void setCount(int count){
        this.count=new SimpleIntegerProperty(count);
    }
    @Override
    public boolean equals(Object obj) {
            if (((EngineT) obj).getName().equals(name)){
                return true;
            }else{
                return false;
            }
        }
        

}

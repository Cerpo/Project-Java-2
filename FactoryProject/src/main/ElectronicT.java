/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Csanad
 */
public class ElectronicT {
    private SimpleStringProperty name;
    //private SimpleIntegerProperty count;
    
    public ElectronicT(String name){
        this.name = new SimpleStringProperty(name);
        //this.count = new SimpleIntegerProperty(count);
    }
    
    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name= new SimpleStringProperty(name);
    }
}

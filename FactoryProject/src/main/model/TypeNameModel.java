package main.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Csanad
 */
public class TypeNameModel {
    private SimpleStringProperty name;

    public TypeNameModel(String name){
        this.name = new SimpleStringProperty(name);
    }
    
    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name= new SimpleStringProperty(name);
    }
}

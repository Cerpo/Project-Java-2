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
public class ComponentStorage {
    
    private ArrayList<Component> components = new ArrayList<Component>();
    
    
    public void addComponent(Component newComponent){
        components.add(newComponent);
    }
    
    public ArrayList<Component> getComponents(){
        return components;
    }
    
    public boolean hasType(String type, Component classType){
        return false;
    }
}

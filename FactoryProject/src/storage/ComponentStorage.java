/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import components.Component;

import java.util.ArrayList;

/**
 *
 * @author Cerpo
 */
public class ComponentStorage {
    
    private ArrayList<Component> components = new ArrayList<>();
    
    
    public void addComponent(Component newComponent){
        components.add(newComponent);
    }
    
    public ArrayList<Component> getComponents(){
        return components;
    }
    
    public boolean hasTypeInCategory(String typeCode, String category){
        for (Component component : components) {
            if (component.getCategory().equals(category) &&
                component.getTypeCode().equals(typeCode)) {
                return true;
            }
        }
        return false;
    }
}

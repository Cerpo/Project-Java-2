package storage;

import components.Component;

import java.util.ArrayList;

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

    public void removeTypeInCategory(String typeCode, String category){
        components.removeIf(component ->
                component.getCategory().equals(category) &&
                component.getTypeCode().equals(typeCode));
    }
}

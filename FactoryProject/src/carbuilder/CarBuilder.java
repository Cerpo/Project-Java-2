package carbuilder;

import storage.ComponentStorage;

import java.util.ArrayList;
import java.util.List;

public class CarBuilder {
    ArrayList<CarRequirement> carRequirements;
    int currentCarRequirement;

    public CarBuilder() {
        this(new ArrayList<>());
    }

    public CarBuilder(List<CarRequirement> requirements) {
        this.carRequirements = new ArrayList<>(requirements);
    }

    public void addCarRequirement(CarRequirement carRequirement) {
        carRequirements.add(carRequirement);
    }

    public void setCurrentCarRequirement(int index){
        this.currentCarRequirement = index;
    }

    public boolean build(ComponentStorage componentStorage) {
        CarRequirement requirement = carRequirements.get(currentCarRequirement);
        if (checkRequirements(requirement, componentStorage)) {
            removeRequirements(requirement, componentStorage);
            return true;
        } else return false;
    }

    private boolean checkRequirements(CarRequirement requirement, ComponentStorage componentStorage) {
        return componentStorage.hasTypeInCategory(requirement.getRequiredBodyType(), "Body") &&
                componentStorage.hasTypeInCategory(requirement.getRequiredElectronicsType(), "Electronics") &&
                componentStorage.hasTypeInCategory(requirement.getRequiredEngineType(), "Engine") &&
                componentStorage.hasTypeInCategory(requirement.getRequiredWheelType(), "Wheel");
    }

    private void removeRequirements(CarRequirement requirement, ComponentStorage componentStorage) {
        componentStorage.removeTypeInCategory(requirement.getRequiredBodyType(), "Body");
        componentStorage.removeTypeInCategory(requirement.getRequiredElectronicsType(), "Electronics");
        componentStorage.removeTypeInCategory(requirement.getRequiredEngineType(), "Engine");
        componentStorage.removeTypeInCategory(requirement.getRequiredWheelType(), "Wheel");
    }
}

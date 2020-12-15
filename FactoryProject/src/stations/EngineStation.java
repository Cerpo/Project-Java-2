package stations;

import components.Engine;

import java.util.List;


public class EngineStation extends Station<Engine>{
    public EngineStation() {
        super();
    }

    public EngineStation(List<Engine> possibleTypes) {
        super(possibleTypes);
    }

    @Override
    public long getProductionTime(Engine type) {
        return type.getCylinderCapacity() + type.getNumberOfCylinders() * 200;
    }
}

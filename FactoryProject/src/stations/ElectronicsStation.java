package stations;

import components.Electronics;

import java.util.List;

public class ElectronicsStation extends Station<Electronics> {
    public ElectronicsStation() {
        super();
    }

    public ElectronicsStation(List<Electronics> possibleTypes) {
        super(possibleTypes);
    }

    @Override
    public long getProductionTime(Electronics type) {
        long productionTime = 2000;
        if (type.hasGps()) productionTime += 1000;
        if (type.hasClimate()) productionTime += 500;
        return productionTime;
    }

}

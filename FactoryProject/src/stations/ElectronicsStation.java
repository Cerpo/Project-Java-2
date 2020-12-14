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

}

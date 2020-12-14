package stations;

import components.Wheel;

import java.util.List;

public class WheelStation extends Station<Wheel> {
    public WheelStation() {
        super();
    }

    public WheelStation(List<Wheel> possibleTypes) {
        super(possibleTypes);
    }


}

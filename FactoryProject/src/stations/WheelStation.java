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

    @Override
    public long getProductionTime(Wheel type) {
        return type.getTireSize() * 10 + (type.isSummerTire() ? 0 : 500) + type.getRimSize() * 100;
    }


}

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
    public Wheel produceOne() {
        Wheel typeToProduce = getCurrentType();
        setCurrentProgress(0);
        try {
            long sleepTime = 2000; //TODO: dynamic sleep time, using typeToProduce
            for (int i = 0; i < sleepTime; i += sleepTime / batchSize) {
                setCurrentProgress(i);
                Thread.sleep(i); // simulate production time
            }
        } catch (InterruptedException e) {}
        return new Wheel(typeToProduce);
    }
}

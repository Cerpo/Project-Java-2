package stations;

import components.Body;

import java.util.List;

public class BodyStation extends Station<Body> {
    public BodyStation() {
        super();
    }

    public BodyStation(List<Body> possibleTypes) {
        super(possibleTypes);
    }

    @Override
    public Body produceOne() {
        Body typeToProduce = getCurrentType();
        setCurrentProgress(0);
        try {
            long sleepTime = 2000; //TODO: dynamic sleep time, using typeToProduce
            for (int i = 0; i < sleepTime; i += sleepTime / batchSize) {
                setCurrentProgress(i);
                Thread.sleep(i); // simulate production time
            }
        } catch (InterruptedException e) {}
        return new Body(typeToProduce);
    }
}

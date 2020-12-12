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
    public Electronics produceOne() {
        Electronics typeToProduce = getCurrentType();
        setCurrentProgress(0);
        try {
            long sleepTime = 2000; //TODO: dynamic sleep time, using typeToProduce
            for (int i = 0; i < sleepTime; i += sleepTime / batchSize) {
                setCurrentProgress(i);
                Thread.sleep(i); // simulate production time
            }
        } catch (InterruptedException e) {}
        return new Electronics(typeToProduce);
    }
}

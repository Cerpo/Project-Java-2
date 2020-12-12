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
    public Engine produceOne() {
        Engine typeToProduce = getCurrentType();
        setCurrentProgress(0);
        try {
            long sleepTime = 2000; //TODO: dynamic sleep time, using typeToProduce
            for (int i = 0; i < sleepTime; i += sleepTime / batchSize) {
                setCurrentProgress(i);
                Thread.sleep(i); // simulate production time
            }
        } catch (InterruptedException e) {}
        return new Engine(typeToProduce);
    }
}

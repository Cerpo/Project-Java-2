/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stations;

import components.Engine;

import java.util.List;

/**
 *
 * @author Cerpo
 */
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

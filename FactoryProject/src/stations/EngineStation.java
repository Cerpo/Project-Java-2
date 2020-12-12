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
}

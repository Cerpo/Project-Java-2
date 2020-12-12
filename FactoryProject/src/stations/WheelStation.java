/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stations;

import components.Wheel;

import java.util.List;

/**
 *
 * @author Cerpo
 */
public class WheelStation extends Station<Wheel> {
    public WheelStation() {
        super();
    }

    public WheelStation(List<Wheel> possibleTypes) {
        super(possibleTypes);
    }
}

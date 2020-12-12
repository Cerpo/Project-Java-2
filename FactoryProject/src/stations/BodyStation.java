/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stations;

import components.Body;

import java.util.List;

/**
 *
 * @author Cerpo
 */
public class BodyStation extends Station<Body> {
    public BodyStation() {
        super();
    }

    public BodyStation(List<Body> possibleTypes) {
        super(possibleTypes);
    }
}

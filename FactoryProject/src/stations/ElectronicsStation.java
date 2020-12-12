/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stations;

import components.Electronics;

import java.util.List;

/**
 *
 * @author Cerpo
 */
public class ElectronicsStation extends Station<Electronics> {
    public ElectronicsStation() {
        super();
    }

    public ElectronicsStation(List<Electronics> possibleTypes) {
        super(possibleTypes);
    }
}

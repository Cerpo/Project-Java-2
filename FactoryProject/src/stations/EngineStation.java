package stations;

import components.Engine;

import java.util.List;
import javafx.scene.control.ProgressBar;


public class EngineStation extends Station<Engine>{
    public EngineStation() {
        super();
    }

    public EngineStation(List<Engine> possibleTypes) {
        super(possibleTypes);
    }
}

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

}

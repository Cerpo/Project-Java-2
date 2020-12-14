package stations;

import components.Component;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public abstract class Station<T extends Component> {

    private ArrayList<T> possibleTypes;
    private int currentType;

    public Station() {
        this(new ArrayList<>());
    }

    public Station(List<T> possibleTypes) {
        this.possibleTypes = new ArrayList<>(possibleTypes);
    }

    public void addType(T type) {
        possibleTypes.add(type);
    }

    public T getCurrentType() {
        return possibleTypes.get(currentType);
    }

    public void setCurrentType(int index) {
        this.currentType = index;
    }

    public ArrayList<String> getPossibleTypes() {
        ArrayList<String> possibleTypeNames = new ArrayList<>();
        possibleTypes.forEach((item) -> {
            possibleTypeNames.add(item.getTypeCode());
        });
        return possibleTypeNames;
    }
}

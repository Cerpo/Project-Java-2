package stations;

import components.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Station<T extends Component> {
    private ArrayList<T> possibleTypes;
    private int currentType;

    private int currentProgress = 0;

    int batchSize = 10;

    public Station() {
        this(new ArrayList<>());
    }

    public Station(List<T> possibleTypes) {
        this.possibleTypes = new ArrayList<>(possibleTypes);
    }

    public abstract T produceOne();

    public void addType(T type) {
        possibleTypes.add(type);
    }

    public T getCurrentType() {
        return possibleTypes.get(currentType);
    }

    public void setCurrentType(int index){
        this.currentType = index;
    }

    public ArrayList<String> getPossibleTypes(){
        ArrayList<String> possibleTypeNames = new ArrayList<>();
        possibleTypes.forEach((item) -> {
            possibleTypeNames.add(item.getTypeCode());
        });
        return possibleTypeNames;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }
}

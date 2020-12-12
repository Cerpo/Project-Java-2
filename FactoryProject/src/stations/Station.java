package stations;

import components.Component;

import java.util.ArrayList;
import java.util.List;

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

    public T startProducing(){
        //TODO: thread start
        T typeToProduce = possibleTypes.get(currentType);
        return typeToProduce; //TODO: copy
    }

    public void stopProducing(){
        //TODO: thread stop
    }
}

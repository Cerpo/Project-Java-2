package threading;

import components.Component;
import stations.Station;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WorkerStation<T extends Component> extends SwingWorker<List<T>, Void> {
    private Station<T> station;
    private ArrayList<T> createdComponents;
    private boolean cancelled;

    public WorkerStation(Station<T> station) {
        this.station = station;
        this.createdComponents = new ArrayList<>();
        this.cancelled = false;
    }

    @Override
    protected List<T> doInBackground() {
        cancelled = false;
        createdComponents.clear();
        while (!cancelled) {
            T result = station.produceOne();
            createdComponents.add(result);
        }
        return createdComponents;
    }

    public List<T> cancel() {
        cancelled = true;
        try {
            return get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

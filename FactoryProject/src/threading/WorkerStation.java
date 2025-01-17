package threading;

import components.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stations.Station;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javafx.scene.control.ProgressBar;

public class WorkerStation<T extends Component> {

    private static final Logger logger = LogManager.getLogger("WorkerStation");

    private Station<T> station;
    private ArrayList<T> createdComponents;
    private SwingWorker<List<T>, Void> worker;
    private boolean cancelled;

    public WorkerStation(Station<T> station) {
        this.station = station;
        this.createdComponents = new ArrayList<>();
        this.cancelled = false;
    }

    public void execute() {
        worker = createWorker();
        worker.execute();
    }

    public List<T> cancel() {
        cancelled = true;
        try {
            return worker.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Station<T> getStation() {
        return station;
    }

    private SwingWorker<List<T>, Void> createWorker() {
        return new SwingWorker<List<T>, Void>() {
            private double currentProgress;
            @Override
            protected List<T> doInBackground() {
                cancelled = false;
                createdComponents.clear();
                while (!cancelled) {
                    T type = station.getCurrentType();
                    logger.info("Starting new " + type.getCategory() + " production, with type code: " + type.getTypeCode());
                    this.firePropertyChange("progress", this.currentProgress, 0.0);
                    this.currentProgress = 0;
                    try {
                        long sleepTime = station.getProductionTime(type);
                        for (int i = 0; i < sleepTime; i += sleepTime / 30) {
                            this.firePropertyChange("progress", this.currentProgress, i/(double)sleepTime);
                            this.currentProgress = i;
                            Thread.sleep(sleepTime/30); // simulate production time
                            if (cancelled)
                                return createdComponents;
                        }
                    } catch (InterruptedException ignored) {
                    }
                    createdComponents.add(type);
                    logger.info("Created new " + type.getCategory() + ", with type code: " + type.getTypeCode());
                }
                return createdComponents;
            }
        };
    }

    public void setProgressBar(ProgressBar progressBar) {
        worker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("progress".equals(evt.getPropertyName())) {
                    progressBar.setProgress(((Double)evt.getNewValue()));
                }
            }
        });
    }
}

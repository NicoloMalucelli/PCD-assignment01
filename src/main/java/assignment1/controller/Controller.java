package assignment1.controller;

import assignment1.model.ModelObserver;
import assignment1.utils.Flag;
import assignment1.utils.Results;
import assignment1.utils.SetUpInfo;

public interface Controller {
    void start(int nWorkers, SetUpInfo setUpInfo);
    Results getResults();
    void notifyObservers(ModelObserver.Event event);
    SetUpInfo getSetUpInfo();
    void stopExecution();
    Flag getStopExecutionFlag();
    void processEvent(Runnable runnable);

    long getElapsedTime();
}

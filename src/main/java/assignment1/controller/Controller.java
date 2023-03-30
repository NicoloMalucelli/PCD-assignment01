package assignment1.controller;

import assignment1.model.ModelObserver;
import assignment1.utils.Results;
import assignment1.utils.SetUpInfo;

public interface Controller {
    void start(int nWorkers);
    Results getResults();
    void notifyObservers(ModelObserver.Event event);
    SetUpInfo getSetUpInfo();
}

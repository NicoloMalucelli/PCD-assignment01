package assignment1.model;

import assignment1.utils.SetUpInfo;
import assignment1.utils.Results;

public interface Model {
    SetUpInfo getSetUpInfo();

    Results getResults();

    void addObserver(ModelObserver observer);

    void notifyObservers(ModelObserver.Event event);
}

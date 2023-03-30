package assignment1.model;

import assignment1.utils.SetUpInfo;
import assignment1.utils.SortedResultList;
import assignment1.utils.SynchronizedQueue;

public interface Model {
    SetUpInfo getSetUpInfo();

    SynchronizedQueue<String> getFiles();

    SynchronizedQueue<Result> getResults();

    SortedResultList getSortedResults();

    void addObserver(ModelObserver observer);

    void notifyObservers();
}

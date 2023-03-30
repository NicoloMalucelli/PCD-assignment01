package assignment1.controller;

import assignment1.model.Result;
import assignment1.utils.SortedResultList;
import assignment1.utils.SortedResultListImpl;
import assignment1.utils.SynchronizedQueue;
import assignment1.utils.SetUpInfo;

public interface Controller {
    void start(int nWorkers);
    SynchronizedQueue<Result> getResults();
    SynchronizedQueue<String> getFiles();
    SortedResultList getSortedResults();
    void notifyObservers();
    SetUpInfo getSetUpInfo();
}

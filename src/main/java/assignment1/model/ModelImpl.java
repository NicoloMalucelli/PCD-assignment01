package assignment1.model;

import assignment1.utils.*;

import java.util.LinkedList;
import java.util.List;

public class ModelImpl implements Model{
    private final SynchronizedQueue<String> files = new SynchronizedQueueImpl<>();
    private final SynchronizedQueue<Result> results = new SynchronizedQueueImpl<>();
    private final SortedResultList sortedResults;
    private final List<ModelObserver> observers = new LinkedList<>();
    private final SetUpInfo setUpInfo;

    public ModelImpl(SetUpInfo setUpInfo) {
        this.setUpInfo = setUpInfo;
        this.sortedResults = new SortedResultListImpl(setUpInfo.nFiles(), setUpInfo.nIntervals(), setUpInfo.lastInterval());
    }

    @Override
    public SetUpInfo getSetUpInfo() {
        return setUpInfo;
    }
    @Override
    public SynchronizedQueue<String> getFiles() {
        return files;
    }
    @Override
    public SynchronizedQueue<Result> getResults() {
        return results;
    }
    @Override
    public SortedResultList getSortedResults() {
        return sortedResults;
    }
    @Override
    public void addObserver(ModelObserver observer){
        this.observers.add(observer);
    }
    @Override
    public void notifyObservers(){
        for(ModelObserver observer: observers){
            observer.resultsUpdated();
        }
    }

}

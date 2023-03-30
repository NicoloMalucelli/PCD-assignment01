package assignment1.model;

import assignment1.utils.*;

import java.util.LinkedList;
import java.util.List;

public class ModelImpl implements Model{
    private Results results;
    private final List<ModelObserver> observers = new LinkedList<>();
    private SetUpInfo setUpInfo;

    @Override
    public void init(SetUpInfo setUpInfo){
        this.setUpInfo = setUpInfo;
        this.results = new ResultsImpl(setUpInfo.nFiles(), setUpInfo.nIntervals(), setUpInfo.lastInterval());
    }

    @Override
    public SetUpInfo getSetUpInfo() {
        return setUpInfo;
    }
    @Override
    public Results getResults() {
        return results;
    }
    @Override
    public void addObserver(ModelObserver observer){
        this.observers.add(observer);
    }
    @Override
    public void notifyObservers(ModelObserver.Event event){
        for(ModelObserver observer: observers){
            switch (event){
                case RESULTS_UPDATED -> observer.resultsUpdated();
                case COMPUTATION_ENDED -> observer.computationEnded();
            }
        }
    }

}

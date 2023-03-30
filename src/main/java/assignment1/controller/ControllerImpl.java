package assignment1.controller;

import assignment1.model.Model;
import assignment1.model.Result;
import assignment1.utils.SetUpInfo;
import assignment1.utils.SortedResultList;
import assignment1.utils.SynchronizedQueue;
import assignment1.model.MasterThread;
import assignment1.view.View;

public class ControllerImpl implements Controller{
    private final Model model;
    private final View view;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void start(int nWorkers) {
        new MasterThread(this , nWorkers).start();
    }

    @Override
    public SynchronizedQueue<Result> getResults() {
        return this.model.getResults();
    }

    @Override
    public SynchronizedQueue<String> getFiles() {
        return this.model.getFiles();
    }

    @Override
    public SortedResultList getSortedResults() {
        return model.getSortedResults();
    }

    @Override
    public void notifyObservers(){
        model.notifyObservers();
    }
    @Override
    public SetUpInfo getSetUpInfo(){
        return model.getSetUpInfo();
    }
}
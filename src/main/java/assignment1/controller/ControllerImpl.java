package assignment1.controller;

import assignment1.model.Model;
import assignment1.model.ModelObserver;
import assignment1.utils.Flag;
import assignment1.utils.SetUpInfo;
import assignment1.utils.Results;
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
    public void start(int nWorkers, SetUpInfo setUpInfo) {
        this.model.init(setUpInfo);
        this.model.getStopExecutionFlag().set(false);
        this.model.setStartTime(System.currentTimeMillis());
        new MasterThread(this , nWorkers).start();
    }
    @Override
    public Results getResults() {
        return model.getResults();
    }
    @Override
    public void notifyObservers(ModelObserver.Event event){
        model.notifyObservers(event);
    }
    @Override
    public SetUpInfo getSetUpInfo(){
        return model.getSetUpInfo();
    }
    @Override
    public void stopExecution() {
        this.model.getStopExecutionFlag().set(true);
    }
    @Override
    public Flag getStopExecutionFlag(){
        return this.model.getStopExecutionFlag();
    }
    @Override
    public void processEvent(Runnable runnable){
        new Thread(runnable).start();
    }
    @Override
    public long getElapsedTime(){
        return System.currentTimeMillis()-model.getStartTime();
    }
}
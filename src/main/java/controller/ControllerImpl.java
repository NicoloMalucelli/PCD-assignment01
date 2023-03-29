package controller;

import model.MasterThread;
import model.Model;
import model.Result;
import utils.SetUpInfo;
import utils.SynchronizedQueue;
import view.View;

import java.io.File;

public class ControllerImpl implements Controller{
    private final Model model;
    private final View view;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void start(Controller controller, SetUpInfo setUpInfo, int nWorkers) {
        new MasterThread(controller, setUpInfo, nWorkers).start();
    }

    @Override
    public SynchronizedQueue<Result> getResults() {
        return this.model.getResults();
    }

    @Override
    public SynchronizedQueue<String> getFiles() {
        return this.model.getFiles();
    }
}
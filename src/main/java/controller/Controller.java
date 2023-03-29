package controller;

import model.Model;
import model.Result;
import utils.SetUpInfo;
import utils.SynchronizedQueue;

public interface Controller {
    void start(Controller controller, SetUpInfo setUpInfo, int nWorkers);
    SynchronizedQueue<Result> getResults();
    SynchronizedQueue<String> getFiles();
}

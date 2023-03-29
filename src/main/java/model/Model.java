package model;

import utils.SynchronizedQueue;

public interface Model {
    SynchronizedQueue<String> getFiles();

    SynchronizedQueue<Result> getResults();
}

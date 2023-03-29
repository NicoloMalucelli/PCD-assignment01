package assignment1.model;

import assignment1.utils.*;

import java.util.HashMap;
import java.util.TreeMap;

public class ModelImpl implements Model{
    private final SynchronizedQueue<String> files = new SynchronizedQueueImpl<>();
    private final SynchronizedQueue<Result> results = new SynchronizedQueueImpl<>();
    private final SortedResultList sortedResults = new SortedResultListImpl();

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
}

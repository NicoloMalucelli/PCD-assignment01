package assignment1.model;

import assignment1.utils.SortedResultList;
import assignment1.utils.SynchronizedQueue;

public interface Model {
    SynchronizedQueue<String> getFiles();

    SynchronizedQueue<Result> getResults();

    SortedResultList getSortedResults();
}

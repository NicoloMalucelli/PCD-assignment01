package assignment1.utils;

import assignment1.model.Result;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class SortedResultListImpl implements SortedResultList{

    private final Set<Result> results = new TreeSet<>();
    private Lock mutex = new ReentrantLock();

    @Override
    public List<Result> getFirstN(int nElems) {
        try{
            mutex.lock();
            return results.stream().limit(nElems).collect(Collectors.toList());
        }finally {
            mutex.unlock();
        }
    }

    @Override
    public void add(Result item) {
        try{
            mutex.lock();
            this.results.add(item);
        }finally {
            mutex.unlock();
        }
    }
}

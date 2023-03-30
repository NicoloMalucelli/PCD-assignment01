package assignment1.utils;

import assignment1.model.Result;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class SortedResultListImpl implements SortedResultList{

    private final Set<Result> results = new TreeSet<>();
    private final Map<Interval, Integer> distribution = new HashMap<>();
    private final int nResults;
    private Lock mutex = new ReentrantLock();

    public SortedResultListImpl(int nResults, int nIntervals, int lastInterval) {
        this.nResults = nResults;
        if(nIntervals == 1){
            distribution.put(new Interval(0, Integer.MAX_VALUE), 0);
        }else {
            final int intervalSize = lastInterval / (nIntervals - 1);
            for (int i = 0; i < nIntervals - 2; i++) {
                distribution.put(new Interval(intervalSize * i, intervalSize * (i + 1)), 0);
            }
            distribution.put(new Interval(intervalSize * (nIntervals - 2), lastInterval), 0);
            distribution.put(new Interval(lastInterval, Integer.MAX_VALUE), 0);
        }
    }

    @Override
    public List<Result> getResults() {
        try{
            mutex.lock();
            return results.stream().limit(nResults).collect(Collectors.toList());
        }finally {
            mutex.unlock();
        }
    }

    @Override
    public Map<Interval, Integer> getDistribution() {
        return this.distribution;
    }

    @Override
    public void add(Result item) {
        try{
            mutex.lock();
            for(Map.Entry<Interval, Integer> entry : distribution.entrySet()){
                if(entry.getKey().contains(item.lines())){
                    entry.setValue(entry.getValue() + 1);
                }
            }
            this.results.add(item);
        }finally {
            mutex.unlock();
        }
    }


}

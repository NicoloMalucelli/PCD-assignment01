package utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SynchronizedQueueImpl<T> implements SynchronizedQueue<T>{

    private final List<T> list = new LinkedList<>();
    private Lock mutex;
    private Condition notEmpty;

    @Override
    public void add(T elem) {
        try {
            mutex.lock();
            list.add(elem);
            notEmpty.signal();
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public T remove() throws InterruptedException {
        try {
            mutex.lock();
            if(this.list.isEmpty()){
                notEmpty.await();
            }
            return list.remove(0);
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            mutex.lock();
            return list.isEmpty();
        } finally {
            mutex.unlock();
        }
    }
}

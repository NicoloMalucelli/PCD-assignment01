package assignment1.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedQueueImpl<T> implements SynchronizedQueue<T>{

    private final List<T> list = new LinkedList<>();
    private Lock mutex = new ReentrantLock();
    private Condition notEmpty = mutex.newCondition();

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
    public T blockingRemove() throws InterruptedException {
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
    public Optional<T> remove(){
        try {
            mutex.lock();
            if(this.list.isEmpty()){
                return Optional.empty();
            }
            return Optional.of(list.remove(0));
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

    @Override
    public String toString() {
        try {
            mutex.lock();
            return "SynchronizedQueueImpl{" +
                    "list=" + list +
                    '}';
        } finally {
            mutex.unlock();
        }
    }
}

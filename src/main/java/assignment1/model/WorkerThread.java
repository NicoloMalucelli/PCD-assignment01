package assignment1.model;

import assignment1.utils.Log;
import assignment1.utils.SynchronizedQueue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class WorkerThread extends Thread {

    private final SynchronizedQueue<String> files;
    private final SynchronizedQueue<Result> results;

    public WorkerThread(SynchronizedQueue<String> files, SynchronizedQueue<Result> results) {
        this.files = files;
        this.results = results;
    }

    @Override
    public void run() {
        while (true) {
            final Optional<String> path = files.remove();
            if (path.isEmpty()) {
                break;
            }
            //Log.log("Thread counting lines of " + path.get());
            final Result result = new Result(path.get(), countLines(path.get()));
            //Log.log("Thread" + path.get() + " has " + result.lines() + " lines");
            results.add(result);
        }
    }

    private int countLines(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

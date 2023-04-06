package assignment1.model;

import assignment1.utils.Flag;
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
    private final Flag stopExecutionFlag;

    public WorkerThread(SynchronizedQueue<String> files, SynchronizedQueue<Result> results, Flag stopExecutionFlag) {
        this.files = files;
        this.results = results;
        this.stopExecutionFlag = stopExecutionFlag;
    }

    @Override
    public void run() {
        while (!stopExecutionFlag.get()) {
            final Optional<String> path = files.remove();
            if (path.isEmpty()) {
                break;
            }
            final Result result = new Result(path.get(), countLines(path.get()));
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

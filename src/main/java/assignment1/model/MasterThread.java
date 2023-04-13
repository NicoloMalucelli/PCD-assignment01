package assignment1.model;

import assignment1.controller.Controller;
import assignment1.utils.Flag;
import assignment1.utils.SynchronizedQueue;
import assignment1.utils.SynchronizedQueueImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MasterThread extends Thread{
    public static final int NUM_OF_WORKERS = Runtime.getRuntime().availableProcessors();
    private final Controller controller;
    private final int nWorkers;
    private final SynchronizedQueue<String> files = new SynchronizedQueueImpl<>();
    private final SynchronizedQueue<Result> results = new SynchronizedQueueImpl<>();
    public MasterThread(Controller controller, int nWorkers) {
        this.controller = controller;
        this.nWorkers = nWorkers;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("MasterThread");
        this.searchFiles();
        final Flag stopExecutionFlag = this.controller.getStopExecutionFlag();
        IntStream.range(0, nWorkers).forEach(i -> {
            Thread worker = new WorkerThread(files, results, stopExecutionFlag);
            worker.setName("Worker[" + i + "]");
            worker.start();
        });
        for (int i = 0, numOfFiles = this.files.size(); i < numOfFiles && !stopExecutionFlag.get(); i++) {
            try {
                final Result result = results.blockingRemove();
                this.controller.getResults().add(result);
                this.controller.notifyObservers(ModelObserver.Event.RESULTS_UPDATED);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.controller.notifyObservers(ModelObserver.Event.COMPUTATION_ENDED);
    }

    private void searchFiles(){
        try (Stream<Path> walkStream = Files.walk(Paths.get(controller.getSetUpInfo().startDir()))) {
            walkStream.filter(p -> p.toFile().isFile() && p.toString().endsWith(".java"))
                    .map(Path::toString)
                    .forEach(p -> files.add(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
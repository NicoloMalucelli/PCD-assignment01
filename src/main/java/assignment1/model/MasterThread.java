package assignment1.model;

import assignment1.controller.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MasterThread extends Thread{

    private final Controller controller;
    private final int nWorkers;
    public MasterThread(Controller controller, int nWorkers) {
        this.controller = controller;
        this.nWorkers = nWorkers;
    }

    @Override
    public void run() {
        this.searchFiles();
        IntStream.range(0, nWorkers).forEach(i -> new WorkerThread(this.controller.getFiles(), this.controller.getResults()).start());
        while(true){
            try {
                final Result result = this.controller.getResults().blockingRemove();
                this.controller.getSortedResults().add(result);
                this.controller.notifyObservers();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void searchFiles(){
        try (Stream<Path> walkStream = Files.walk(Paths.get(controller.getSetUpInfo().startDir()))) {
            walkStream.filter(p -> p.toFile().isFile() && p.toString().endsWith(".java"))
                    .map(Path::toString)
                    .forEach(p -> this.controller.getFiles().add(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
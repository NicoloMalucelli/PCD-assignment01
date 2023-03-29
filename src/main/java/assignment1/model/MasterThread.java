package assignment1.model;

import assignment1.controller.Controller;
import assignment1.utils.Log;
import assignment1.utils.SetUpInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MasterThread extends Thread{

    private final Controller controller;
    private final SetUpInfo setUpInfo;
    private final int nWorkers;
    public MasterThread(Controller controller, SetUpInfo setUpInfo, int nWorkers) {
        this.controller = controller;
        this.setUpInfo = setUpInfo;
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
                Log.log(this.controller.getSortedResults().getFirstN(10).toString());
                Log.log("\n\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void searchFiles(){
        try (Stream<Path> walkStream = Files.walk(Paths.get(setUpInfo.startDir()))) {
            walkStream.filter(p -> p.toFile().isFile() && p.toString().endsWith(".java"))
                    .limit(setUpInfo.nFiles())
                    .map(Path::toString)
                    .forEach(p -> this.controller.getFiles().add(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package assignment1.view;

import assignment1.controller.Controller;
import assignment1.model.Result;
import assignment1.utils.Interval;

import java.util.Map;

public class ConsoleView implements View{
    private Controller controller;
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void resultsUpdated() {
    }

    @Override
    public void computationEnded() {
        System.out.println("\nfiles with most lines:");
        for (Result result : this.controller.getResults().getRanking()) {
            System.out.println(result.filePath() + " -> " + result.lines() + " lines");
        }
        System.out.println("\nlines distribution:");
        for (Map.Entry<Interval, Integer> e : this.controller.getResults().getDistribution().entrySet()) {
            System.out.println("files of " + e.getKey() + " lines: " + e.getValue());
        }
    }


}
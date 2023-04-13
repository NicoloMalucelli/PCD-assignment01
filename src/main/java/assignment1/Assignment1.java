package assignment1;

import assignment1.controller.Controller;
import assignment1.controller.ControllerImpl;
import assignment1.model.Model;
import assignment1.model.ModelImpl;
import assignment1.utils.SetUpInfo;
import assignment1.utils.Strings;
import assignment1.view.ConsoleView;
import assignment1.view.View;

import java.util.Scanner;

import static assignment1.model.MasterThread.NUM_OF_WORKERS;

public class Assignment1 {

    public static void main(String[] args){
        String tmp;
        System.out.print("Root directory: ");
        Scanner scanner = new Scanner(System.in);
        final String dir = scanner.nextLine(); //C:\Users\nicol\Documents\Progetti\scarabeo

        do{
            System.out.print("Number of files to visualize: ");
            tmp = scanner.nextLine();
        }while (!Strings.isNumeric(tmp) || Integer.parseInt(tmp) <= 1);
        final Integer nFiles = Integer.parseInt(tmp);

        do{
            System.out.print("Number of  interval to visualize: ");
            tmp = scanner.nextLine();
        }while (!Strings.isNumeric(tmp) || Integer.parseInt(tmp) <= 1);
        final Integer nIntervals = Integer.parseInt(tmp);

        do{
            System.out.print("Last interval max: ");
            tmp = scanner.nextLine();
        }while (!Strings.isNumeric(tmp) || Integer.parseInt(tmp) <= 1);
        final Integer lastInterval = Integer.parseInt(tmp);

        final SetUpInfo setUpInfo = new SetUpInfo(dir, nFiles, nIntervals, lastInterval);
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        final Controller controller = new ControllerImpl(model, view);

        model.addObserver(view);
        controller.start(NUM_OF_WORKERS, setUpInfo);
    }

}
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

public class Scalability {

    public static void main(String[] args){
        //C:\Users\nicol\Documents\Progetti\scarabeo
        //C:\Users\nicol\Documents\Progetti\PCD\java-design-patterns
        //C:\Users\nicol\Documents\Progetti\PCD\spring-framework
        final SetUpInfo setUpInfo = new SetUpInfo("C:\\Users\\nicol\\Documents\\Progetti\\scarabeo", 10, 10, 150);
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        final Controller controller = new ControllerImpl(model, view);

        model.addObserver(view);
        controller.start(NUM_OF_WORKERS, setUpInfo);
    }

}
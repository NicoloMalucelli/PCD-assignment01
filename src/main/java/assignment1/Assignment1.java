package assignment1;

import assignment1.controller.Controller;
import assignment1.controller.ControllerImpl;
import assignment1.model.Model;
import assignment1.model.ModelImpl;
import assignment1.utils.SetUpInfo;
import assignment1.view.ConsoleView;
import assignment1.view.View;

public class Assignment1 {

    public static void main(String[] args){
        final SetUpInfo setUpInfo = new SetUpInfo("C:\\Users\\nicol\\Documents\\Progetti\\scarabeo", 50, 2, 100);
        final Model model = new ModelImpl(setUpInfo);
        final View view = new ConsoleView();
        final Controller controller = new ControllerImpl(model, view);

        model.addObserver(view);
        controller.start(2);
    }

}
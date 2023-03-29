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
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        final Controller controller = new ControllerImpl(model, view);

        final SetUpInfo setUpInfo = new SetUpInfo("C:\\Users\\nicol\\Documents\\Progetti\\scarabeo", 50, 1, 100);
        controller.start(setUpInfo, 2);
    }

}

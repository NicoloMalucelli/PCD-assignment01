package assignment1.view;

import assignment1.controller.Controller;

public class ConsoleView implements View{
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}

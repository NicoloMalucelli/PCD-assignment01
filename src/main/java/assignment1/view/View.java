package assignment1.view;

import assignment1.controller.Controller;
import assignment1.model.ModelObserver;

public interface View extends ModelObserver {
    void setController(Controller controller);
}

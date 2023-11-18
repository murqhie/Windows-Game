package Controllers;
import Models.Model;
import Views.IView;

public class Controller implements IController {
    private Model model;
    private IView view;

    @Override
    public void nextFrame() {

    }

    public void setModel(Model model) {
        this.model = model;
    }
    public void setView(IView view) {
        this.view = view;
    }
}

import processing.core.PApplet;
import Models.Model;
import Controllers.Controller;
import Views.DefaultView;

public final class Main {
    public static void main(String[] args) {

        var model = new Model();
        var controller = new Controller();
        var view = new DefaultView(1000,800);

        // Connect M, V and C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);

        // Starts the processing application
        PApplet.runSketch(new String[]{""}, view);
    }
}
import processing.core.PApplet;
import Models.Model;
import Controllers.Controller;
import Views.View;

public final class Main {
    public static void main(String[] args) {

        var model = new Model();
        var controller = new Controller();
        var view = new View();

        // Connect M, V and C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);

        // Starts the processing application
        PApplet.runSketch(new String[]{""}, view);
    }
}
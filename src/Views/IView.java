package Views;

import Models.DataStructures.Vector;

public interface IView {
    void drawPlaying();

    Vector getMousePosition();

    int getScreenWidth();

    int getScreenHeight();
}

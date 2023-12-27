package Views;

import Models.DataStructures.Vector;

public interface IView {
    void drawPlaying();
    void drawGameOver();
    void drawStart();

    Vector getMousePosition();

    int getScreenWidth();

    int getScreenHeight();
    void showStartButton();
}

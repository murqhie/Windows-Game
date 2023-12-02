package Controllers;
import Models.Objects.Player;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public interface IController {
    void nextFrame();
    Player getPlayer();
    void setGameState(String state);
    void setWindow(int winWidth, int winHeight);
    void handleKeyPressed(KeyEvent event);
    void handleKeyReleased(KeyEvent event);
    void handleMouse(MouseEvent event);
}

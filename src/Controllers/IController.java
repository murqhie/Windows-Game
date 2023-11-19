package Controllers;
import Models.Objects.Player;

import java.awt.event.KeyEvent;

public interface IController {
    void nextFrame();
    Player getPlayer();
    void setGameState(String state);
    void setWindow(int winWidth, int winHeight);
    void handleKeyboard(KeyEvent event);
}

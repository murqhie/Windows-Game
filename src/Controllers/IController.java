package Controllers;
import Models.Objects.Player;
import Models.Objects.Window;

public interface IController {
    void nextFrame();
    Player getPlayer();
    void setGameState(String state);
    void setWindow(int winWidth, int winHeight);
}

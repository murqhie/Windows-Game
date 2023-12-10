package Controllers;
import Models.Objects.Enemy;
import Models.Objects.ICharacter;
import Models.Objects.Player;
import Models.Objects.Window;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.ArrayList;

public interface IController {
    void tick();
    Player getPlayer();
    void setGameState(String state);
    void handleKeyPressed(KeyEvent event);
    void handleKeyReleased(KeyEvent event);
    void handleMousePressed(MouseEvent event);
    void handleMouseReleased(MouseEvent event);
    ArrayList<Window> getEnemyWindows();
    Window getMainWindow();
    ArrayList<Enemy> getEnemies();
}

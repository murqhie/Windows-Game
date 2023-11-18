package Controllers;
import Models.Model;
import Models.Objects.Player;
import Views.IView;
import Models.DataStructures.GameState;
public class Controller implements IController {
    private Model model;
    private IView view;

    @Override
    public void nextFrame() {

        if(model.getState() == GameState.START){
            model.startNewGame();
            model.setState(GameState.PLAYING);
        }
        if(model.getState() == GameState.PLAYING){
            view.drawPlaying();
        }
    }
    public Player getPlayer(){
        return model.getPlayer();
    }
    public void setModel(Model model) {this.model = model;}
    public void setView(IView view) {this.view = view;}
    public void setGameState(String state){
        if(state.equals("START")){
            model.setState(GameState.START);
        }
        if(state.equals("PLAYING")){
            model.setState(GameState.PLAYING);
        }
        if(state.equals("GAME_OVER")){
            model.setState(GameState.GAME_OVER);
        }
    }
    public void setWindow(int winWidth, int winHeight) {
        model.getWindow().setWidth(winWidth);
        model.getWindow().setHeight(winHeight);
    }
}

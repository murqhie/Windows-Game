package Models;
import Models.DataStructures.GameState;
import Models.DataStructures.Vector;
import Models.Objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;



public class ModelTest {
    Model model;
    AntiCursor antiCursor;
    Vector trashBinCoordinates = new Vector(65,220);
    @BeforeEach
    void setup(){
        model = new Model();
        model.startNewGame(1920,1080);

    }
    @Test
    void testAddingEnemy(){
        Assertions.assertTrue(model.getEnemies().isEmpty());
        for (int i = 0; i < 2000; i++) {
            model.addEnemy();
        }
        Assertions.assertFalse(model.getEnemies().isEmpty());
        int antiCursorCount = 0;
        for (Object enemy : model.getEnemies()) {
            if (enemy instanceof AntiCursor) {
                antiCursorCount++;
            }
        }
        Assertions.assertTrue(antiCursorCount <= 1);
    }


    @Test
    void testPlayerMovement(){
        model.getPlayer().setKeyInputs(0,true);

        Vector velocity = new Vector(0,0).add(new Vector(0,-1)).multiply(1- 0.1f);
        Vector position = model.getPlayer().getPosition().add(velocity);

        model.getPlayer().move();

        Assertions.assertEquals(model.getPlayer().getX(), position.getX());
        Assertions.assertEquals(model.getPlayer().getY(), position.getY());

    }

    @Test
    void testPlayerAttack(){
        model.getPlayer().setMouseInput(true);
        model.getPlayer().setShootDirection(new Vector(0,0));
        model.getPlayer().attack(model.getProjectiles());
        Assertions.assertFalse(model.getProjectiles().isEmpty());
    }

    @Test
    void testPlayerCollidingWithWindow(){
        for (int i = 0; i < 100; i++) {
            model.getPlayer().attack(model.getProjectiles());
        }
        Assertions.assertFalse(model.getPlayer().isCollidingWithWindow());
    }

    @Test
    void testAntiCursorAttack(){
        antiCursor = new AntiCursor(new Vector(0,0),model.getPlayer());
        int numberOfProjectiles = model.getProjectiles().size();
        antiCursor.attack(model.getProjectiles());
        Assertions.assertEquals(numberOfProjectiles, model.getProjectiles().size());
    }
    @Test
    void testAntiCursorMovesTowardsTrashBin(){
        antiCursor = new AntiCursor(new Vector(0,0),model.getPlayer());

        Vector distance = trashBinCoordinates.add(new Vector(0,0).multiply(-1));
        Vector velocity = distance.unit().multiply(8);
        Vector position = antiCursor.getPosition().add(velocity);

        antiCursor.move();

        Assertions.assertEquals(antiCursor.getX(),position.getX());
        Assertions.assertEquals(antiCursor.getY(),position.getY());
    }
    @Test
    void testHasTrashBin(){
        antiCursor = new AntiCursor(trashBinCoordinates, model.getPlayer());
        Assertions.assertFalse(antiCursor.hasTrashBin());
        antiCursor.move();
        Assertions.assertTrue(antiCursor.hasTrashBin());
    }

    @Test
    void testAntiCursorCollidesWithPlayer(){
        antiCursor = new AntiCursor(model.getPlayer().getPosition(), model.getPlayer());
        antiCursor.collidesWithPlayer(model.getPlayer());
        Assertions.assertTrue(model.getPlayer().isDead());
    }

    @Test
    void testBugAttack(){
        Bug bug = new Bug(new Vector(0,0), model.getPlayer());
        Projectile projectile = new Projectile(new Vector(0,0), new Vector(0,0),"player",5);
        projectile.collidesWithEnemy(new ArrayList<>(List.of(bug)));
        bug.attack(model.getProjectiles());
        Assertions.assertFalse(model.getProjectiles().isEmpty());
    }

    @Test
    void testBugDistance(){
        Bug bug = new Bug(new Vector(0,0), model.getPlayer());
        Vector distance = model.getPlayer().getPosition().add(bug.getPosition().multiply(-1));
        bug.move();
        Assertions.assertEquals(bug.getDistance().getX(), distance.getX());
        Assertions.assertEquals(bug.getDistance().getY(), distance.getY());
    }


    @Test
    void testVirusAttack(){
        Virus virus = new Virus(new Vector(0,0), model.getPlayer(),new Window(100,100,100,100));
        virus.attack(model.getProjectiles());
        Assertions.assertFalse(model.getProjectiles().isEmpty());
    }

    @Test
    void testVirusGetsHit(){
        Virus virus = new Virus(new Vector(0,0), model.getPlayer(),new Window(100,100,100,100));
        model.getEnemies().add(virus);

        Projectile projectile = new Projectile(new Vector(0,0), new Vector(0,0),"player",5);

        projectile.collidesWithEnemy(model.getEnemies());
        Assertions.assertTrue(virus.isDead());
    }
    @Test
    void detectCollisions(){


        model.getEnemies().add(new Bug(new Vector(0,0),model.getPlayer()));
        model.getEnemies().add(new Virus(new Vector(100,100), model.getPlayer(), model.getMainWindow()));
        model.getEnemies().add(new AntiCursor(new Vector(200,200),model.getPlayer()));

        Projectile projectile0 = new Projectile(model.getEnemies().get(0).getPosition(),new Vector(0,0),"player",5);
        Projectile projectile1 = new Projectile(model.getEnemies().get(1).getPosition(),new Vector(0,0),"player",5);
        Projectile projectile2 = new Projectile(model.getEnemies().get(2).getPosition(),new Vector(0,0),"player",5);
        model.getProjectiles().add(projectile0);
        model.getProjectiles().add(projectile1);
        model.getProjectiles().add(projectile2);

        model.detectCollision();
        Assertions.assertTrue(
                projectile0.isCollided() &&
                        projectile1.isCollided() &&
                        projectile2.isCollided());

        Assertions.assertTrue(
                model.getEnemies().get(0).isDead() &&
                        model.getEnemies().get(1).isDead() &&
                        model.getEnemies().get(2).isDead());
    }


    @Test
    void testProjectileGetsOutOfWindow(){
        Projectile projectile0 = new Projectile(new Vector(500,500),new Vector(0,0),"player", 5);
        projectile0.getsOutOfWindow( new Window(10,10, new Vector(0,0)),model.getWindows());
        Assertions.assertTrue(projectile0.isCollided());

        Projectile projectile1 = new Projectile(new Vector(500,500),new Vector(0,0),"player", 5);
        projectile1.getsOutOfWindow( new Window(500,500, new Vector(400,400)),model.getWindows());
        Assertions.assertFalse(projectile1.isCollided());

        Projectile projectile2 = new Projectile(new Vector(500,500),new Vector(0,0),"player", 5);
        model.getWindows().add(new Window(500,500, new Vector(400,400)));
        projectile2.getsOutOfWindow( new Window(0,0, new Vector(0,0)),model.getWindows());
        Assertions.assertFalse(projectile2.isCollided());
    }

    @Test
    void testProjectileCollisions(){
        Projectile projectile = new Projectile(model.getPlayer().getPosition(),new Vector(0,0),"player", 5);
        projectile.collidesWithEnemy(model.getEnemies());
        Assertions.assertFalse(projectile.isCollided());

        projectile.collidesWithPlayer(model.getPlayer());
        Assertions.assertTrue(projectile.isCollided());
    }

    @Test
    void testProjectileMovement(){
        Projectile projectile = new Projectile(new Vector(0,0),new Vector(10,10),"player", 5);
        projectile.move();
        Assertions.assertEquals(projectile.getX(),10);
        Assertions.assertEquals(projectile.getY(),10);
    }

    @Test
    void testProjectileGetRadius(){
        Projectile projectile = new Projectile(new Vector(0,0),new Vector(0,0),"enemy",5);
        Assertions.assertEquals(5,projectile.getRadius());
    }
    @Test
    void testIfGameEndsWhenPlayerDead(){
        model.setState(GameState.START);
        model.getPlayer().getsHit();
        model.checkGameOver();
        Assertions.assertSame(model.getState(), GameState.GAME_OVER);
    }

    @Test
    void testScores(){
        Assertions.assertSame(model.getScore(),0);
        model.addEnemy();
        model.getProjectiles().add(new Projectile(model.getEnemies().get(0).getPosition(),new Vector(0,0),"player",5));
        model.detectCollision();
        int score = model.getScore();
        Assertions.assertTrue(score != 0);
        model.startNewGame(100,100);
        Assertions.assertEquals(model.getHighScore(), score);
        model.setScore(0);
        Assertions.assertEquals(0, model.getScore());
    }

    @Test
    void testWindowMovement(){
        Window window = new Window(100,100,1000,1000);
        Vector positionBefore = window.getPosition();
        window.setAcceleration(new Vector(5,5));
        window.move();
        Assertions.assertNotSame(positionBefore, window.getPosition());}


}

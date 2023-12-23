package Models.Objects;

import java.util.ArrayList;

public interface ICharacter {
    void move();
    void attack(ArrayList<Projectile> projectiles);
    void getsHit();
    int getColor();
    float getX();
    float getY();
    int getRadius();
    boolean isDead();
    boolean isInWindow();}

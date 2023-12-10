package Models.Objects;

import java.util.Collection;

public interface ICharacter {
    void move();
    void attack();
    void getsHit();
    int getColor();
    float getX();
    float getY();
    int getRadius();
    boolean isDead();
    boolean isInWindow();
    Collection<Projectile> getProjectiles();
}

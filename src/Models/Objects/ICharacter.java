package Models.Objects;

public interface ICharacter {
    void move();
    void attack();
    void getsHit();

    int getColor();

    float getX();

    float getY();

    int getRadius();
    boolean isDead();
}

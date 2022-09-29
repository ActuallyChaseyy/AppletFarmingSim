import java.awt.*;

@SuppressWarnings("all")
public class Wheat extends Plant{
    private int xPos, yPos, w, h;
    public static boolean isTouch = false;
    private Color wheatColor;

    public int c1, c2, c3;

    private boolean isDead = false;

    private int oldX, oldY;

    public long respawnTimer;

    private long time;

    private long maxAge;
    public long dieTimer;

    private long ageTimer;
    private int hitBoxX, hitBoxY, hitBoxWidth, hitBoxHeight;

    private int lvl;
    public Wheat(Graphics g, int _xPos, int _yPos, int Width, int Height, int _hitBoxX, int _hitBoxY) {
        xPos = _xPos;
        yPos = _yPos;
        oldX = xPos;
        oldY = yPos;
        w = Width;
        h = Height;
        hitBoxX = _hitBoxX;
        hitBoxY = _hitBoxY;
        hitBoxWidth = hitBoxX + w;
        hitBoxHeight = hitBoxY + h;

        c1 = 255;
        c2 = 186;
        c3 = 10;

        wheatColor = new Color(c1,c2,c3);

        maxAge = genRand(6001, 10001);
        dieTimer = System.currentTimeMillis() + maxAge;
    }

    public void draw(Graphics g) {
        this.checkRespawn();
        this.age();

        g.setColor(wheatColor);
        g.fillRect(xPos, yPos, w, h);
    }

    /* public boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (Player.getFarmingMode() == 0) {
            if (playerX + playerWidth > this.xPos && playerX < this.xPos + 150 && playerY + playerHeight > this.yPos && playerY < this.yPos + 150) {

                Score.addScore(1);

                respawnTimer = System.currentTimeMillis() + 500000;
                isDead = true;
                return isTouch = true;
            }
        }
        return isTouch = false;
    } */

    public boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (Player.getFarmingMode() == 0) {
            if (!isDead && playerX + playerWidth > this.hitBoxX && playerX < this.hitBoxWidth && playerY + playerHeight > this.hitBoxY && playerY < this.hitBoxHeight) {

                Score.addScore(1);

                respawnTimer = System.currentTimeMillis() + 500000;
                isDead = true;

                return isTouch = true;
            }
        }

        else if (Player.getFarmingMode() == 1) {
            if (isDead && playerX + playerWidth > this.hitBoxX && playerX < this.hitBoxWidth && playerY + playerHeight > this.hitBoxY && playerY < this.hitBoxHeight) {

                // Respawn wheat, don't add score
                this.forceRespawn();
                return isTouch = true;
            }
        }
        return isTouch = false;
    }


    public int getXPosition() {
        return xPos;
    }

    public void changeColor(int _r, int _g, int _b) {
        c1 = _r;
        c2 = _g;
        c3 = _b;

        wheatColor = new Color(_r,_g,_b);
    }

    public void setPosition(int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;
    }

    public void checkRespawn() {
        if (System.currentTimeMillis() >= respawnTimer && isDead) {
            this.setPosition(oldX, oldY);
            isDead = false;

            maxAge = genRand(15001, 30001);
            dieTimer = System.currentTimeMillis() + maxAge;

            ageTimer = System.currentTimeMillis() + 1000;
        }
    }

    public void forceRespawn() {
        respawnTimer = 0;
    }

    private void age() {
        if (System.currentTimeMillis() >= dieTimer && !isDead) {
            isDead = true;
            this.setPosition(-2000, -2000);

            respawnTimer = System.currentTimeMillis() + 500000;
        }
    }

    public long genRand(long min, long max) {
        return (long) (Math.random() * (max - min + 1) + min);
    }

}

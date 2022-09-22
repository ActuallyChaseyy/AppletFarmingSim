import java.awt.*;

@SuppressWarnings("all")
public class Wheat {
    private int xPos, yPos, w, h;
    public static boolean isTouch = false;
    private Color wheatColor;

    public int c1, c2, c3;

    private boolean isDead = false;

    private int oldX, oldY;

    private long respawnTimer;

    private long time;
    public Wheat(Graphics g, int _xPos, int _yPos, int Width, int Height) {
        xPos = _xPos;
        yPos = _yPos;
        oldX = xPos;
        oldY = yPos;
        w = Width;
        h = Height;

        c1 = 255;
        c2 = 186;
        c3 = 10;

        wheatColor = new Color(c1,c2,c3);
    }

    public void draw(Graphics g) {
        this.checkRespawn();

        g.setColor(wheatColor);
        g.fillRect(xPos, yPos, w, h);
    }

    public boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (Player.getFarmingMode() == 0) {
            if (playerX + playerWidth > this.xPos && playerX < this.xPos + 150 && playerY + playerHeight > this.yPos && playerY < this.yPos + 150) {

                Score.addScore(1);

                respawnTimer = System.currentTimeMillis() + 5000;
                isDead = true;
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
        }
    }

}

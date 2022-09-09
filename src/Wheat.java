import java.awt.*;

public class Wheat extends Plant{
    private int xPos, yPos;
    public static boolean isTouch = false;

    public Wheat(Graphics g, int _xPos, int _yPos, int Width, int Height) {
        xPos = _xPos;
        yPos = _yPos;

        g.setColor(new Color(255,186,1));
        g.fillRect(_xPos, _yPos, Width, Height);

    }

    public boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (playerX + playerWidth > this.xPos && playerX < this.xPos + 150 && playerY + playerHeight > this.yPos && playerY < this.yPos + 150) {
            return isTouch = true;
        }
        return isTouch = false;
    }


    public int getXPosition() {
        return xPos;
    }

}

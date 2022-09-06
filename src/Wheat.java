import java.awt.*;

public class Wheat extends Plant{
    private static int xPos, yPos;
    public static boolean isTouch = false;

    public Wheat(Graphics g, int _xPos, int _yPos, int Width, int Height) {
        xPos = _xPos;
        yPos = _yPos;

        g.setColor(new Color(255,186,1));
        g.fillRect(_xPos, _yPos, Width, Height);

    }

    public static boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (playerX + playerWidth > xPos && playerX < xPos + 50 && playerY + playerHeight > yPos && playerY < yPos + 50) {
            return isTouch = true;
        }
        return isTouch = false;
    }


    public static int getXPosition() {
        return xPos;
    }

}

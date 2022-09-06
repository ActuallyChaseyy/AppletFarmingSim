import java.awt.*;

public class Wheat extends Plant{
    private static int xPos, yPos;

    public Wheat(Graphics g, int _xPos, int _yPos, int Width, int Height) {
        xPos = _xPos;
        yPos = _yPos;

        g.setColor(new Color(255,186,1));
        g.fillRect(_xPos, _yPos, Width, Height);


    }

    public static int getXPosition() {
        return xPos;
    }

}

import java.awt.*;

public class Wheat extends Plant{

    public Wheat(Graphics g, int xPos, int yPos, int Width, int Height) {
        g.setColor(new Color(255,186,1));
        g.fillRect(xPos, yPos, Width, Height);
    }

}

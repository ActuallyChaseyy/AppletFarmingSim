import java.awt.*;

@SuppressWarnings("all")
public class Soil {

    int Width, Height;
    int xPos, yPos;

    int tiles;

    public Soil(int _Width, int _Height, int _xPos, int _yPos) {

        Width = _Width;
        Height = _Height;

        xPos = _xPos;
        yPos = _yPos;

        tiles = 1;

    }

    public void placeSoil(Graphics g, int _Width, int _Height, int xPos, int yPos) {
        g.setColor(new Color(106,74,58));
        g.fillRect(xPos, yPos, _Width, _Height);
    }

}

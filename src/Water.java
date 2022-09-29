import java.awt.*;

@SuppressWarnings("ALL")
public class Water extends ToolBlockGeneric {
    private Color waterColor;

    public Water(Graphics g, int _xpos, int _ypos, int _w, int _h) {
        xPos = _xpos;
        yPos = _ypos;
        Width = _w;
        Height = _h;

        waterColor = new Color(20, 20, 196);
    }

    public boolean playerIsTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (playerX + playerWidth > this.xPos && playerX < this.xPos + 150 && playerY + playerHeight > this.yPos && playerY < this.yPos + 150) {
            Player.setFarmingMode(2);

            super.placeholderMethod();

            return true;
        }
        return false;
    }

    public void setWaterColor(int r, int g, int b) {
        waterColor = new Color(r, g, b);
    }

    public Color getWaterColor() {
        return waterColor;
    }

}

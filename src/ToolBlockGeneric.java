import java.awt.*;

public class ToolBlockGeneric {
    public int xPos, yPos, Width, Height;

    public void draw(Graphics g, Color _c) {
        g.setColor(_c);
        g.fillRect(xPos, yPos, Width, Height);
    }

    public void setPosition(int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;
    }
}

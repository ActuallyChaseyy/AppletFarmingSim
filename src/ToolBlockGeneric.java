import java.awt.*;

public abstract class ToolBlockGeneric {
    public int xPos, yPos, Width, Height;

    public void drawDefault(Graphics g, Color _c) {
        g.setColor(_c);
        g.fillRect(xPos, yPos, Width, Height);
    }

    public void setPosition(int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;
    }

    public void placeholderMethod() {
        System.out.println("You just used the super thingy. good job i guess.");
    }

    public abstract void draw(Graphics g);
}

import java.awt.*;

public class Scene {

    private Color bgColor;

    public Scene() {
        bgColor = new Color(255, 255, 255);
    }

    public void drawBackground(Graphics g, int r, int gr, int b, int width, int height) {
        bgColor = new Color(r,gr,b);
        g.setColor(bgColor);
        g.fillRect(0,0, width, height);
    }

    public String getBgColor() {
        return bgColor.toString();
    }

}

import java.awt.*;

public class Level {
    /*
      I made level its own class, to try to keep Engine.java slightly cleaner.
      The only thing this class will really do is provide Level.DrawLevel() and return information about the scene
      for the sake of debug.
     */

    private Color pathColor;
    private Color grassColor;

    public Level() {
        grassColor = new Color(26, 65, 5);
        pathColor = new Color(54, 126, 9);
    }

    public void drawPath(Graphics g) {
        g.setColor(pathColor);
        g.fillRect(25, 25, 400, 150);
        g.fillRect(400, 25, 150, 800);
        g.fillRect(550, 350, 450, 150);
    }

    public void drawGrass(Graphics g) {
        // I was gonna do something interesting here, but im actually just gonna fill all empty space with this shit lol
        g.setColor(grassColor);
        g.fillRect(-2, -2, 2000, 2000);
    }
}

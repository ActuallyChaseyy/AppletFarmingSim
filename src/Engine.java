import java.applet.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("ALL")
public class Engine extends Thread implements KeyListener, ActionListener {

    private Graphics screen;
    private Graphics buffer;
    private Image offscreen;
    private int fps;
    private int width, height;

    public boolean up,down,left,right;
    private boolean debug;
    private int timecount = 0;
    private boolean isDay = true;
    Scene Scene = new Scene();
    doDaylightCycle daylight = new doDaylightCycle(120000,60000);
    Player player = new Player(100,100);
    Soil soil = new Soil(1,1,1,1);

    int amountOfWheat = 4;

    Wheat wheatStack[] = new Wheat[amountOfWheat];


    public Engine(Applet a, int f) {
        fps = f;
        screen = a.getGraphics();
        width = a.getWidth();
        height = a.getHeight();
        offscreen = a.createImage(width, height);
        buffer = offscreen.getGraphics();
        a.addKeyListener(this);
        a.requestFocus();

        debug = false;
    }

    public Engine(Applet a, int f, boolean debugToggle) {
        fps = f;
        screen = a.getGraphics();
        width = a.getWidth();
        height = a.getHeight();
        offscreen = a.createImage(width, height);
        buffer = offscreen.getGraphics();
        a.addKeyListener(this);
        a.requestFocus();

        debug = debugToggle;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a': left = true; break;
            case 'A': left = true; break;
            case 'd': right = true; break;
            case 'D': right = true; break;
            case 'w': up = true; break;
            case 'W': up = true; break;
            case 's': down = true; break;
            case 'S': down = true; break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'a': left = false; break;
            case 'A': left = false; break;
            case 'd': right = false; break;
            case 'D': right = false; break;
            case 'w': up = false; break;
            case 'W': up = false; break;
            case 's': down = false; break;
            case 'S': down = false; break;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public String getDirectionBool(String facing) {
        if (facing.toLowerCase() == "right") {
            if (right) {
                return "True";
            }
            if (!(right)) {
                return "False";
            }
        }

        if (facing.toLowerCase() == "left") {
            if (left) {
                return "True";
            }

            if (!(left)) {
                return "False";
            }
        }

        if (facing.toLowerCase() == "down") {
            if (down) {
                return "True";
            }
            if (!(down)) {
                return "False";
            }
        }

        if (facing.toLowerCase() == "up") {
            if (up) {
                return "True";
            }
            if (!(up)) {
                return "False";
            }
        }

        return "null";
    }


    public void run() {
        while (true) {

            int xPosition = player.xPos;
            int yPosition = player.yPos;
            int playerHeight = player.playerHeight;
            int playerWidth = player.playerWidth;

            daylight.doDC(buffer, width, height);

            soil.placeSoil(buffer, 1000, 200,600,300);

             for (int z = 0; z < amountOfWheat; z++) {
                wheatStack[z] = new Wheat(buffer, 625 + (175 * z), 325, 150, 150);
            }

            player.Move(up,down,left,right,height,width);

            /*
            if(up) {
                player.MoveUp();
            }
            if (down) {
                player.MoveDown();
            }
            if (left) {
                player.MoveLeft();
            }
            if (right) {
                player.MoveRight();
            }
            */

            player.drawPlayer(buffer);

            // All Colored Objects Above Here

            buffer.setColor(new Color(0, 0, 0));
            if (debug) {
                buffer.drawString("up Boolean: " + getDirectionBool("up"), 100, 90);
                buffer.drawString("down boolean: " + getDirectionBool("down"), 100, 110);
                buffer.drawString("left boolean: " + getDirectionBool("left"), 100, 130);
                buffer.drawString("right boolean: " + getDirectionBool("right"), 100, 150);

                buffer.drawString("xPos: " + player.GetXPos(), 100, 180);
                buffer.drawString("yPos: " + player.GetYPos(), 100, 200);

                buffer.drawString("Screen size: " + width + "," + height, 100, 230);

                buffer.drawString("Time: " + System.currentTimeMillis(), 100, 250);

                buffer.drawString("Is Day Bool: " + daylight.getIsDay(), 100, 280);

                buffer.drawString("Next Night In: " + daylight.nextNightIn, 100, 300);
                buffer.drawString("Next Day In: " + daylight.nextDayIn, 100, 320);

                buffer.drawString("Wheat 1 Position X: " + wheatStack[1].getXPosition(), 100, 340);

                buffer.drawString("Is touching wheat: " + wheatStack[1].playerTouching(xPosition, yPosition, playerHeight, playerWidth), 100, 360);
            }

            // Nothing under this comment //

            screen.drawImage(offscreen, 0, 0, null);

            try {
                 Thread.sleep(1000/fps);
            }
            catch (InterruptedException e ) {
                System.err.println("Thread Sleep Err: " + e);
            }

            buffer.clearRect(0,0,width,height);
        }
    }
}
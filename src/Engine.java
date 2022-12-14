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
    doDaylightCycle daylight = new doDaylightCycle(120000,60000);
    Player player = new Player(100,100);
    Soil soil = new Soil(1,1,1,1);

    private int amountOfWheat = 6;
    private int amountOfSoil = 6;

    Wheat wheatStack[] = new Wheat[amountOfWheat];
    Soil soilStack[] = new Soil[amountOfSoil];

    Water waterBlock = new Water(buffer, 75,300,150,150);

    Seed seedBlock = new Seed(buffer, 75,475,150,150);

    Harvest harvestBlock = new Harvest(buffer, 75,650,150,150);
    Level Level = new Level();

    private Boolean hasInitWheat = false;
    private Boolean hasInitSoil = false;
    Font scoreFont;


    public Engine(Applet a, int f) {
        fps = f;
        screen = a.getGraphics();
        width = a.getWidth();
        height = a.getHeight();
        offscreen = a.createImage(width, height);
        buffer = offscreen.getGraphics();
        a.addKeyListener(this);

        debug = false;

        scoreFont = new Font("Fuzzy Bubbles", Font.BOLD, 60);
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

            // Draw level / scene first, overlay everything on top
            Level.drawGrass(buffer);
            Level.drawPath(buffer);

            // Run all checks
            if (!hasInitSoil){
                for (int i = 0; i < amountOfSoil; i++) {
                    soilStack[i] = new Soil(200,200,600 + (200 * i),100);
                }
                hasInitSoil = true;
            }

            for (int i = 0; i < amountOfSoil; i++) {
                soilStack[i].placeSoil(buffer);
            }

            if (!hasInitWheat) {
                for (int i = 0; i < amountOfWheat; i++) {
                    wheatStack[i] = new Wheat(buffer, 625 + (175 * i), 125, 150, 150, 625 + (175 * i), 125);
                }
                hasInitWheat = true;
            }

            for (int z = 0; z < amountOfWheat; z++) {
                wheatStack[z].draw(buffer);
            }

            for (int i = 0; i < amountOfWheat; i++) {
                if (wheatStack[i].playerTouching(xPosition, yPosition, playerHeight, playerWidth)) {
                    wheatStack[i].setPosition(-2000, -1000);
                }
            }

            waterBlock.playerIsTouching(xPosition, yPosition, playerHeight, playerWidth);
            seedBlock.playerIsTouching(xPosition, yPosition, playerHeight, playerWidth);
            harvestBlock.playerIsTouching(xPosition, yPosition, playerHeight, playerWidth);

            // draw water
            waterBlock.draw(buffer);
            seedBlock.draw(buffer);
            harvestBlock.draw(buffer);

            player.Move(up,down,left,right,height,width);

            player.drawPlayer(buffer);

            // Score overlays everything :)
            buffer.setFont(scoreFont);
            buffer.setColor(Color.ORANGE);
            buffer.drawString("DOLLAS: $$" + Score.getValue(), 1400, 900);

            // Coming soon to a theatre near you: Soil (2) (the sequel) (electric boogaloo)
            buffer.setColor(Color.RED);
            buffer.drawString("COMING SOON: SOIL, PART 2", 550 + 250 /* Im lazy */, 650);


            // All Colored Objects Above Here

            buffer.setColor(new Color(0, 0, 0));
            if (debug) {
                /* buffer.drawString("up Boolean: " + getDirectionBool("up"), 100, 90);
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

                 */

                buffer.drawString("Wheat 1 Position X: " + wheatStack[0].getXPosition(), 100, 340);

                buffer.drawString("Is touching wheat: " + wheatStack[1].playerTouching(xPosition, yPosition, playerHeight, playerWidth), 100, 360);

                buffer.drawString("Score: " + Score.getValue(), 100, 380);

                buffer.drawString("Wheat 1 dieTimer: " + wheatStack[0].dieTimer, 100, 400);

                buffer.drawString("Wheat 1 respawn timer: " + wheatStack[0].respawnTimer, 100, 420);

                buffer.drawString("Is touching water: " + waterBlock.playerIsTouching(xPosition, yPosition, playerHeight, playerWidth), 100, 440);

                buffer.drawString("Player farming mode: " + player.getFarmingMode(), 100, 460);

                buffer.drawString("System time now: " + System.currentTimeMillis(), 100, 500);
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
import java.awt.*;

@SuppressWarnings("ALL")
public class Player {
    public int xPos, yPos;
    protected int playerHeight;
    protected int playerWidth;

    private static int mode = 0;

    public Player(int _playerHeight, int _playerWidth) {

        playerHeight = _playerHeight;
        playerWidth = _playerWidth;

        xPos = 0;
        yPos = 0;

    }

    public void Move(Boolean up, Boolean down, Boolean left, Boolean right, int screenHeight, int screenWidth) {

        if (up) {
            yPos -= 10;
        }
        if (down) {
            yPos += 10;
        }
        if (left) {
            xPos -= 10;
        }
        if (right) {
            xPos += 10;
        }

        if (xPos < 0) { xPos = 0; }
        if (yPos < 0) { yPos = 0; }

        if (xPos > screenWidth - playerWidth) { xPos = screenWidth - playerWidth; }
        if (yPos > screenHeight - playerHeight) { yPos = screenHeight - playerHeight; }
    }

    public void Move(int x, int y) {
        xPos = x;
        yPos = y;
    }

    /* public void MoveLeft() {
        xPos -= 1;
    }

    public void MoveUp() {
        yPos -= 1;
    }

    public void MoveDown() {
        yPos += 1;
    }

    public void MoveRight() {
        xPos += 1;
    } */

    // Get and Set x-y position
    public int GetXPos() { return xPos; }
    public void SetXPos(int _new) { xPos = _new; }
    public int GetYPos() { return yPos; }
    public void SetYPos(int _new) { yPos = _new; }

    public void drawPlayer(Graphics g) {

        // Making a circle sprite w a gun

        g.setColor(Color.BLUE);
        g.fillOval(xPos, yPos, playerWidth, playerHeight);
    }

    public static int setFarmingMode(int _mode) {

        // Modes {
        // 0 = Harvesting
        // 1 = Planting
        // 2 = Watering
        // }

        return mode = _mode;
    }

    public static int getFarmingMode() {
        return mode;
    }
}
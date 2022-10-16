import java.awt.*;

@SuppressWarnings("all")
public class Wheat extends Plant{
    private int xPos, yPos, w, h;
    public static boolean isTouch = false;
    private Color wheatColor, stemColor, seedColor, sproutColor, smallWheatColor, leafColor, leaf2Color;

    public int c1, c2, c3, s1, s2, s3, seedC1, seedC2, seedC3, sproutC1, sproutC2, sproutC3,
            smallWheatC1, smallWheatC2, smallWheatC3, leafC1, leafC2, leafC3, leaf2C1, leaf2C2, leaf2C3;

    private boolean isDead = false;

    private int oldX, oldY;

    public long respawnTimer;

    private long time;

    private long maxAge;
    public long dieTimer;

    private long ageTimer;
    private int hitBoxX, hitBoxY, hitBoxWidth, hitBoxHeight;
    public int stage;

    protected long age1, age2, age3, age4, waterableIn;

    private int lvl;
    public Wheat(Graphics g, int _xPos, int _yPos, int Width, int Height, int _hitBoxX, int _hitBoxY) {
        xPos = _xPos;
        yPos = _yPos;
        oldX = xPos;
        oldY = yPos;
        w = Width;
        h = Height;
        hitBoxX = _hitBoxX;
        hitBoxY = _hitBoxY;
        hitBoxWidth = hitBoxX + w;
        hitBoxHeight = hitBoxY + h;

        /**
         * Each individual color, made up of R, G and B values, are stored in variables.
         * These variables are overrideable through their respective changeColor() methods.
         *
         * This is super cluttery, I'm aware, however, it's necessary (i think) to have control over the
         * colors. There is probably a cleaner way of doing this but I don't care :)
         */

        c1 = 255;
        c2 = 186;
        c3 = 10;

        s1 = 154;
        s2 = 123;
        s3 = 79;

        seedC1 = 0;
        seedC2 = 255;
        seedC3 = 0;

        sproutC1 = 126;
        sproutC2 = 182;
        sproutC3 = 29;

        smallWheatC1 = 94;
        smallWheatC2 = 131;
        smallWheatC3 = 23;

        leafC1 = 164;
        leafC2 = 229;
        leafC3 = 38;

        leaf2C1 = 125;
        leaf2C2 = 162;
        leaf2C3 = 40;

        wheatColor = new Color(c1,c2,c3);
        stemColor = new Color(s1,s2,s3);
        seedColor = new Color(seedC1,seedC2,seedC3);
        sproutColor = new Color(sproutC1, sproutC2, sproutC3);
        smallWheatColor = new Color(smallWheatC1, smallWheatC2, smallWheatC3);
        leafColor = new Color(leafC1, leafC2, leafC3);
        leaf2Color = new Color(leaf2C1, leaf2C2, leaf2C3);

        /*
         * The maxAge is the amount of time in milliseconds that the plant will live for.
         * The dieTimer is just a timer for when the plant will die.
         * The ageTimer is a timer for when the plant will age.
         * simple stuff.
         */

        maxAge = genRand(60001, 100001);
        dieTimer = System.currentTimeMillis() + maxAge;

        age1 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.25));
        age2 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.5));
        age3 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.75));
        age4 = System.currentTimeMillis() + (maxAge * 1);

        stage = 1;
    }

    public void draw(Graphics g) {
        /**
         * draw() Method is called every frame.
         *
         * first thing, do the checks on the object. Don't draw if dead :+1:
         */

        this.checkRespawn();
        this.age();

        /**
         * I had to suffer through writing all this code to make the wheat look like wheat.
         * And it still doesnt look like wheat.
         *
         * I'm not even going to try to explain this, it's just a bunch of if statements.
         */

        /// Draw fully grown wheat
        /// TODO: Check for stage of growth

        // Seedling Wheat
        if (stage == 0) {
            g.setColor(seedColor);
            g.fillOval(xPos-2000, yPos, w, h);
        }
        if (stage == 1) {
            g.setColor(seedColor);

            // First row of 6

            g.fillOval(xPos + (w / 8), yPos, 10, 10);
            g.fillOval(xPos + 3 * (w / 8) + 14, yPos -2, 10, 10);
            g.fillOval(xPos + 5 * (w / 8) + 7, yPos + 4, 10, 10);

            // Second row of 6

            g.fillOval(xPos + (w / 8) + 10, yPos + 20, 10, 10);
            g.fillOval(xPos + 3 * (w / 8) - 5, yPos + 24, 10, 10);
            g.fillOval(xPos + 5 * (w / 8), yPos + 28, 10, 10);

            // Third row of 6

            g.fillOval(xPos + (w / 8), yPos + 40, 10, 10);
            g.fillOval(xPos + 3 * (w / 8) - 11, yPos + 41, 10, 10);
            g.fillOval(xPos + 5 * (w / 8) + 8, yPos + 46, 10, 10);

            // Fourth row of 6

            g.fillOval(xPos + (w / 8) - 11, yPos + 60, 10, 10);
            g.fillOval(xPos + 3 * (w / 8) - 3, yPos + 58, 10, 10);
            g.fillOval(xPos + 5 * (w / 8) + 11, yPos + 63, 10, 10);

            // Fifth row of 6

            g.fillOval(xPos + (w / 8), yPos + 80, 10, 10);
            g.fillOval(xPos + 3 * (w / 8) - 13, yPos + 84, 10, 10);
            g.fillOval(xPos + 5 * (w / 8) + 8, yPos + 91, 10, 10);

            // Sixth row of 6

            g.fillOval(xPos + (w / 8), yPos + 100, 10, 10);
            g.fillOval(xPos + 3 * (w / 8) - 2, yPos + 104, 10, 10);
            g.fillOval(xPos + 5 * (w / 8), yPos + 108, 10, 10);

        }

        // Sprout Wheat ( 2 )

        else if (stage == 2) {
            g.setColor(sproutColor);

            // First Row

            g.fillRect(xPos + (w / 4) + (5), yPos + 25, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) + (5), yPos + 22, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + (5), yPos + 32, 5, 25);

            // Second Row

            g.fillRect(xPos + (w / 4) - 20 + (5), yPos + (h / 3) + 31, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) - 10 + (5), yPos + (h / 3) + 40, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + (5), yPos + (h / 3) + 30, 5, 25);

            // Third Row

            g.fillRect(xPos + (w / 4) - 10 + (5), yPos + 2 * (h / 3) + 35, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) + (5), yPos + 2 * (h / 3) + 30, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + 10 + (5), yPos + 2 * (h / 3) + 40, 5, 25);

        }

        // Small Wheat :) ( 3 )

        else if (stage == 3) {
            g.setColor(smallWheatColor);

            // First Row

            g.fillRect(xPos + (w / 4) + (5), yPos + 25, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) + (5), yPos + 22, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + (5), yPos + 32, 5, 25);

            // Second Row

            g.fillRect(xPos + (w / 4) - 20 + (5), yPos + (h / 3) + 31, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) - 10 + (5), yPos + (h / 3) + 40, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + (5), yPos + (h / 3) + 30, 5, 25);

            // Third Row

            g.fillRect(xPos + (w / 4) - 10 + (5), yPos + 2 * (h / 3) + 35, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) + (5), yPos + 2 * (h / 3) + 30, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + 10 + (5), yPos + 2 * (h / 3) + 40, 5, 25);

            // Leaves

            g.setColor(leafColor);

            // First Set

            g.fillOval(xPos + (w / 4) - 20, yPos + 15, 27, 15);
            g.fillOval(xPos + 2 * (w / 4) - 17, yPos + 10, 27, 15);
            g.fillOval(xPos + 3 * (w / 4) - 20, yPos + 25, 27, 15);

            g.fillOval(xPos + (w / 4) - 35, yPos + (h / 3) + 21, 27, 15);
            g.fillOval(xPos + 2 * (w / 4) - 27, yPos + (h / 3) + 30, 27, 15);
            g.fillOval(xPos + 3 * (w / 4) - 15, yPos + (h / 3) + 20, 27, 15);

            g.fillOval(xPos + (w / 4) - 25, yPos + 2 * (h / 3) + 30, 27, 15);
            g.fillOval(xPos + 2 * (w / 4) - 17, yPos + 2 * (h / 3) + 25, 27, 15);
            g.fillOval(xPos + 3 * (w / 4) - 7, yPos + 2 * (h / 3) + 35, 27, 15);

            // Second Set

            g.setColor(leaf2Color);

            g.fillOval(xPos + (w / 4) + 7, yPos + 35, 17, 10);
            g.fillOval(xPos + 2 * (w / 4) + 10, yPos + 30, 17, 10);
            g.fillOval(xPos + 3 * (w / 4) + 7, yPos + 45, 17, 10);

            g.fillOval(xPos + (w / 4) - 15, yPos + (h / 3) + 41, 17, 10);
            g.fillOval(xPos + 2 * (w / 4) - 7, yPos + (h / 3) + 50, 17, 10);
            g.fillOval(xPos + 3 * (w / 4) + 5, yPos + (h / 3) + 40, 17, 10);

            g.fillOval(xPos + (w / 4) - 5, yPos + 2 * (h / 3) + 50, 17, 10);
            g.fillOval(xPos + 2 * (w / 4) + 3, yPos + 2 * (h / 3) + 45, 17, 10);
            g.fillOval(xPos + 3 * (w / 4) + 13, yPos + 2 * (h / 3) + 55, 17, 10);

        }

        // Full Grown
        else if  (stage == 4) {
            g.setColor(wheatColor);

            // First Row

            g.fillOval(xPos + (w / 4), yPos, 15, 25);
            g.fillOval(xPos + 2 * (w / 4), yPos - 3, 15, 25);
            g.fillOval(xPos + 3 * (w / 4), yPos + 7, 15, 25);

            // Second Row

            g.fillOval(xPos + (w / 4) - 20, yPos + (h / 3) + 6, 15, 25);
            g.fillOval(xPos + 2 * (w / 4) - 10, yPos + (h / 3) + 15, 15, 25);
            g.fillOval(xPos + 3 * (w / 4), yPos + (h / 3) + 5, 15, 25);

            // Third Row

            g.fillOval(xPos + (w / 4) - 10, yPos + 2 * (h / 3) + 10, 15, 25);
            g.fillOval(xPos + 2 * (w / 4), yPos + 2 * (h / 3) + 5, 15, 25);
            g.fillOval(xPos + 3 * (w / 4) + 10, yPos + 2 * (h / 3) + 15, 15, 25);

            g.setColor(stemColor);

            // First Row

            g.fillRect(xPos + (w / 4) + (5), yPos + 25, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) + (5), yPos + 22, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + (5), yPos + 32, 5, 25);

            // Second Row

            g.fillRect(xPos + (w / 4) - 20 + (5), yPos + (h / 3) + 31, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) - 10 + (5), yPos + (h / 3) + 40, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + (5), yPos + (h / 3) + 30, 5, 25);

            // Third Row

            g.fillRect(xPos + (w / 4) - 10 + (5), yPos + 2 * (h / 3) + 35, 5, 25);
            g.fillRect(xPos + 2 * (w / 4) + (5), yPos + 2 * (h / 3) + 30, 5, 25);
            g.fillRect(xPos + 3 * (w / 4) + 10 + (5), yPos + 2 * (h / 3) + 40, 5, 25);
        }
    }

    /* public boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (Player.getFarmingMode() == 0) {
            if (playerX + playerWidth > this.xPos && playerX < this.xPos + 150 && playerY + playerHeight > this.yPos && playerY < this.yPos + 150) {

                Score.addScore(1);

                respawnTimer = System.currentTimeMillis() + 500000;
                isDead = true;
                return isTouch = true;
            }
        }
        return isTouch = false;
    } */

    public boolean playerTouching(int playerX, int playerY, int playerWidth, int playerHeight) {
        /**
         * self explanatory name :)
         *
         * Just checks if the player is touching the wheat through a couple of if else loops.
         * also checks for the hitbox of the wheat, which is different to the drawn object (it makes sense in
         * practice)
         */
        if (Player.getFarmingMode() == 0) {
            if (stage == 4) {
                if (!isDead && playerX + playerWidth > this.hitBoxX && playerX < this.hitBoxWidth && playerY + playerHeight > this.hitBoxY && playerY < this.hitBoxHeight) {

                    Score.addScore(1);

                    respawnTimer = System.currentTimeMillis() + 500000;
                    isDead = true;

                    stage = 0;

                    return isTouch = true;
                }
            }
            else {
                return isTouch = false;
            }
        }

        if (Player.getFarmingMode() == 1) {
            if (isDead && playerX + playerWidth > this.hitBoxX && playerX < this.hitBoxWidth && playerY + playerHeight > this.hitBoxY && playerY < this.hitBoxHeight) {
                // Respawn wheat, don't add score
                stage = 1;
                this.forceRespawn();
                return isTouch = true;
            }
        }

        if (Player.getFarmingMode() == 2) {
            // Water plant. Force stage + 1, don't add score, add 3 second timer to waterableIn
            if (!(isDead) && playerX + playerWidth > this.hitBoxX && playerX < this.hitBoxWidth && playerY + playerHeight > this.hitBoxY && playerY < this.hitBoxHeight) {
                if ( stage >= 4) {
                    return isTouch = false;
                }
                else {
                    if (waterableIn < System.currentTimeMillis()) {

                        switch (stage) {
                            case 1: age1 = 0;
                            case 2: age2 = 0;
                            case 3: age3 = 0;
                        }

                        isDead = false;
                        waterableIn = System.currentTimeMillis() + 3000;
                        return isTouch = false;
                    }
                    else {
                        return isTouch = false;
                    }
                }
            }
        }

        return isTouch = false;
    }


    public int getXPosition() {
        return xPos;
    }

    public void changeWheatColor(int _r, int _g, int _b) {
        c1 = _r;
        c2 = _g;
        c3 = _b;

        wheatColor = new Color(_r,_g,_b);
    }

    public void setPosition(int _xPos, int _yPos) {
        xPos = _xPos;
        yPos = _yPos;
    }

    public void checkRespawn() {
        if (System.currentTimeMillis() >= respawnTimer && isDead) {
            this.setPosition(oldX, oldY);
            isDead = false;

            maxAge = genRand(15001, 30001);
            dieTimer = System.currentTimeMillis() + maxAge;

            age1 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.25));
            age2 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.5));
            age3 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.75));
            age4 = System.currentTimeMillis() + (maxAge * 1);

            ageTimer = System.currentTimeMillis() + 1000;
        }
    }

    public void forceRespawn() {
        respawnTimer = 0;
    }

    private void age() {

        // Age 0: Wheat is dead?
        // Age 1: Wheat is seed
        // Age 2: Wheat is sprout
        // Age 3: Wheat is small
        // Age 4: Wheat is grown
        if (!(stage == 0)) {


            if (System.currentTimeMillis() <= age1) {
                stage = 1;
            } else if (System.currentTimeMillis() <= age2 && System.currentTimeMillis() > age1) {
                stage = 2;
            } else if (System.currentTimeMillis() <= age3 && System.currentTimeMillis() > age2) {
                stage = 3;
            } else if (System.currentTimeMillis() <= age4 && System.currentTimeMillis() > age3) {
                stage = 4;
            } else if (System.currentTimeMillis() > age4) {
                stage = 0;
                isDead = true;
                respawnTimer = System.currentTimeMillis() + 500000;

                age1 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.25));
                age2 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.5));
                age3 = (long) Math.floor(System.currentTimeMillis() + (maxAge * 0.75));
                age4 = System.currentTimeMillis() + (maxAge * 1);
            }

            if (System.currentTimeMillis() >= dieTimer && !isDead) {
                isDead = true;
                this.setPosition(-2000, -2000);

                respawnTimer = System.currentTimeMillis() + 500000;
            }
        }
    }

    public long genRand(long min, long max) {
        return (long) (Math.random() * (max - min + 1) + min);
    }

    public int genRand(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}

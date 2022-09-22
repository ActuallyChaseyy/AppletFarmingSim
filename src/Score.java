/**
 * Static class to keep track of score. Don't want to keep it in the player object :)
 */

@SuppressWarnings("unused")
public class Score {
    private static int value;
    private static int highScore;

    public static void addScore(int score) {
        value += score;
    }

    public static void minusValue(int score) {
        value -= score;
    }

    public static int getValue() {
        return value;
    }

    public static void reset() {
        value = 0;
    }

    public static void setValue(int num) {
        value = num;
    }

    public static void setHighScore(int num) {
        highScore = num;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void checkHighScore() {
        if (value > highScore) {
            highScore = value;
        }
    }

    public static void resetHighScore() {
        highScore = 0;
    }

}

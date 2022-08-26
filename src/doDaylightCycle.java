import java.awt.*;


public class doDaylightCycle {

    private long currentTime;
    private long dayLength;
    private long nightLength;
    private Color skyColor;

    private boolean isDay = true;

    public long nextDayIn;
    public long nextNightIn;

    private int co = 255;
    public doDaylightCycle(long day, long night) {
        currentTime = System.currentTimeMillis();
        dayLength = day;
        nightLength = night;
        skyColor = new Color(co,co,co);
    }

    public long nextNight() {

        long _nextNight;

        return _nextNight = currentTime += dayLength;
    }

    public long nextDay() {

        long _nextDay;
        return _nextDay = currentTime += nightLength;
    }

    public boolean getIsDay(){ return isDay; }

    public void doDC(Graphics g, int ScreenWidth, int ScreenHeight) {
        currentTime = System.currentTimeMillis();

        g.setColor(skyColor);
        g.fillRect(0,0, ScreenWidth, ScreenHeight);

        if (currentTime >= nextNightIn) {
            isDay = false;
            nextNightIn = currentTime + dayLength;

            for (int i = 255; i >= 50; i-=5) {
                co -= i;
            }

        }
        if (currentTime >= nextDayIn) {
            isDay = true;
            nextDayIn = currentTime + nightLength + dayLength;
        }
    }

}

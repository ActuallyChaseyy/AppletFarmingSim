import java.applet.*;

public class App extends Applet {
    public void init() {
        this.requestFocus();
        Engine engine = new Engine(this, 60);
        engine.start();
    }
}

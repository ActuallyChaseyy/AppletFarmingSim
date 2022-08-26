public class Plant {

    private int stage;
    private boolean isDead = false;

    public void grow() {
        this.stage += 1;
    }

    public void checkDead() {
        if (stage == 5) {
            isDead = true;
        }
    }

}
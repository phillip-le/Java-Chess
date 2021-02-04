public class Player {
    private boolean white;

    public Player(boolean white) {
        this.white = white;
    }

    /**
     * Gets whether or not the player is playing as white.
     * @return true if the player is playing as white, else false.
     */
    public boolean isWhite() {
        return this.white;
    }
}

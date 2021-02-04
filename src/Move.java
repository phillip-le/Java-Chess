public class Move {
    // The starting and ending positions of the move
    private final Position src;
    private final Position dst;

    // The player who made the move
    private final Player player;

    public Move(Player player, Position src, Position dst) {
        this.player = player;
        this.src = src;
        this.dst = dst;
    }

    /**
     * Gets the player who made the move.
     * @return the player who made the move.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the starting position of the move.
     * @return the starting position of the move.
     */
    public Position getSrc() {
        return this.src;
    }

    /**
     * Gets the ending position of the move.
     * @return the ending position of the move.
     */
    public Position getDst() {
        return this.dst;
    }
}

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

    public Player getPlayer() {
        return this.player;
    }

    public Position getSrc() {
        return this.src;
    }

    public Position getDst() {
        return this.dst;
    }
}

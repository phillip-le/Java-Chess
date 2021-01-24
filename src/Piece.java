public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return this.white;
    }

    public boolean getKilled() {
        return this.killed;
    }

    public void setKilled() {
        this.killed = true;
    }

    public abstract boolean canMove(Board board, Position src, Position dst);

    public abstract String toString();
}

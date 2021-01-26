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

    /**
     * Checks if the move is invalid for reasons that are common to all Pieces.
     * @param board which stores the locations of pieces
     * @param src - starting position of the piece
     * @param dst - ending position of the piece
     * @return true if it passes basic checks, else false
     */
    protected boolean validPieceMove(Board board, Position src, Position dst) {
        // Can't move a dead piece
        if (this.getKilled()) {
            return false;
        }
        // Src position does not match Piece's position
        if (board.getPiece(src) == null) {
            return false;
        }
        // Trying to team kill
        if (board.getPiece(dst) != null && board.getPiece(dst).isWhite() == board.getPiece(src).isWhite()) {
            return false;
        }
        // Trying to move in-place
        if (src.getRow() == dst.getRow() && src.getCol() == dst.getCol()) {
            return false;
        }
        return true;
    }
}

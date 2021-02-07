import javafx.scene.layout.StackPane;

public abstract class Piece extends StackPane {
    private boolean killed;
    private boolean white;

    public Piece(boolean white) {
        this.killed = false;
        this.white = white;
    }

    /**
     * Gets whether or not the piece is a white piece.
     * @return true if white, else false.
     */
    public boolean isWhite() {
        return this.white;
    }

    /**
     * Gets whether or not the piece has been killed.
     * @return the killed status of the piece.
     */
    public boolean getKilled() {
        return this.killed;
    }

    /**
     * Kills the piece.
     */
    public void setKilled() {
        this.killed = true;
    }

    /**
     * Checks if the piece can move from the src position to the dst position.
     * @param board - the board of the game.
     * @param src - the starting position of the piece.
     * @param dst - the ending position of the piece.
     * @return true if the piece can move to dst, else false.
     */
    public abstract boolean canMove(Board board, Position src, Position dst);

    /**
     * Gets string representing the piece.
     * @return the string representing the piece.
     */
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

    /**
     * Checks if the path between the src and dst are clear of obstacle pieces
     * @param board which stores the location of pieces
     * @param src - starting position of the piece
     * @param dst - ending position of the piece
     * @param rowInc - direction to increment for rows
     * @param colInc - direction to increments for columns
     * @return true if the path is clear between the src and dst, else false
     */
    protected boolean isPathClear(Board board, Position src, Position dst, int rowInc, int colInc) {
        try {
            Position testPosition = new Position(src.getRow() + rowInc, (char) ((int) src.getCol() + colInc));
            // Check that the path is clear between the src and dst
            while (!(testPosition.getRow() == dst.getRow() && testPosition.getCol() == dst.getCol())) {
                if (board.getPiece(testPosition) != null) {
                    return false;
                }
                testPosition = new Position(testPosition.getRow() + rowInc, (char) ((int) testPosition.getCol() + colInc));
            }
        } catch (InvalidPositionException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Gets the difference in rows between the starting and ending positions.
     * @param src - the starting position.
     * @param dst - the ending position.
     * @return the difference in rows between the starting and ending positions.
     */
    protected int getRowDiff(Position src, Position dst) {
        return Math.abs(src.getRow() - dst.getRow());
    }

    /**
     * Gets the difference in columns between the starting and ending positions.
     * @param src - the starting position.
     * @param dst - the ending position.
     * @return the difference in columns between the starting and ending positions.
     */
    protected int getColDiff(Position src, Position dst) {
        return Math.abs((int) src.getCol() - (int) dst.getCol());
    }
}

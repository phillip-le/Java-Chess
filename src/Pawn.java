public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (super.getKilled()) {
            return false;
        }
        // Trying src position does not match Pawn
        if (board.getPiece(src) == null) {
            return false;
        }
        // Trying to team kill
        if (board.getPiece(dst) != null && board.getPiece(dst).isWhite() == board.getPiece(src).isWhite()) {
            return false;
        }
        // If diagonal is empty or moving too far horizontally
        if (src.getCol() != dst.getCol() && (board.getPiece(dst) == null ||
                Math.abs((int) src.getCol() - (int) dst.getCol()) > 1)) {
            return false;
        }
        // Moving vertically too many spaces at once
        if (Math.abs(src.getRow() - dst.getRow()) > 2) {
            return false;
        }
        if (super.isWhite()) {
            // Piece is trying to move backwards
            if (src.getRow() > dst.getRow()) {
                return false;
            }
            // Moving vertically too many spaces at once or moving two spaces and diagonally
            if (dst.getRow() - src.getRow() > 1 && (src.getRow() != 2 || src.getCol() != dst.getCol())) {
                return false;
            }
        } else { // Piece is Black
            // Piece is trying to move backwards
            if (dst.getRow() > src.getRow()) {
                return false;
            }
            // Moving vertically too many spaces at once or moving two spaces and diagonally
            if (src.getRow() - dst.getRow() > 1 && (src.getRow() != 7 || src.getCol() != dst.getCol())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "p";
    }
}

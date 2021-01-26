public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (!validPieceMove(board, src, dst)) {
            return false;
        }
        // True if the dst is diagonal to the src, else false
        if (getRowDiff(src, dst) != getColDiff(src, dst)) {
            return false;
        }

        // The increment to add to the row
        int rowInc = dst.getRow() > src.getRow() ? 1 : -1;
        // The increment to add to the col
        int colInc = dst.getCol() > src.getCol() ? 1 : -1;

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

    @Override
    public String toString() {
        return "b";
    }
}

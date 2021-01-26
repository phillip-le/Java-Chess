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

        return isPathClear(board, src, dst, rowInc, colInc);
    }

    @Override
    public String toString() {
        return "b";
    }
}

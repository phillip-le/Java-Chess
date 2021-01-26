public class Castle extends Piece {

    public Castle(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (!validPieceMove(board, src, dst)) {
            return false;
        }
        // Checks if only the rows are different or if only the cols are different
        if (!((src.getRow() == dst.getRow() && src.getCol() != dst.getCol()) ||
                (src.getRow() != dst.getRow() && src.getCol() == dst.getCol()))) {
            return false;
        }
        // The increment to add to the row, ensure range between -1 and 1
        int rowInc = Math.min(Math.max(Integer.compare(dst.getRow(), src.getRow()), -1), 1);
        // The increment to add to the col, ensure range between -1 and 1
        int colInc = Math.min(Math.max(Character.compare(dst.getCol(), src.getCol()), -1), 1);

        return isPathClear(board, src, dst, rowInc, colInc);
    }

    @Override
    public String toString() {
        return "c";
    }

}

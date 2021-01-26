public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (!validPieceMove(board, src, dst)) {
            return false;
        }
        int rowDiff = getRowDiff(src, dst);
        int colDiff = getColDiff(src, dst);
        // True if the dst is an L shape away from the src, else false
        return (rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1);
    }

    @Override
    public String toString() {
        return "n";
    }
}

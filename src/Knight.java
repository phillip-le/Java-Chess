public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (!validPieceMove(board, src, dst)) {
            return false;
        }
        int rowDiff = Math.abs(src.getRow() - dst.getRow());
        int colDiff = Math.abs((int) src.getCol() - (int) dst.getCol());
        if (!((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "n";
    }
}

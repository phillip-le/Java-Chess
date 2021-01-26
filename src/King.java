import java.util.List;

public class King extends Piece {

    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (!validPieceMove(board, src, dst)) {
            return false;
        }
        if (getRowDiff(src, dst) > 1 || getColDiff(src, dst) > 1) {
            return false;
        }
        List<Position> opposingTeamPos = board.getTeamPieces(!isWhite());
        for (Position p : opposingTeamPos) {
            if (board.getPiece(p).canMove(board, p, dst)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "k";
    }
}

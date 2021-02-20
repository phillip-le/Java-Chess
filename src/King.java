import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class King extends Piece {

    public King(boolean white) throws FileNotFoundException {
        super(white);
        if (white) {
            image = new Image(new FileInputStream("src/images/King_White.png"));
        } else {
            image = new Image(new FileInputStream("src/images/King_Black.png"));
        }
        addIcon(image);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (invalidPieceMove(board, src, dst)) {
            return false;
        }
        if (getRowDiff(src, dst) > 1 || getColDiff(src, dst) > 1) {
            return false;
        }
        Piece oldDstPiece = testMove(board, src, dst);
        List<Position> opposingTeamPos = board.getTeamPositions(!isWhite());
        for (Position p : opposingTeamPos) {
            if (board.getPiece(p).canMove(board, p, dst)) {
                System.out.println(p.toString() + " can Move to checkmate!");
                undoTestMove(board, src, dst, oldDstPiece);
                return false;
            }
        }
        undoTestMove(board, src, dst, oldDstPiece);
        return true;
    }

    private Piece testMove(Board board, Position src, Position dst) {
        Piece oldDstPiece = board.getPiece(dst);
        if (oldDstPiece != null) {
            board.removePiece(dst);
        }
        board.removePiece(src);
        board.setPiece(dst, this);
        return oldDstPiece;
    }

    private void undoTestMove(Board board, Position src, Position dst, Piece oldDstPiece) {
        board.removePiece(dst);
        board.setPiece(src, this);
        if (oldDstPiece != null) {
            board.setPiece(dst, oldDstPiece);
        }
    }

    @Override
    public String toString() {
        return "k";
    }
}

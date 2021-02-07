import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pawn extends Piece {

    public Pawn(boolean white) throws FileNotFoundException {
        super(white);
        if (white) {
            image = new Image(new FileInputStream("src/images/Pawn_White.png"));
        } else {
            image = new Image(new FileInputStream("src/images/Pawn_Black.png"));
        }
        getChildren().add(new ImageView(image));
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (!validPieceMove(board, src, dst)) {
            return false;
        }
        // If diagonal is empty or moving too far horizontally
        if (src.getCol() != dst.getCol() && (board.getPiece(dst) == null ||
                Math.abs((int) src.getCol() - (int) dst.getCol()) > 1)) {
            return false;
        }
        // Moving vertically too many spaces at once
        if (getRowDiff(src, dst) > 2) {
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

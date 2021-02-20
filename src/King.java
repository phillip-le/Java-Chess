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
        List<Position> opposingTeamPos = board.getTeamPositions(!isWhite());
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

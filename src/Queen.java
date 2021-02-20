import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Queen extends Piece {

    public Queen(boolean white) throws FileNotFoundException {
        super(white);
        if (white) {
            image = new Image(new FileInputStream("src/images/Queen_White.png"));
        } else {
            image = new Image(new FileInputStream("src/images/Queen_Black.png"));
        }
        addIcon(image);
    }

    @Override
    public boolean canMove(Board board, Position src, Position dst) {
        if (invalidPieceMove(board, src, dst)) {
            return false;
        }
        // Checks (if only the rows are different or if only the cols are different) or if src and dst are diagonal
        if (!(((src.getRow() == dst.getRow() && src.getCol() != dst.getCol()) ||
                (src.getRow() != dst.getRow() && src.getCol() == dst.getCol())) ||
                (getRowDiff(src, dst) == getColDiff(src, dst)))) {
            return false;
        }
        // The increment to add to the row, ensure range between -1 and 1
        int rowInc = Integer.compare(dst.getRow(), src.getRow());
        // The increment to add to the col, ensure range between -1 and 1
        int colInc = Math.min(Math.max(Character.compare(dst.getCol(), src.getCol()), -1), 1);

        return isPathClear(board, src, dst, rowInc, colInc);
    }

    @Override
    public String toString() {
        return "q";
    }
}

import javafx.geometry.Pos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Board board;
    private Knight white;
    private Knight black;

    @BeforeEach
    void setUp() throws InvalidPositionException, FileNotFoundException {
        board = new Board();
        board.clearBoard();
        white = new Knight(true);
        black = new Knight(false);
    }

    @AfterEach
    void tearDown() {
        board = null;
        white = null;
        black = null;
    }

    @Test
    void checkValidPositions() throws InvalidPositionException {
        int initRow = 4;
        char initColInt = (int) 'd';
        Position init = new Position(initRow, (char) initColInt);
        List<Position> validPositions = Arrays.asList(
                new Position(initRow + 1, (char) (initColInt - 2)),
                new Position(initRow + 2, (char) (initColInt - 1)),
                new Position(initRow + 2, (char) (initColInt + 1)),
                new Position(initRow + 1, (char) (initColInt + 2)),
                new Position(initRow - 1, (char) (initColInt + 2)),
                new Position(initRow - 2, (char) (initColInt + 1)),
                new Position(initRow - 2, (char) (initColInt - 1)),
                new Position(initRow - 1, (char) (initColInt - 2))
        );
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                Position dst = new Position(initRow + i, (char) (initColInt + j));
                if (validPositions.contains(dst)) {
                    board.setPiece(init, white);
                    assertTrue(white.canMove(board, init, dst));
                    board.setPiece(init, black);
                    assertTrue(black.canMove(board, init, dst));
                } else {
                    board.setPiece(init, white);
                    assertFalse(white.canMove(board, init, dst));
                    board.setPiece(init, black);
                    assertFalse(black.canMove(board, init, dst));
                }
            }
        }
    }
}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private Board board;
    private King white;
    private King black;

    @BeforeEach
    void setUp() throws InvalidPositionException {
        board = new Board();
        white = new King(true);
        black = new King(false);
    }

    @AfterEach
    void tearDown() {
        board = null;
        white = null;
        black = null;
    }

    @Test
    public void canMoveEmpty() throws InvalidPositionException {
        int initRow = 2;
        char initCol = 'c';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, white);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Position testPosition = new Position(initRow + i, (char) ((int) initCol + j));
                if (i == 0 && j == 0) {
                    assertFalse(white.canMove(board, init, testPosition));
                } else {
                    assertTrue(white.canMove(board, init, testPosition));
                }
            }
        }
    }

    @Test
    public void cannotCheckmate() throws InvalidPositionException {
        int initRow = 2;
        char initCol = 'c';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, white);
        Position attackingCastlePos = new Position(8, 'd');
        Castle attackingCastle = new Castle(false);
        board.setPiece(attackingCastlePos, attackingCastle);
        board.getTeamPositions(false).add(attackingCastlePos);
        for (int i = 1; i < 4; i++) {
            Position illegalMove = new Position(i, 'd');
            assertFalse(white.canMove(board, init, illegalMove));
        }
    }
}
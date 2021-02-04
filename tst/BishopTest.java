import javafx.geometry.Pos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    private Board board;
    private Bishop white;
    private Bishop black;

    @BeforeEach
    void setUp() throws InvalidPositionException {
        board = new Board();
        white = new Bishop(true);
        black = new Bishop(false);
    }

    @AfterEach
    void tearDown() {
        board = null;
        white = null;
        black = null;
    }

    @Test
    void basicMoveToEmpty() throws InvalidPositionException {
        int initRow = 4;
        char initCol = 'f';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, white);
        assertTrue(white.canMove(board, init, new Position(initRow + 1, 'g')));
        assertTrue(white.canMove(board, init, new Position(initRow + 2, 'h')));
        assertTrue(white.canMove(board, init, new Position(8, 'b')));
        assertTrue(white.canMove(board, init, new Position(2, 'd')));
        assertFalse(white.canMove(board, init, new Position(4, 'e')));
        assertFalse(white.canMove(board, init, new Position(5, 'd')));
        assertFalse(white.canMove(board, init, new Position(2, 'g')));
    }

    @Test
    void basicMoveToCapture() throws InvalidPositionException {
        int initRow = 4;
        char initCol = 'f';
        Position init = new Position(initRow, initCol);
        Position blackToCapturePos = new Position(6, 'd');
        board.setPiece(init, white);
        board.setPiece(blackToCapturePos, black);

        // Can capture black bishop
        assertTrue(white.canMove(board, init, blackToCapturePos));
        // Can move to empty square
        assertTrue(white.canMove(board, init, new Position(5, 'e')));
        // Blocked by blackToCapture
        assertFalse(white.canMove(board, init, new Position(8, 'b')));

        Bishop whiteToBlock = new Bishop(true);
        Position whiteToBlockPos = new Position(5, 'g');
        board.setPiece(whiteToBlockPos, whiteToBlock);
        assertFalse(white.canMove(board, init, whiteToBlockPos));
        Position blackUntouchedPos = new Position(6, 'h');
        board.setPiece(blackUntouchedPos, black);
        // Init white is being blocked by another white bishop from attacking black
        assertFalse(white.canMove(board, init, blackUntouchedPos));

        // Black can capture whiteToBlock
        assertTrue(black.canMove(board, blackUntouchedPos, whiteToBlockPos));
    }
}
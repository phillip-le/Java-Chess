import javafx.geometry.Pos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CastleTest {

    private Board board;
    private Castle white;
    private Castle black;

    @BeforeEach
    void setUp() throws InvalidPositionException {
        board = new Board();
        white = new Castle(true);
        black = new Castle(false);
    }

    @AfterEach
    void tearDown() {
        board = null;
        white = null;
        black = null;
    }

    @Test
    void basicMoveToEmpty() throws InvalidPositionException {
        int initRow = 3;
        char initCol = 'b';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, black);
        assertTrue(black.canMove(board, init, new Position(3, 'a')));
        assertTrue(black.canMove(board, init, new Position(5, 'b')));
        assertTrue(black.canMove(board, init, new Position(8, 'b')));
        assertTrue(black.canMove(board, init, new Position(3, 'f')));
        assertTrue(black.canMove(board, init, new Position(2, 'b')));
        assertTrue(black.canMove(board, init, new Position(1, 'b')));

        assertFalse(black.canMove(board, init, new Position(1, 'a')));
        assertFalse(black.canMove(board, init, new Position(4, 'a')));
        assertFalse(black.canMove(board, init, new Position(8, 'd')));
        assertFalse(black.canMove(board, init, new Position(5, 'f')));
    }

    @Test
    void basicMoveToCaptureHorizontal() throws InvalidPositionException {
        int initRow = 5;
        char initCol = 'e';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, black);
        Position whiteToCapturePos = new Position(5, 'a');
        board.setPiece(whiteToCapturePos, white);
        assertTrue(black.canMove(board, init, whiteToCapturePos));
        assertTrue(white.canMove(board, whiteToCapturePos, init));

        Castle blackToBlock = new Castle(false);
        Position blackToBlockPos = new Position(5, 'c');
        board.setPiece(blackToBlockPos, blackToBlock);
        // Can move to empty space
        assertTrue(black.canMove(board, init, new Position(5, 'd')));
        // Blocked by blackToBlock piece
        assertFalse(black.canMove(board, init, blackToBlockPos));
        assertFalse(black.canMove(board, init, new Position(5, 'b')));
        assertFalse(black.canMove(board, init, whiteToCapturePos));
    }

    @Test
    void basicMoveToCaptureVertical() throws InvalidPositionException {
        int initRow = 5;
        char initCol = 'e';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, white);
        Position blackToCapturePos = new Position(1, 'e');
        board.setPiece(blackToCapturePos, black);
        assertTrue(white.canMove(board, init, blackToCapturePos));
        assertTrue(black.canMove(board, blackToCapturePos, init));

        Castle whiteToBlock = new Castle(true);
        Position whiteToBlockPos = new Position(4, 'e');
        board.setPiece(whiteToBlockPos, whiteToBlock);
        assertFalse(white.canMove(board, init, whiteToBlockPos));
        assertFalse(whiteToBlock.canMove(board, whiteToBlockPos, init));
        assertFalse(white.canMove(board, init, new Position(3, 'e')));
        assertFalse(white.canMove(board, init, new Position(2, 'e')));
        assertFalse(white.canMove(board, init, blackToCapturePos));
    }
}
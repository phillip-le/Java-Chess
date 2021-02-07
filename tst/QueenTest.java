import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    private Board board;
    private Queen white;
    private Queen black;

    @BeforeEach
    void setUp() throws InvalidPositionException, FileNotFoundException {
        board = new Board();
        white = new Queen(true);
        black = new Queen(false);
    }

    @AfterEach
    void tearDown() {
        board = null;
        white = null;
        black = null;
    }

    @Test
    void basicVerticalMoveToEmpty() throws InvalidPositionException {
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

        assertTrue(black.canMove(board, init, new Position(4, 'a')));
        assertFalse(black.canMove(board, init, new Position(1, 'a')));

        assertFalse(black.canMove(board, init, new Position(8, 'd')));
        assertFalse(black.canMove(board, init, new Position(5, 'f')));
    }

    @Test
    void basicDiagonalMoveToEmpty() throws InvalidPositionException {
        int initRow = 4;
        char initCol = 'f';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, white);
        assertTrue(white.canMove(board, init, new Position(initRow + 1, 'g')));
        assertTrue(white.canMove(board, init, new Position(initRow + 2, 'h')));
        assertTrue(white.canMove(board, init, new Position(8, 'b')));
        assertTrue(white.canMove(board, init, new Position(2, 'd')));

        // Knight moves
        assertFalse(white.canMove(board, init, new Position(5, 'd')));
        assertFalse(white.canMove(board, init, new Position(2, 'g')));
    }

    @Test
    void basicDiagonalMoveToCapture() throws InvalidPositionException, FileNotFoundException {
        int initRow = 4;
        char initCol = 'f';
        Position init = new Position(initRow, initCol);
        Position blackToCapturePos = new Position(6, 'd');
        board.setPiece(init, white);
        board.setPiece(blackToCapturePos, black);

        // Can capture black Queen
        assertTrue(white.canMove(board, init, blackToCapturePos));
        // Can move to empty square
        assertTrue(white.canMove(board, init, new Position(5, 'e')));
        // Blocked by blackToCapture
        assertFalse(white.canMove(board, init, new Position(8, 'b')));

        Queen whiteToBlock = new Queen(true);
        Position whiteToBlockPos = new Position(5, 'g');
        board.setPiece(whiteToBlockPos, whiteToBlock);
        assertFalse(white.canMove(board, init, whiteToBlockPos));
        Position blackUntouchedPos = new Position(6, 'h');
        board.setPiece(blackUntouchedPos, black);
        // Init white is being blocked by another white Queen from attacking black
        assertFalse(white.canMove(board, init, blackUntouchedPos));

        // Black can capture whiteToBlock
        assertTrue(black.canMove(board, blackUntouchedPos, whiteToBlockPos));
    }

    @Test
    void basicHorizontalMoveToCapture() throws InvalidPositionException, FileNotFoundException {
        int initRow = 5;
        char initCol = 'e';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, black);
        Position whiteToCapturePos = new Position(5, 'a');
        board.setPiece(whiteToCapturePos, white);
        assertTrue(black.canMove(board, init, whiteToCapturePos));
        assertTrue(white.canMove(board, whiteToCapturePos, init));

        Queen blackToBlock = new Queen(false);
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
    void basicVerticalMoveToCapture() throws InvalidPositionException, FileNotFoundException {
        int initRow = 5;
        char initCol = 'e';
        Position init = new Position(initRow, initCol);
        board.setPiece(init, white);
        Position blackToCapturePos = new Position(1, 'e');
        board.setPiece(blackToCapturePos, black);
        assertTrue(white.canMove(board, init, blackToCapturePos));
        assertTrue(black.canMove(board, blackToCapturePos, init));

        Queen whiteToBlock = new Queen(true);
        Position whiteToBlockPos = new Position(4, 'e');
        board.setPiece(whiteToBlockPos, whiteToBlock);
        assertFalse(white.canMove(board, init, whiteToBlockPos));
        assertFalse(whiteToBlock.canMove(board, whiteToBlockPos, init));
        assertFalse(white.canMove(board, init, new Position(3, 'e')));
        assertFalse(white.canMove(board, init, new Position(2, 'e')));
        assertFalse(white.canMove(board, init, blackToCapturePos));
    }
}
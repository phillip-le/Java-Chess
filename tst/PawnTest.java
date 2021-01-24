import javafx.geometry.Pos;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    private Board board;
    private Pawn white;
    private Pawn black;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        board = new Board();
        white = new Pawn(true);
        black = new Pawn(false);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        board = null;
        white = null;
        black = null;
    }

    @org.junit.jupiter.api.Test
    void canMoveInitialTwoWhite() throws InvalidPositionException {
        char col = 'b';
        int initRow = 2;
        Position init = new Position(initRow, col);
        Position dstOneAway = new Position(initRow + 1, col);
        Position dstTwoAway = new Position(initRow + 2, col);
        board.setPiece(init, white);
        assertTrue(white.canMove(board, init, dstOneAway));
        assertTrue(white.canMove(board, init, dstTwoAway));
    }

    @org.junit.jupiter.api.Test
    void canMoveInitialTwoBlack() throws InvalidPositionException {
        char col = 'e';
        int initRow = 7;
        Position init = new Position(initRow,col);
        Position dstOneAway = new Position(initRow - 1,col);
        Position dstTwoAway = new Position(initRow - 2, col);
        board.setPiece(init, black);
        assertTrue(black.canMove(board, init, dstOneAway));
        assertTrue(black.canMove(board, init, dstTwoAway));
    }

    @org.junit.jupiter.api.Test
    void canMoveDiagonal() throws InvalidPositionException {
        int whiteRow = 4;
        char whiteCol = 'd';
        Position whitePos = new Position(whiteRow, whiteCol);
        Position blackPos = new Position(whiteRow + 1, (char) ((int) whiteCol + 1));
        board.setPiece(whitePos, white);
        board.setPiece(blackPos, black);
        assertTrue(white.canMove(board, whitePos, blackPos));
        assertTrue(black.canMove(board, blackPos, whitePos));
    }

    @org.junit.jupiter.api.Test
    void canMoveDiagonalEmptyWhite() throws InvalidPositionException {
        int whiteRow = 3;
        char whiteCol = 'e';
        Position whiteSrc = new Position(whiteRow, whiteCol);
        Position whiteLeftDiag = new Position(whiteRow + 1, (char) (whiteCol - 1));
        Position whiteRightDiag = new Position(whiteRow + 1, (char) (whiteCol + 1));
        board.setPiece(whiteSrc, white);
        assertFalse(white.canMove(board, whiteSrc, whiteLeftDiag));
        assertFalse(white.canMove(board, whiteSrc, whiteRightDiag));
    }

    @org.junit.jupiter.api.Test
    void canMoveDiagonalEmptyBlack() throws InvalidPositionException {
        int row = 5;
        char col = 'f';
        Position init = new Position(row, col);
        Position leftDiag = new Position(row - 1, (char) (col - 1));
        Position rightDiag = new Position(row - 1, (char) (col + 1));
        board.setPiece(init, black);
        assertFalse(white.canMove(board, init, leftDiag));
        assertFalse(white.canMove(board, init, rightDiag));
    }
}
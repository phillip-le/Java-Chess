import javafx.geometry.Pos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    @BeforeEach
    void setUp() throws InvalidPositionException {
        board = new Board();
    }

    @Test
    void testSetupBoard() throws InvalidPositionException {
        for (char col = 'a'; col <= 'h'; col++) {
            Piece white = board.getPiece(new Position(2, col));
            assertTrue(isCorrectPiece(white, Pawn.class, true));
            Piece black = board.getPiece(new Position(7, col));
            assertTrue(isCorrectPiece(black, Pawn.class, false));
        }
        Piece whiteLeftCastle = board.getPiece(new Position(1, 'a'));
        assertTrue(isCorrectPiece(whiteLeftCastle, Castle.class, true));
        Piece whiteRightCastle = board.getPiece(new Position(1, 'h'));
        assertTrue(isCorrectPiece(whiteRightCastle, Castle.class, true));
        Piece blackLeftCastle = board.getPiece(new Position(8, 'a'));
        assertTrue(isCorrectPiece(blackLeftCastle, Castle.class, false));
        Piece blackRightCastle = board.getPiece(new Position(8, 'h'));
        assertTrue(isCorrectPiece(blackRightCastle, Castle.class, false));

        Piece whiteLeftKnight = board.getPiece(new Position(1, 'b'));
        assertTrue(isCorrectPiece(whiteLeftKnight, Knight.class, true));
        Piece whiteRightKnight = board.getPiece(new Position(1, 'g'));
        assertTrue(isCorrectPiece(whiteRightKnight, Knight.class, true));
        Piece blackLeftKnight = board.getPiece(new Position(8, 'b'));
        assertTrue(isCorrectPiece(blackLeftKnight, Knight.class, false));
        Piece blackRightKnight = board.getPiece(new Position(8, 'g'));
        assertTrue(isCorrectPiece(blackRightKnight, Knight.class, false));

        Piece whiteLeftBishop = board.getPiece(new Position(1, 'c'));
        assertTrue(isCorrectPiece(whiteLeftBishop, Bishop.class, true));
        Piece whiteRightBishop = board.getPiece(new Position(1, 'f'));
        assertTrue(isCorrectPiece(whiteRightBishop, Bishop.class, true));
        Piece blackLeftBishop = board.getPiece(new Position(8, 'c'));
        assertTrue(isCorrectPiece(blackLeftBishop, Bishop.class, false));
        Piece blackRightBishop = board.getPiece(new Position(8, 'f'));
        assertTrue(isCorrectPiece(blackRightBishop, Bishop.class, false));

        Piece whiteKing = board.getPiece(new Position(1, 'd'));
        assertTrue(isCorrectPiece(whiteKing, King.class, true));
        Piece blackKing = board.getPiece(new Position(8, 'd'));
        assertTrue(isCorrectPiece(blackKing, King.class, false));
        Piece whiteQueen = board.getPiece(new Position(1, 'e'));
        assertTrue(isCorrectPiece(whiteQueen, Queen.class, true));
        Piece blackQueen = board.getPiece(new Position(8, 'e'));
        assertTrue(isCorrectPiece(blackQueen, Queen.class, false));
    }

    private boolean isCorrectPiece(Piece piece, Class c, boolean white) {
        return piece.getClass() == c && piece.isWhite() == white;
    }

    @AfterEach
    void tearDown() {
        board = null;
    }
}
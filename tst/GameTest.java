import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() throws InvalidPositionException {
        game = new Game();
    }

    @Test
    void standardOpening() throws InvalidPositionException {
        if (game.validMoveInput("e2e4")) {
            game.makeMove(game.getMove(true, "e2e4"));
        }
        game.getBoard().printBoard();
        if (game.validMoveInput("e7e5")) {
            game.makeMove(game.getMove(false, "e7e5"));
        }
        game.getBoard().printBoard();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }
}
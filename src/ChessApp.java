import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class ChessApp extends Application {

    // Specifies the colours for the chess board
    public static final String WHITE_SQUARE = "#F9E487";
    public static final String BLACK_SQUARE = "#9C7B4B";
    public static final String SELECTED_SQUARE = "#93D6D6";

    // Specifies the dimensions of the chess board
    private static final int WINDOW_SIZE = 480;
    private static final int ROW_COUNT = 8;
    public static final int SQUARE_SIZE = WINDOW_SIZE / ROW_COUNT;

    private Group squareGroup, pieceGroup;
    private HashMap<Position, Square> display;
    private Game game;

    private boolean lookingForMove;
    private Position selectedPosition;

    /**
     * Initialise the chess board.
     * @return the root of the chess board display.
     * @throws InvalidPositionException - invalid position on board
     * @throws FileNotFoundException - image for piece could not be found
     */
    private Parent createBoard() throws InvalidPositionException, FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        squareGroup = new Group();
        pieceGroup = new Group();
        display = new HashMap<>();
        game = new Game();
        lookingForMove = false;

        int counter = 0;
        for (int row = ROW_COUNT; row >= 1; row--) {
            for (char col = 'a'; col <= 'h'; col++) {
                Square square;
                if (++counter % 2 == 0) {
                    square = new Square(false);
                } else {
                    square = new Square(true);
                }
                square.setTranslateX(Utility.colToX(col));
                square.setTranslateY(Utility.rowToY(row));
                Position pos = new Position(row, col);
                Piece piece = game.getBoard().getPiece(pos);
                if (piece != null) {
                    square.setPiece(piece);
                    piece.moveIcon(pos);
                    pieceGroup.getChildren().add(piece);
                }
                display.put(pos, square);
                squareGroup.getChildren().add(square);
            }
            counter--;
        }
        root.setOnMouseClicked(e -> selectSquare(clickToPos(e.getSceneX(), e.getSceneY())));
        root.getChildren().addAll(squareGroup, pieceGroup);
        return root;
    }

    /**
     * Select a square on the board to move from or to.
     * @param pos - the position of the square to select.
     */
    private void selectSquare(Position pos) {
        // If the position is invalid
        if (pos == null) {
            return;
        }
        Player currentPlayer = game.getCurrentPlayer();
        // If trying to select an empty square to move from
        if (!lookingForMove) {
            Piece selectedPiece = game.getBoard().getPiece(pos);
            // Trying to move from empty square
            if (selectedPiece == null) {
                System.out.println("Trying to move from empty square!");
                return;
            // Trying to move the opponent's piece
            } else if (selectedPiece.isWhite() != currentPlayer.isWhite()) {
                System.out.println("Trying to move opponents piece!");
                return;
            }
            // Highlight the square that the player is moving from
            System.out.println("Looking for move!");
            lookingForMove = true;
            highlightSquare(pos);
            return;
        }
        // Attempt to move from selectedPosition to pos
        Piece piece = game.getBoard().getPiece(selectedPosition);
        System.out.println("Trying to move " + piece.isWhite() + " " + piece.toString());
        Move move = new Move(currentPlayer, selectedPosition, pos);
        if (game.validMove(move)) {
            System.out.println("Moved piece!");
            movePiece(move);
            if (game.isCheckmate(game.whiteKingPos)) {
                System.out.println("Black won!");
            } else if (game.isCheckmate(game.blackKingPos)) {
                System.out.println("White won!");
            }
            game.switchPlayers();
        }
        resetLookingForMove();
    }

    /**
     * Move the piece. Assumes that the move is valid.
     * See @game.validMove
     * @param move - the move to make
     */
    private void movePiece(Move move) {
        Piece piece = game.getBoard().getPiece(move.getSrc());
        display.get(move.getSrc()).setPiece(null);
        display.get(move.getDst()).setPiece(piece);
        pieceGroup.getChildren().remove(game.getBoard().getPiece(move.getDst()));
        game.makeMove(move);
    }

    /**
     * Highlight the square at pos.
     * @param pos - the position of the square to highlight.
     */
    private void highlightSquare(Position pos) {
        Square square = display.get(pos);
        square.setFill(SELECTED_SQUARE);
        selectedPosition = pos;
        lookingForMove = true;
    }

    /**
     * Unhighlight the previously selected square.
     */
    private void resetLookingForMove() {
        lookingForMove = false;
        display.get(selectedPosition).setDefaultFill();
        selectedPosition = null;
    }

    /**
     * Convert the coords of a mouse click to a Position on the board.
     * @param eventX - the X coordinate of the mouse click.
     * @param eventY - the Y coordinate of the mouse click.
     * @return the Position of the mouse click on the board.
     */
    private Position clickToPos(double eventX, double eventY) {
        int row = Utility.yToRow(eventY);
        char col = Utility.xToCol(eventX);
        try {
            System.out.println("Clicked: " + new Position(row, col));
            return new Position(row, col);
        } catch (InvalidPositionException ipe) {
            return null;
        }
    }

    @Override
    public void start(Stage stage) throws InvalidPositionException, FileNotFoundException {
        stage.setScene(new Scene(createBoard()));
        stage.show();
    }

 public static void main(String[] args) {
        launch(args);
    }
}
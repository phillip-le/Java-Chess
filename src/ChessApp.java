import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class ChessApp extends Application {

    public static final String WHITE_SQUARE = "#F9E487";
    public static final String BLACK_SQUARE = "#9C7B4B";
    public static final String SELECTED_SQUARE = "#93D6D6";

    private static final int WINDOW_SIZE = 480;
    private static final int ROW_COUNT = 8;
    public static final int SQUARE_SIZE = WINDOW_SIZE / ROW_COUNT;

    private Group squareGroup, pieceGroup;
    private HashMap<Position, Square> display;
    private Game game;
    private Player whitePlayer, blackPlayer;
    // Specifies if it is white's turn to play
    private Player currentPlayer;

    private boolean lookingForMove;
    private Position selectedPosition;

    private Parent createBoard() throws InvalidPositionException, FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        squareGroup = new Group();
        pieceGroup = new Group();
        display = new HashMap<>();
        game = new Game();
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        currentPlayer = whitePlayer;
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
        root.setOnMouseClicked(e -> {
            selectSquare(clickToPos(e.getSceneX(), e.getSceneY()));
        });
        root.getChildren().addAll(squareGroup, pieceGroup);
        return root;
    }

    private void selectSquare(Position pos) {
        // If the position is invalid
        if (pos == null) {
            return;
        }
        // If trying to select an empty square to move from
        if (!lookingForMove) {
            Piece selectedPiece = game.getBoard().getPiece(pos);
            if (selectedPiece == null) {
                System.out.println("Trying to move from empty square!");
                return;
            } else if (selectedPiece.isWhite() != currentPlayer.isWhite()) {
                System.out.println("Trying to move opponents piece!");
                return;
            }
            System.out.println("Looking for move!");
            lookingForMove = true;
            highlightSquare(pos);
            return;
        }
        Piece piece = game.getBoard().getPiece(selectedPosition);
        System.out.println("Trying to move " + piece.isWhite() + " " + piece.toString());
        Move move = new Move(currentPlayer, selectedPosition, pos);
        if (piece.canMove(game.getBoard(), selectedPosition, pos) && game.validMove(move)) {
            System.out.println("Moved piece!");
            movePiece(move);
        }
        resetLookingForMove();
        if (currentPlayer.isWhite()) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    private void resetLookingForMove() {
        lookingForMove = false;
        display.get(selectedPosition).setDefaultFill();
        selectedPosition = null;
    }

    private void movePiece(Move move) {
        Piece piece = game.getBoard().getPiece(move.getSrc());
        display.get(move.getSrc()).setPiece(null);
        display.get(move.getDst()).setPiece(piece);
        game.makeMove(move);
    }

    private void highlightSquare(Position pos) {
        Square square = display.get(pos);
        square.setFill(SELECTED_SQUARE);
        selectedPosition = pos;
        lookingForMove = true;
    }

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
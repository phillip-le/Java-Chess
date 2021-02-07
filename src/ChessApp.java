import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class ChessApp extends Application {

    private static final int WINDOW_SIZE = 480;
    private static final int ROW_COUNT = 8;
    public static final int SQUARE_SIZE = WINDOW_SIZE / ROW_COUNT;
    private Group squareGroup, pieceGroup;
    private HashMap<Position, Square> display;
    private Game game;

    private Parent createBoard() throws InvalidPositionException, FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        squareGroup = new Group();
        pieceGroup = new Group();
        display = new HashMap<>();
        game = new Game();
        int counter = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < ROW_COUNT; j++) {
                Square square;
                if (++counter % 2 == 0) {
                    square = new Square(false);
                } else {
                    square = new Square(true);
                }
                square.setTranslateX(j * SQUARE_SIZE);
                square.setTranslateY(i * SQUARE_SIZE);
                display.put(new Position(i + 1, (char) (j + 'a')), square);
                squareGroup.getChildren().add(square);
            }
            counter--;
        }
        for (Position p : game.getBoard().getTeamPositions(true)) {
            Piece piece = game.getBoard().getPiece(p);
            display.get(p).setPiece(piece);
            pieceGroup.getChildren().add(piece);
        }
        for (Position p : game.getBoard().getTeamPositions(false)) {
            Piece piece = game.getBoard().getPiece(p);
            display.get(p).setPiece(piece);
            pieceGroup.getChildren().add(piece);
        }

        root.getChildren().addAll(squareGroup, pieceGroup);
        return root;
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
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

import java.util.HashMap;

public class ChessApp extends Application {

    private static final int WINDOW_SIZE = 480;
    private static final int ROW_COUNT = 8;
    public static final int SQUARE_SIZE = WINDOW_SIZE / ROW_COUNT;
    private HashMap<Position, Square> board;
    private Group squareGroup, pieceGroup;

    private Parent createBoard() throws InvalidPositionException {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
        board = new HashMap<>();
        squareGroup = new Group();
        pieceGroup = new Group();

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
                board.put(new Position(i + 1, (char) (j + 'a')), square);
                squareGroup.getChildren().add(square);
            }
            counter--;
        }
        root.getChildren().addAll(squareGroup, pieceGroup);
        return root;
    }



    @Override
    public void start(Stage stage) throws InvalidPositionException {
        stage.setScene(new Scene(createBoard()));
        stage.show();
    }

 public static void main(String[] args) {
        launch(args);
    }
}
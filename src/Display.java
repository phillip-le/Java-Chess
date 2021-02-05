import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    private static int WINDOW_SIZE = 480;
    private static int ROW_COUNT = 8;
    private static int SQUARE_SIZE = WINDOW_SIZE / ROW_COUNT;


    private Parent createBoard() {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
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
                root.getChildren().add(square);
            }
            counter--;
        }
        return root;
    }

    private class Square extends StackPane {
        public Square(boolean white) {
            Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
            if (white) {
                square.setFill(null);
            }
            square.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().add(square);
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(createBoard()));
        stage.show();
    }

 public static void main(String[] args) {
        launch(args);
    }
}
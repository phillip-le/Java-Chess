import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane {

    private Piece piece;

    public Square(boolean white) {
        Rectangle square = new Rectangle(ChessApp.SQUARE_SIZE, ChessApp.SQUARE_SIZE);
        if (white) {
            square.setFill(null);
        }
        square.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().add(square);
    }

    public boolean hasPiece() {
        return piece != null;
    }
}
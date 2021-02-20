import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane {

    private Piece piece;
    private Rectangle square;
    private boolean white;

    public Square(boolean white) {
        square = new Rectangle(ChessApp.SQUARE_SIZE, ChessApp.SQUARE_SIZE);
        this.white = white;
        setDefaultFill();
        square.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().add(square);
    }

    public void setDefaultFill() {
        if (white) {
            setFill(ChessApp.WHITE_SQUARE);
        } else {
            setFill(ChessApp.BLACK_SQUARE);
        }
    }

    public void setFill(String colour) {
        square.setFill(Color.web(colour));
    }

    public boolean isWhite() {
        return white;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
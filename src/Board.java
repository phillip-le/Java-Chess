import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Board {
    private Dictionary<Position, Piece> field;

    public Board() {
        this.field = new Hashtable<>();
    }

    public Piece getPiece(Position position) {
        return this.field.get(position);
    }

    public void setPiece(Position position, Piece piece) {
        this.field.put(position, piece);
    }
}

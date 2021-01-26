import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Board {
    private Dictionary<Position, Piece> field;
    private static final int numTeamPieces = 16;
    private List<Position> blackPieces;
    private List<Position> whitePieces;

    public Board() {
        this.field = new Hashtable<>();
        this.blackPieces = new ArrayList<>(numTeamPieces);
        this.whitePieces = new ArrayList<>(numTeamPieces);
    }

    public Piece getPiece(Position position) {
        return this.field.get(position);
    }

    public void setPiece(Position position, Piece piece) {
        this.field.put(position, piece);
    }

    public void removePiece(Position position, Piece piece) {
        this.field.remove(position);
    }

    public List<Position> getTeamPieces(boolean white) {
        return white ? whitePieces : blackPieces;
    }
}

import java.util.Objects;

public class Position {
    private final int row;
    private final char col;
    private Piece piece;

    public Position(int row, char col) throws InvalidPositionException {
        if (row < 1 || row > 8 || (int) col < 97 || (int) col > 104) {
            throw new InvalidPositionException("Invalid coordinates for position.");
        }
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public char getCol() {
        return this.col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col && Objects.equals(piece, position.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, piece);
    }

    @Override
    public String toString() {
        return row + String.valueOf(col);
    }
}

class InvalidPositionException extends Exception {
    public InvalidPositionException(String s) {
        super(s);
    }
}
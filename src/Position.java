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
}

class InvalidPositionException extends Exception {
    public InvalidPositionException(String s) {
        super(s);
    }
}
import java.util.*;

public class Board {
    private Dictionary<Position, Piece> field;
    private static final int numTeamPieces = 16;
    private List<Position> blackPositions;
    private List<Position> whitePositions;

    public Board() throws InvalidPositionException {
        field = new Hashtable<>();
        blackPositions = new ArrayList<>(numTeamPieces);
        whitePositions = new ArrayList<>(numTeamPieces);

        // Setup the board
        for (char col = 'a'; col <= 'h'; col++) {
            addPiece(new Pawn(true), new Position(2, col));
            addPiece(new Pawn(false), new Position(7, col));
        }
        addSymmetricalPieces(new Castle(true), new Castle(true),
                new Castle(false), new Castle(false), 1, 'a');
        addSymmetricalPieces(new Knight(true), new Knight(true),
                new Knight(false), new Knight(false), 1, 'b');
        addSymmetricalPieces(new Bishop(true), new Bishop(true),
                new Bishop(false), new Bishop(false), 1, 'c');
        addPiece(new King(true), new Position(1, 'd'));
        addPiece(new Queen(true), new Position(1, 'e'));
        addPiece(new King(false), new Position(8, 'd'));
        addPiece(new Queen(false), new Position(8, 'e'));
    }

    public Piece getPiece(Position position) {
        return field.get(position);
    }

    public void setPiece(Position position, Piece piece) {
        field.put(position, piece);
        getTeamPositions(piece.isWhite()).add(position);
    }

    public void removePiece(Position position) {
        Piece piece = getPiece(position);
        getTeamPositions(piece.isWhite()).remove(position);
        field.remove(position);
    }

    public List<Position> getTeamPositions(boolean white) {
        return white ? whitePositions : blackPositions;
    }

    private void addPiece(Piece piece, Position position) {
        field.put(position, piece);
        if (piece.isWhite()) {
            whitePositions.add(position);
        } else {
            blackPositions.add(position);
        }
    }

    private void addSymmetricalPieces(Piece whiteLeft, Piece whiteRight, Piece blackLeft,
            Piece blackRight, int row, char col) throws InvalidPositionException {
        Position whiteLeftPos = new Position(row, col);
        Position whiteRightPos = new Position(row, (char) ('h' - ((int) col - 97)));
        Position blackLeftPos = new Position(8 - row + 1, col);
        Position blackRightPos = new Position(8 - row + 1, (char) ('h' - ((int) col - 97)));
        field.put(whiteLeftPos, whiteLeft);
        field.put(whiteRightPos, whiteRight);
        field.put(blackLeftPos, blackLeft);
        field.put(blackRightPos, blackRight);
        whitePositions.add(whiteLeftPos);
        whitePositions.add(whiteRightPos);
        blackPositions.add(blackLeftPos);
        blackPositions.add(blackRightPos);
    }

    public void printBoard() throws InvalidPositionException {
        for (int i = 8; i >= 1; i--) {
            for (char j = 'a'; j <= 'h'; j++) {
                Piece piece = field.get(new Position(i, j));
                if (piece != null) {
                    System.out.print(piece.toString());
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

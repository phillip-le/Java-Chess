import javafx.geometry.Pos;

public class Game {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;

    public Game() throws InvalidPositionException {
        board = new Board();
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
    }

    private Player getPlayer(boolean white) {
        if (white) {
            return whitePlayer;
        }
        return blackPlayer;
    }

    /**
     * Checks if move is valid.
     * @param move - the move to be made.
     * @return true if move is valid, else false.
     */
    public boolean validMove(Move move) {
        Piece piece = board.getPiece(move.getSrc());
        return piece != null && move.getPlayer().isWhite() == piece.isWhite() &&
                piece.canMove(board, move.getSrc(), move.getDst());
    }

    /**
     * Moves the piece from starting position to ending position and kills
     * piece at ending position if one exists.
     * @param move - the move to be made.
     */
    public void makeMove(Move move) {
        Piece dstPiece = board.getPiece(move.getDst());
        if (dstPiece != null) {
            dstPiece.setKilled();
            board.removePiece(move.getDst());
        }
        board.setPiece(move.getDst(), board.getPiece(move.getSrc()));
        board.removePiece(move.getSrc());
    }

    public boolean validMoveInput(String rawMove) {
        try {
            new Position(Integer.parseInt(String.valueOf(rawMove.charAt(1))), rawMove.charAt(0));
            new Position(Integer.parseInt(String.valueOf(rawMove.charAt(3))), rawMove.charAt(2));
        } catch (InvalidPositionException e) {
            return false;
        }
        return true;
    }

    public Move getMove(boolean white, String rawMove) throws InvalidPositionException {
        return new Move(getPlayer(white), new Position(Integer.parseInt(String.valueOf(rawMove.charAt(1))), rawMove.charAt(0)),
                new Position(Integer.parseInt(String.valueOf(rawMove.charAt(3))), rawMove.charAt(2)));
    }

    /**
     * Gets the board of the game.
     * @return the board of the game.
     */
    public Board getBoard() {
        return board;
    }
}

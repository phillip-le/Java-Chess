import java.io.FileNotFoundException;
import java.util.List;

public class Game {

    private Board board;
    private Player whitePlayer, blackPlayer, currentPlayer;
    Position whiteKingPos, blackKingPos;

    public Game() throws InvalidPositionException, FileNotFoundException {
        board = new Board();
        whiteKingPos = new Position(1, 'd');
        blackKingPos = new Position(8, 'd');
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        currentPlayer = whitePlayer;
    }

    /**
     * Gets the current player of the game.
     * @return the current player of the game.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get the white player if true, else get the black player.
     * @param white - whether to get the white player.
     * @return the white player if true, else the black player.
     */
    public Player getPlayer(boolean white) {
        if (white) {
            return whitePlayer;
        }
        return blackPlayer;
    }

    /**
     * Switches the player whose turn it is.
     */
    public void switchPlayers() {
        if (currentPlayer.isWhite()) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
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
        Piece srcPiece = board.getPiece(move.getSrc());
        srcPiece.moveIcon(move.getDst());
        board.setPiece(move.getDst(), srcPiece);
        board.removePiece(move.getSrc());
        if (srcPiece.getClass() == King.class) {
            if (srcPiece.isWhite()) {
                whiteKingPos = move.getDst();
            } else {
                blackKingPos = move.getDst();
            }
        }
    }

    /**
     * Checks if the rawMove is a valid input for a move.
     * @param rawMove - the raw input for a move.
     * @return true if rawMove is a valid input for a move, else false.
     */
    public boolean validMoveInput(String rawMove) {
        try {
            new Position(Integer.parseInt(String.valueOf(rawMove.charAt(1))), rawMove.charAt(0));
            new Position(Integer.parseInt(String.valueOf(rawMove.charAt(3))), rawMove.charAt(2));
        } catch (InvalidPositionException e) {
            return false;
        }
        return true;
    }

    /**
     * Gets the move based on the rawMove and player who played the move.
     * @param white - whether the white player played the move.
     * @param rawMove - the move of a piece.
     * @return the move of a piece.
     * @throws InvalidPositionException - an invalid position on the board.
     */
    public Move getMove(boolean white, String rawMove) throws InvalidPositionException {
        return new Move(getPlayer(white),
                new Position(Integer.parseInt(String.valueOf(rawMove.charAt(1))), rawMove.charAt(0)),
                new Position(Integer.parseInt(String.valueOf(rawMove.charAt(3))), rawMove.charAt(2)));
    }

    /**
     * Checks if the King at the position src is in checkmate.
     * @param src - the position of the King
     * @return true if the King is in checkmate, else false.
     */
    public boolean isCheckmate(Position src) {
        Piece king = board.getPiece(src);
        // Check if other team can capture the king
        List<Position> opposingTeamPos = board.getTeamPositions(!king.isWhite());
        boolean canCapture = false;
        for (Position p : opposingTeamPos) {
            if (board.getPiece(p).canMove(board, p, src)) {
                canCapture = true;
            }
        }
        if (!canCapture) {
            return false;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    Position dst = new Position(src.getRow() + i,
                            Utility.intToChar(Utility.charToInt(src.getCol()) + j));
                    if (king.canMove(board, src, dst)) {
                        return false;
                    }
                } catch (InvalidPositionException ignored) {}
            }
        }
        return true;
    }

    /**
     * Gets the board of the game.
     * @return the board of the game.
     */
    public Board getBoard() {
        return board;
    }
}

public class Utility {
    public static int rowToY(int row) {
        return (8 - row) * ChessApp.SQUARE_SIZE;
    }

    public static int yToRow(double y) {
        return 8 - (int) Math.floor(y / ChessApp.SQUARE_SIZE);
    }

    public static double colToX(char col) {
        return (col - 'a') * ChessApp.SQUARE_SIZE;
    }

    public static char xToCol(double x) {
        return intToChar((int) Math.floor(x / ChessApp.SQUARE_SIZE));
    }

    public static char intToChar(int value) {
        return (char) (value + 'a');
    }

    public static int charToInt(char character) {
        return character - 'a';
    }
}

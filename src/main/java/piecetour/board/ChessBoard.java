package piecetour.board;

public class ChessBoard {

    private final Integer boardSize;

    public ChessBoard(Integer boardSize){
        this.boardSize=boardSize;
    }

    // function restricts the knight to remain within
    // the 8x8 chessboard
    boolean limits(int x, int y) {
        return ((x >= 0 && y >= 0) &&
                (x < boardSize && y < boardSize));
    }

    /* Checks whether a square is valid and
    empty or not */
    boolean isempty(int a[], int x, int y) {
        return (limits(x, y)) && (a[y * boardSize + x] < 0);
    }

    /* displays the chessboard with all the
    legal knight's moves */
    void print(int boardCells[]) {
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j)
                System.out.printf("%d\t", boardCells[j * boardSize + i]);
            System.out.printf("\n");
        }
    }


}

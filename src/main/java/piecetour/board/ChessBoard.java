package piecetour.board;

import java.text.DecimalFormat;

public class ChessBoard {

    private final Integer boardSize = 10;
    private final Cell boardCells[];

    public ChessBoard(Integer boardSize){
        this.boardCells = new Cell[boardSize * boardSize];
        // Filling up the chessboard matrix with -1's
        for (Integer line = 0; line < boardSize; ++line) {
            for (Integer column = 0; column < boardSize; ++column) {
                setCellAt(line, column, new Cell(line, column, -1));
            }
        }

    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public Cell[] getBoardCells() {
        return boardCells;
    }

    public void setCellAt(Integer linePlace, Integer columnPlace, Cell cell) {
        boardCells[columnPlace * boardSize + linePlace] = cell;
    }

    public Cell getCellAt(Integer linePlace, Integer columnPlace) {
        return boardCells[columnPlace * boardSize + linePlace];
    }

    // function restricts the knight to remain within
    // the 10x10 chessboard
    public boolean isPointInsideBoardLimits(Integer linePlace, Integer columnPlace) {
        return ((linePlace >= 0 && columnPlace >= 0) &&
                (linePlace < boardSize && columnPlace < boardSize));
    }

    /* Checks whether boardCells square is valid and
    empty or not */
    public boolean isCellValidAndEmpty(Integer linePlace, Integer columnPlace) {
        return (isPointInsideBoardLimits(linePlace, columnPlace)) && (getCellAt(linePlace, columnPlace).getVisitNumber() < 0);
    }

    /* displays the chessboard with all the
legal knight's moves */
    public void printBoard() {
        DecimalFormat format = new DecimalFormat("00");
        for (Integer line = 0; line < boardSize; ++line) {
            for (Integer column = 0; column < boardSize; ++column)
                System.out.printf(" %s", format.format(getCellAt(line, column).getVisitNumber()));
            System.out.printf("\n");
        }
    }



}

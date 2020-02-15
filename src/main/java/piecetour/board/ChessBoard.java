package piecetour.board;

import piecetour.board.piece.PieceTour;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * It describes a chessboard containing the size and its cells.
 * The class has validation methods for its size and boundaries.
 *
 * @author Ricardo Pereira Ramalho
 */
public class ChessBoard {

    /** Logger to show warnings and info */
    private Logger logger = Logger.getLogger(PieceTour.class.getName());

    /**
     * attribute to represent the size of the board
     */
    private final Integer boardSize;

    /**
     * The cells contained inside the chessboard
     */
    private final Cell boardCells[];

    /**
     * Constructor that builds a new chessboard according to the provided size,
     * filling the cells with the initial value of -1
     *
     * @param boardSize represents the number of cells per side of a square chessboard
     */
    public ChessBoard(Integer boardSize) throws InvalidPositionException {
        this.boardSize = boardSize;
        this.boardCells = new Cell[boardSize * boardSize];
        // Filling up the chessboard matrix with -1's
        for (Integer line = 0; line < boardSize; ++line) {
            for (Integer column = 0; column < boardSize; ++column) {
                setCellAt(line, column, new Cell(line, column, -1));
            }
        }

    }

    /**
     * Getter method for the attribute {@code boardSize}
     *
     * @return {@link Integer} containing the side size of the board
     */
    public Integer getBoardSize() {
        return boardSize;
    }

    /**
     * Getter method for the attribute {@code boardCells}
     *
     * @return array of {@link piecetour.board.Cell} containing the all the cells of the board
     */
    public Cell[] getBoardCells() {
        return boardCells;
    }


    /**
     * Put a {@link piecetour.board.Cell} on specific position
     *
     * @param linePlace   {@link Integer} containing the position in the line
     * @param columnPlace {@link Integer} containing the position in the column
     * @param cell        {@link piecetour.board.Cell} to fill the board
     * @throws InvalidPositionException if the line and column values are invalid
     */
    private void setCellAt(Integer linePlace, Integer columnPlace, Cell cell) throws InvalidPositionException {
        if (isPointInsideBoardLimits(linePlace, columnPlace)) {
            throw new InvalidPositionException("Invalid line and column");
        }
        boardCells[columnPlace * boardSize + linePlace] = cell;
    }

    /**
     * Retrieve a {@link piecetour.board.Cell} in specific position
     *
     * @param linePlace
     * @param columnPlace
     * @return respective position {@link piecetour.board.Cell}
     */
    public Cell getCellAt(Integer linePlace, Integer columnPlace) throws InvalidPositionException {
        if (isPointInsideBoardLimits(linePlace, columnPlace)) {
            throw new InvalidPositionException("Invalid line and column");
        }
        return boardCells[columnPlace * boardSize + linePlace];
    }

    /**
     * Validate if the given position is inside the board
     *
     * @param linePlace {@link Integer} containing the position in the line
     * @param columnPlace {@link Integer} containing the position in the column
     * @return {@link Boolean} that states if the position is inside the board
     */
    public Boolean isPointInsideBoardLimits(Integer linePlace, Integer columnPlace) {
        return ((linePlace >= 0 && columnPlace >= 0) &&
                (linePlace < boardSize && columnPlace < boardSize));
    }

    /**
     * Validate if the cell in the given position is inside the board and hasn't be visited before
     *
     * @param linePlace {@link Integer} containing the position in the line
     * @param columnPlace {@link Integer} containing the position in the column
     * @return {@link Boolean} that states if the cell is valid to be visited
     * @throws InvalidPositionException if the line and column values are invalid
     */
    public boolean isCellValidAndEmpty(Integer linePlace, Integer columnPlace) throws InvalidPositionException {
        return (isPointInsideBoardLimits(linePlace, columnPlace)) && (getCellAt(linePlace, columnPlace).getVisitNumber() < 0);
    }

    /**
     * Print the board in the console
     *
     * @throws InvalidPositionException if any line and column values inside the loop are invalid
     */
    public void printBoard() throws InvalidPositionException {
        DecimalFormat format = new DecimalFormat("00");
        StringBuilder board = new StringBuilder("\n");
        for (Integer line = 0; line < boardSize; ++line) {
            for (Integer column = 0; column < boardSize; ++column)
                board.append(String.format(" %s", format.format(getCellAt(line, column).getVisitNumber())));
            board.append("\n");
        }
        logger.info(board.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard)) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.equals(getBoardSize(), that.getBoardSize()) &&
                Arrays.equals(getBoardCells(), that.getBoardCells());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(getBoardSize());
        result = 31 * result + Arrays.hashCode(getBoardCells());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ChessBoard{" +
                "boardSize=" + boardSize +
                ", boardCells=" + Arrays.toString(boardCells) +
                '}';
    }
}

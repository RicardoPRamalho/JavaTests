package piecetour.board.piece;

import piecetour.board.Cell;
import piecetour.board.ChessBoard;
import piecetour.board.InvalidPositionException;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 * Perform the piece's tour using the Warnsdorff's rule
 *
 * @author Ricardo Pereira Ramalho
 */
public class PieceTour {

    /**
     * Logger to show warnings and info
     */
    private Logger logger = Logger.getLogger(PieceTour.class.getName());

    /**
     * Attribute that represents the chessboard
     */
    public ChessBoard board;


    /*  */

    /**
     * Initiate the piece's tour processing it until it's complete.
     *
     * @param initialLine   {@link Integer} initial position in the line
     * @param initialColumn {@link Integer} initial position in the column
     * @throws InvalidPositionException if any invalid position is attempted
     */
    public void findClosedTour(Integer initialLine, Integer initialColumn) throws InvalidPositionException {
        Boolean tourComplete = Boolean.FALSE;
        while (!tourComplete) {
            tourComplete = makeTour(initialLine, initialColumn);
        }
    }

    /**
     * Generates the tour using Warnsdorff's rule.
     *
     * @param initialLine   {@link Integer} initial position in the line
     * @param initialColumn {@link Integer} initial position in the column
     * @return {@link Boolean} states if the movements using the Warnsdorff rule are possible.
     * @throws InvalidPositionException if any invalid position is attempted
     */
    private Boolean makeTour(Integer initialLine, Integer initialColumn) throws InvalidPositionException {
        board = new ChessBoard(10);

        if (!board.isPointInsideBoardLimits(initialLine, initialColumn)) {
            throw new InvalidPositionException("Invalid line and column");
        }
        Cell cell = board.getCellAt(initialLine, initialColumn);
        cell.setVisitNumber(1); // Mark first move.
        Integer requiredMoviments = board.getBoardSize() * board.getBoardSize() - 1;

        for (Integer i = 0; i < requiredMoviments; ++i) {
            cell = nextMove(cell);
            if (cell == null) {
                return false;
            }
        }
        if (!verifyNeighbours(cell, initialLine, initialColumn)) {
            return false;
        }
        return true;

    }

    /**
     * Given the current {@link piecetour.board.Cell} retrieves the next {@link piecetour.board.Cell} according to the
     * Warnsdorff's Rule
     *
     * @param currentCell {@link piecetour.board.Cell} where the movement has been initiated
     * @return {@link piecetour.board.Cell} where the movement ended, {@link null} if no possible movement can be attempted
     * @throws InvalidPositionException if any invalid position is attempted
     */
    private Cell nextMove(Cell currentCell) throws InvalidPositionException {
        Movement chosenMovement = findBestMovementByWarnsdorffsRule(currentCell);

        if (chosenMovement == null) {
            return null;
        }
        Cell nextCell = board.getCellAt(currentCell.getLine() + chosenMovement.getLine(),
                currentCell.getColumn() + chosenMovement.getColumn());
        nextCell.setVisitNumber(
                board.getCellAt(currentCell.getLine(), currentCell.getColumn()).getVisitNumber() + 1);

        return nextCell;
    }

    /**
     * Implements the Waensdorff's rule that states:
     * 1- Given a initial cell
     * 2- Randomly iterate over the movements
     * 3- Verifies all the adjacent cells
     * 4- Select the cell with minor number of unvisited places adjacent
     * 
     * @param currentCell {@link piecetour.board.Cell} where the movement has been initiated
     * @return  {@link piecetour.board.Cell} where the movement ended
     * @throws InvalidPositionException if any invalid position is attempted
     */
    private Movement findBestMovementByWarnsdorffsRule(Cell currentCell) throws InvalidPositionException {
        Movement possibleMovement;
        Integer possibleLine;
        Integer possibleColumn;
        Integer adjacentEmptyCellsNumber;
        Integer previousAdjacentEmptyCellsNumber = (board.getBoardSize() + 1);
        Integer start = ThreadLocalRandom.current().nextInt(1000) % Movement.values().length;
        Movement choosenMovement = null;
        for (Integer count = 0; count < Movement.values().length; count++) {
            possibleMovement = Movement.values()[(start + count) % Movement.values().length];
            possibleLine = currentCell.getLine() + possibleMovement.getLine();
            possibleColumn = currentCell.getColumn() + possibleMovement.getColumn();
            adjacentEmptyCellsNumber = getNeighboringEmptyCellsNumber(possibleLine, possibleColumn);
            if ((board.isCellValidAndEmpty(possibleLine, possibleColumn))
                    && adjacentEmptyCellsNumber < previousAdjacentEmptyCellsNumber) {
                choosenMovement = possibleMovement;
                previousAdjacentEmptyCellsNumber = adjacentEmptyCellsNumber;
            }
        }
        return choosenMovement;
    }

    /**
     * Retrieves the quantity of empty squares neighboring the given position
     *
     * @param linePlace   {@link Integer} position in the line
     * @param columnPlace {@link Integer} position in the column
     * @return {@link Integer} the number of empty squares adjacent to the given position
     * @throws InvalidPositionException
     */
    private Integer getNeighboringEmptyCellsNumber(Integer linePlace, Integer columnPlace)
            throws InvalidPositionException {
        Integer count = 0;
        for (Movement move : Movement.values()) {
            if (board.isCellValidAndEmpty((linePlace + move.getLine()), (columnPlace + move.getColumn()))) {
                count++;
            }
        }

        return count;
    }

    /**
     * Validate if the given cell is near the given position
     *
     * @param cell          {@link piecetour.board.Cell} to be verified
     * @param currentLine   {@link Integer} position in the line
     * @param currentColumn {@link Integer} position in the line
     * @return {@link Boolean} states if the cell is near the given position
     */
    private Boolean verifyNeighbours(Cell cell, Integer currentLine, Integer currentColumn) {
        for (Movement move : Movement.values()) {
            if (((cell.getLine() + move.getLine()) == currentLine) &&
                    ((cell.getColumn() + move.getColumn()) == currentColumn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for the attribute {@code board}
     *
     * @return {@link piecetour.board.ChessBoard} containing the chessboard in which the tour occurred.
     */
    public ChessBoard getBoard() {
        return board;
    }
}

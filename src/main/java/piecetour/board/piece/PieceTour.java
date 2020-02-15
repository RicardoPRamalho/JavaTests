package piecetour.board.piece;

import piecetour.board.Cell;
import piecetour.board.ChessBoard;

import java.util.concurrent.ThreadLocalRandom;

public class PieceTour {

    public ChessBoard board;

    /* Generates the legal moves using warnsdorff's
    heuristics. Returns false if not possible */
    public void findClosedTour(Integer initialLine, Integer initialColumn) {
        while (!makeTour(initialLine, initialColumn)) {
            System.out.println("processing tour...");
        }
    }

    private Boolean makeTour(Integer initialLine, Integer initialColumn) {
        board = new ChessBoard(10);
        Cell cell = board.getCellAt(initialLine, initialColumn);
        cell.setVisitNumber(1); // Mark first move.
        Integer requiredMoviments = board.getBoardSize() * board.getBoardSize() - 1;

        for (Integer i = 0; i < requiredMoviments; ++i) {
            cell = nextMove(cell);
            if (cell == null) {
                return false;
            }
        }
        if (!neighbour(cell, initialLine, initialColumn)) {
            return false;
        }
        return true;

    }

    // Picks next point using Warnsdorff's heuristic.
    // Returns false if it is not possible to pick
    // next point.
    private Cell nextMove(Cell currentCell) {
        // Try all N adjacent of (*x, *y) starting
        // from boardCells random adjacent. Find the adjacent
        // with minimum degree.
        Movement chosenMovement = findBestMovementByWarnsdorffRule(currentCell);

        // IF we could not find boardCells next currentCell
        if (chosenMovement == null) {
            return null;
        }

        Cell nextCell = board.getCellAt(currentCell.getLine() + chosenMovement.getLine(),
                currentCell.getColumn() + chosenMovement.getColumn());
        nextCell.setVisitNumber(
                board.getCellAt(currentCell.getLine(), currentCell.getColumn()).getVisitNumber() + 1);

        return nextCell;
    }

    private Movement findBestMovementByWarnsdorffRule(Cell currentCell) {
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

    /* Returns the number of empty squares
    adjacent to (linePlace, columnPlace) */
    private Integer getNeighboringEmptyCellsNumber(Integer linePlace, Integer columnPlace) {
        Integer count = 0;
        for (Movement move : Movement.values()) {
            if (board.isCellValidAndEmpty((linePlace + move.getLine()), (columnPlace + move.getColumn()))) {
                count++;
            }
        }

        return count;
    }

    /* checks its neighbouring sqaures */
    /* If the knight ends on a square that is one
    knight's move from the beginning square,
    then tour is closed */
    private Boolean neighbour(Cell nextCell, Integer currentLine, Integer currentColumn) {
        for (Movement move : Movement.values()) {
            if (((nextCell.getLine() + move.getLine()) == currentLine) &&
                    ((nextCell.getColumn() + move.getColumn()) == currentColumn)) {
                return true;
            }
        }
        return false;
    }

    public ChessBoard getBoard() {
        return board;
    }
}

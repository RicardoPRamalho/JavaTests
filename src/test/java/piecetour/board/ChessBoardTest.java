package piecetour.board;

import org.junit.Before;
import org.junit.Test;
import piecetour.board.exception.InvalidPositionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test cases for the class {@link ChessBoard}
 *
 * @author Ricardo Pereira Ramalho
 */
public class ChessBoardTest {

    private static ChessBoard chessBoard;

    /**
     * Initialize the chessboard
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Before
    public void init() throws InvalidPositionException {
        chessBoard = new ChessBoard(10);
    }

    /**
     * Test if the class return true for a valid position inside the board
     */
    @Test
    public void shouldReturnTrueIfPositionIsValid() {
        Boolean inBounds = chessBoard.isPointInsideBoardLimits(2, 3);

        assertThat(inBounds, equalTo(Boolean.TRUE));
    }

    /**
     * Test if the class return false for a invalid position inside the board
     */
    @Test
    public void shouldReturnFalseIfPositionIsInvalid() {
        Boolean inBounds = chessBoard.isPointInsideBoardLimits(-2, 3);

        assertThat(inBounds, equalTo(Boolean.FALSE));
    }

    /**
     * Test if the application return true for a empty cell in a valid position
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Test
    public void shouldReturnTrueIfCellAtPositionIsValidAndEmpty() throws InvalidPositionException {
        Boolean isValid = chessBoard.isCellValidAndEmpty(2, 3);
        assertThat(isValid, equalTo(Boolean.TRUE));
    }

    /**
     * Test if the application return false for a cell in a invalid position
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Test
    public void shouldReturnFalseIfCellAtPositionIsInvalidAndEmpty() throws InvalidPositionException {
        Boolean isValid = chessBoard.isCellValidAndEmpty(-2, 3);
        assertThat(isValid, equalTo(Boolean.FALSE));
    }

    /**
     * Test if the application return false for a cell not empty in a valid position
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Test
    public void shouldReturnFalseIfCellAtPositionIsValidAndNotEmpty() throws InvalidPositionException {
        chessBoard.getCellAt(5, 6).setVisitNumber(2);
        Boolean isValid = chessBoard.isCellValidAndEmpty(5, 6);
        assertThat(isValid, equalTo(Boolean.FALSE));
    }

    /**
     * Test if the class retrieve the specific cell at the position
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Test
    public void shouldRetrieveCellAtPosition() throws InvalidPositionException {
        Cell cell = chessBoard.getCellAt(2, 3);
        assertThat(cell.getLine(), equalTo(2));
        assertThat(cell.getColumn(), equalTo(3));
    }

    /**
     * Test if the class throws a exception when a non existent cell is tried
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Test(expected = InvalidPositionException.class)
    public void shouldThrowExceptionIfCellAtPositionDoesntExists() throws InvalidPositionException {
        chessBoard.getCellAt(11, 3);
    }


}
package piecetour.board.piece;


import org.junit.Before;
import org.junit.Test;
import piecetour.board.Cell;
import piecetour.board.exception.InvalidPositionException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test cases for the class {@link PieceTour}
 */
public class PieceTourTest {
    PieceTour pieceTour = new PieceTour();

    /**
     * Initialize the chessboard
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Before
    public void init() {
        pieceTour = new PieceTour();
    }

    /**
     * Should verify if every cell inside the board is visited for all possible start positions.
     *
     * @throws InvalidPositionException
     */
    @Test
    public void shouldVisitEveryCellInsideTheBoardForEveryValidPositionTour() throws InvalidPositionException {
        for (Integer line = 0; line < 10; line++) {
            for (Integer column = 0; column < 10; column++) {
                pieceTour.findClosedTour(3, 2);
                Cell[] cells = pieceTour.getBoard().getBoardCells();
                Set<Cell> set = new HashSet<>();
                set.addAll(Arrays.asList(cells));
                assertThat(set.size(), equalTo(100));
                assertThat(set.stream().filter(cell -> cell.getVisitNumber() < 0)
                        .findFirst().isEmpty(), equalTo(Boolean.TRUE));
                assertThat(set.stream().filter(cell -> cell.getVisitNumber() > 100)
                        .findFirst().isEmpty(), equalTo(Boolean.TRUE));
            }
        }
    }

    /**
     * Should throw an exception if a non existent position is tried.
     *
     * @throws InvalidPositionException if any position is invalid
     */
    @Test(expected = InvalidPositionException.class)
    public void shouldThrowExceptionIfInvalidPositionIsTried() throws InvalidPositionException {
        pieceTour.findClosedTour(3, -2);
    }


}

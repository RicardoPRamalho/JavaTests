package piecetour.board;

import java.util.Objects;

/**
 * It describes a single cell with its line, position and a visit number.
 *
 * @author Ricardo Pereira Ramalho
 */
public class Cell {

    /**
     * line position inside the board
     */
    private Integer line;
    /**
     * column position inside the board
     */
    private Integer column;
    /**
     * number that states when it has been visited by the piece
     */
    private Integer visitNumber;

    /**
     * Constructor that states the position in line, column and its initial visitation number
     *
     * @param line        {@link Integer} containing the position in the line
     * @param column      {@link Integer} containing the position in the column
     * @param visitNumber {@link Integer} containing the initial visitation number
     */
    public Cell(Integer line, Integer column, Integer visitNumber) {
        this.line = line;
        this.column = column;
        this.visitNumber = visitNumber;
    }

    /**
     * Getter method for the attribute {@link line}
     *
     * @return {@link Integer} the position of the cell in the board line
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Getter method for the attribute {@link column}
     *
     * @return {@link Integer} the position of the cell in the board column
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Getter method for the attribute {@link visitNumber}
     *
     * @return {@link Integer} containing the order this cell has been visited by the piece
     */
    public Integer getVisitNumber() {
        return visitNumber;
    }

    /**
     * Setter method for the attribute {@link visitNumber}
     *
     * @param visitNumber {@link Integer} containing the order this cell has been visited by the piece
     */
    public void setVisitNumber(Integer visitNumber) {
        this.visitNumber = visitNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return getLine().equals(cell.getLine()) &&
                getColumn().equals(cell.getColumn()) &&
                getVisitNumber().equals(cell.getVisitNumber());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLine(), getColumn(), getVisitNumber());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Cell{" +
                "line=" + line +
                ", column=" + column +
                ", visitNumber=" + visitNumber +
                '}';
    }
}

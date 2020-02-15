package piecetour.board.piece;

/**
 * Represents possibles movements inside the chessboard.
 *
 * @author Ricardo Pereira Ramalho
 */
public enum Movement {

    EAST(3, 0),
    SOUTHEAST(2, -2),
    SOUTH(0, -3),
    SOUTHWEST(-2, -2),
    WEST(-3, 0),
    NORTHWEST(-2, 2),
    NORTH(0, 3),
    NORTHEAST(2, 2);


    /**
     * Quantity of positions in the line the movement achieves
     */
    private Integer line;
    /**
     * Quantity of positions in the column the movement achieves
     */
    private Integer column;

    /**
     * Constructor that describes the position achieved by the movement
     *
     * @param line {@link Integer} Quantity of positions in the line the movement achieves
     * @param column {@link Integer} Quantity of positions in the column the movement achieves
     */
    Movement(Integer line, Integer column){
        this.line=line;
        this.column=column;
    }

    /**
     * Getter method for the attribute {@code line}
     *
     * @return {@link Integer} the position achieved in the board line
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Getter method for the attribute {@code column}
     *
     * @return {@link Integer} the position achieved in the board column
     */
    public Integer getColumn() {
        return column;
    }
}

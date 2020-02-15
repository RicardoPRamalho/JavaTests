package piecetour.board.piece;

public enum Movement {

    EAST(3, 0),
    SOUTHEAST(2, -2),
    SOUTH(0, -3),
    SOUTHWEST(-2, -2),
    WEST(-3, 0),
    NORTHWEST(-2, 2),
    NORTH(0, 3),
    NORTHEAST(2, 2);


    private Integer line;
    private Integer column;

    Movement(Integer line, Integer column){
        this.line=line;
        this.column=column;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}

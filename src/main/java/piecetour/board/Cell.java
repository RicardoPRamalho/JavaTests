package piecetour.board;

public class Cell {

    private Integer column;
    private Integer line;

    public Cell(Integer column, Integer line) {
        this.column = column;
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getLine() {
        return line;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
}

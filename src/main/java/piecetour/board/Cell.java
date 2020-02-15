package piecetour.board;

public class Cell {

    private Integer line;
    private Integer column;
    private Integer visitNumber;

    public Cell(Integer line, Integer column) {
        this.line = line;
        this.column = column;
    }

    public Cell(Integer line, Integer column, Integer visitNumber) {
        this.line = line;
        this.column = column;
        this.visitNumber = visitNumber;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Integer visitNumber) {
        this.visitNumber = visitNumber;
    }
}

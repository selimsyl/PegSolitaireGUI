package PegGame;

import java.util.ArrayList;

public abstract class GameBoard {
    protected ArrayList<PegCell> PegCellList;
    protected int rowSize;
    protected int columnSize;

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public ArrayList<PegCell> getPegCellList() {
        return PegCellList;
    }

    public void setPegCellList(ArrayList<PegCell> pegCellList) {
        PegCellList = pegCellList;
    }

    ArrayList<PegCell> getCellList() {return PegCellList;}
}

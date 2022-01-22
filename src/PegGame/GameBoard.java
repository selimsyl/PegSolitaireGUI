package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameBoard extends JPanel implements Serializable {
    protected ArrayList<PegCell> PegCellList = new ArrayList<>();
    protected ArrayList<Integer> unUsedPegCellListIndex = new ArrayList<>();
    protected int rowSize;
    protected int columnSize;
    protected PegCellActioner btnActioner;

    GameBoard(int rowSize, int columnSize) {
        btnActioner = new PegCellActioner();
        this.rowSize =rowSize;
        this.columnSize = columnSize;
        for (int i =0; i < rowSize*columnSize; ++i) {
            var btn = new PegCell(i,PegCell.peg);
            btn.setBackground(Color.white);
            PegCellList.add(btn);
        }
    }

    protected void initUnusedPegs() {
        for (var btn:PegCellList) {
//            if (btn.isPeg())
                btn.addActionListener(btnActioner);
        }
    }

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

    protected void calcUnusedCells(int startIdx,int rowCnt,int columnCnt,int increase) {
        for (int i=0;i<rowCnt;++i) {
            for(int k=0;k<columnCnt;++k){
                unUsedPegCellListIndex.add(startIdx + (i*increase) + k);
            }
        }
    }

    private class PegCellActioner implements ActionListener,Serializable {
        private PegCell lastClickedPegCell;

        PegCellActioner() {}

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!isCellValid(((PegCell)e.getSource()).getIndex())) {
                lastClickedPegCell = null;
                return;
            }
            if(lastClickedPegCell == null) {
                lastClickedPegCell = (PegCell) e.getSource();
            } else {
                var clickedPegCell = (PegCell) e.getSource();
                var lastIndx = lastClickedPegCell.getIndex();
                var idxDiff = clickedPegCell.getIndex() - lastIndx;
                if(isValidMove(idxDiff)){
                        var icon = (ImageIcon) clickedPegCell.getIcon();
                        if (icon.equals(PegCell.Nopeg) && PegCellList.get( lastClickedPegCell.getIndex()+idxDiff/2).getIcon().equals(PegCell.peg)) {
                            clickedPegCell.setPeg();
                            lastClickedPegCell.unsetPeg();
                            PegCellList.get( lastClickedPegCell.getIndex()+idxDiff/2).unsetPeg();
                        }
                }
                lastClickedPegCell = null;
            }
        }
    }
    boolean isCellValid(Integer idx) {
        for (var index:unUsedPegCellListIndex) {
            if (idx.equals(index))
                return false;
        }
        return true;
    }
    boolean isValidMove(int no) {
        return no==rowSize+columnSize ||
                no==-(rowSize+columnSize) ||
                no==2                 ||
                no==-2;
    }
}

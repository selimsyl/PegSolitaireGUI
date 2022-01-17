package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PegSolitaire {
    public enum BoardTypes {Tpype1,Tpype2,Tpype3,Tpype4,Tpype5,Tpype6};
    private GameBoard board;

    static class PegCellActioner implements ActionListener {
        private PegCell lastClickedPegCell;
        private ArrayList<PegCell> PegCellList;

        PegCellActioner() {}
        PegCellActioner(ArrayList<PegCell> PegCellList) {
            this.PegCellList = PegCellList;
        }
        void setPegCellList(ArrayList<PegCell> PegCellList) {
            this.PegCellList = PegCellList;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(lastClickedPegCell == null) {
                lastClickedPegCell = (PegCell) e.getSource();
            } else {
                var clickedPegCell = (PegCell) e.getSource();
                var lastIndx = lastClickedPegCell.getIndex();
                var idxDiff = clickedPegCell.getIndex() - lastIndx;
                switch (idxDiff) {
                    case +14, -14, -2, +2 -> {
                        var icon = (ImageIcon) clickedPegCell.getIcon();
                        if (icon.equals(PegCell.Nopeg) && PegCellList.get(lastClickedPegCell.getIndex()+idxDiff/2).getIcon().equals(PegCell.peg)) {
                            clickedPegCell.setPeg();
                            lastClickedPegCell.unsetPeg();
                        }
                    }
                }
                lastClickedPegCell = null;
            }
        }
    }
}

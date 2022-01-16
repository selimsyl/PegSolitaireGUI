package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PegSolitaire {

    static class Cell extends JButton {
        private int index;
        static ImageIcon peg;
        static ImageIcon Nopeg;

        Cell() {}
        Cell(int idx) {index = idx;}
        Cell(ImageIcon icon) {super(icon);}
        Cell(int x,ImageIcon icon) {super(icon);index = x;}

        int getIndex() {return index;}
        static void getImageIcons() {
            var imageIcon = new ImageIcon("/home/mib/IdeaProjects/PegSolitaire/r.jpg");
            var WhiteIcon = new ImageIcon("/home/mib/IdeaProjects/PegSolitaire/s.png");

            Image imageWhite = WhiteIcon.getImage(); // transform it
            Image newWhite = imageWhite.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            Nopeg = new ImageIcon(newWhite);  // transform it back

            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            peg = new ImageIcon(newimg);  // transform it back
        }
        void setPeg() {
            setIcon(peg);
        }
        void unsetPeg() {
            setIcon(Nopeg);
        }
    }

    static class CellActioner implements ActionListener {
        private Cell lastClickedCell;
        private ArrayList<Cell> cellList;

        CellActioner() {}
        CellActioner(ArrayList<Cell> cellList) {
            this.cellList = cellList;
        }
        void setCellList(ArrayList<Cell> cellList) {
            this.cellList = cellList;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(lastClickedCell == null) {
                lastClickedCell = (Cell) e.getSource();
            } else {
                var clickedCell = (Cell) e.getSource();
                var lastIndx = lastClickedCell.getIndex();
                var idxDiff = clickedCell.getIndex() - lastIndx;
                switch (idxDiff) {
                    case +14, -14, -2, +2 -> {
                        var icon = (ImageIcon) clickedCell.getIcon();
                        if (icon.equals(Cell.Nopeg) && cellList.get(clickedCell.getIndex()+Math.abs(idxDiff/2)).getIcon().equals(Cell.peg)) {
                            clickedCell.setPeg();
                            lastClickedCell.unsetPeg();
                        }
                    }
                }
                lastClickedCell = null;
            }
        }
    }
}

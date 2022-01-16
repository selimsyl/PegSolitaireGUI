package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PegSolitaire {

    static class Cell extends JButton {
        private int index;
        private static ImageIcon peg;
        private static ImageIcon Nopeg;

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
        private ArrayList<Cell> cellList;
        private Cell lastClickedCell;
        CellActioner() {}
        CellActioner(ArrayList<Cell> cells) {
            cellList = cells;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(lastClickedCell == null) {
                lastClickedCell = (Cell) e.getSource();
            } else {
                var clickedCell = (Cell) e.getSource();
                if (clickedCell.getIndex() == lastClickedCell.getIndex()+14) {
                    clickedCell.setPeg();
                    lastClickedCell.unsetPeg();
                } else if (clickedCell.getIndex() == lastClickedCell.getIndex()-14) {
                    clickedCell.setPeg();
                    lastClickedCell.unsetPeg();
                } else if (clickedCell.getIndex() == lastClickedCell.getIndex()-2) {
                    clickedCell.setPeg();
                    lastClickedCell.unsetPeg();
                } else if (clickedCell.getIndex() == lastClickedCell.getIndex()+2) {
                    clickedCell.setPeg();
                    lastClickedCell.unsetPeg();
                }
                lastClickedCell = null;
            }
        }
    }
}

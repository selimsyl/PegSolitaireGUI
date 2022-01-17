package PegGame;

import javax.swing.*;

public class PegCell extends JButton {
        private int index;
        static ImageIcon peg = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/r.jpg").getIcon();
        static ImageIcon Nopeg = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/s.png").getIcon();

        PegCell() {}
        PegCell(int idx) {index = idx;}
        PegCell(ImageIcon icon) {super(icon);}
        PegCell(int x,ImageIcon icon) {super(icon);index = x;}

        int getIndex() {return index;}
        void setPeg() {
            setIcon(peg);
        }
        void unsetPeg() {
            setIcon(Nopeg);
        }
}

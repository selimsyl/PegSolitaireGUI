package PegGame;

import javax.swing.*;
import java.io.Serializable;

public class PegCell extends JButton implements Serializable {
        private int index;
        static ImageIcon peg = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/r.jpg").getIcon();
        static ImageIcon Nopeg = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/s.png").getIcon();
        static ImageIcon border = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/e.jpg").getIcon();

        public PegCell() {}
        public PegCell(int idx) {index = idx;}
        public PegCell(ImageIcon icon) {super(icon);}
        public PegCell(int x,ImageIcon icon) {super(icon);index = x;}

        public int getIndex() {return index;}
        public void setPeg() {
            setIcon(peg);
        }
        public void unsetPeg() {
            setIcon(Nopeg);
        }
        public void setBorder() {setIcon(border);}
        public boolean isPeg() {return getIcon().equals(peg);}
}

package PegGame;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

public class PegCell extends JButton implements Serializable {
  private int index;
  private CellType type;
  static ImageIcon peg = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/r.jpg").getIcon();
  static ImageIcon Nopeg = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/s.png").getIcon();
  static ImageIcon border = new ImageToIcon("/home/mib/IdeaProjects/PegSolitaire/e.jpg").getIcon();

  public PegCell(int x, ImageIcon icon) {
    super(icon);
    index = x;
    type = icon == peg ? CellType.PEG : CellType.NOPEG;
  }

  public int getIndex() {
    return index;
  }

  public void setPeg() {
    setIcon(peg);
    type = CellType.PEG;
  }

  public void unsetPeg() {
    setIcon(Nopeg);
    type = CellType.NOPEG;
  }

  public boolean isPeg() {
    return type == CellType.PEG;
  }

  public void setUnused() {
    this.setIcon((null));
    type = CellType.UNUSED;
  }

  private enum CellType implements Serializable {
    PEG, NOPEG, UNUSED
  }
}

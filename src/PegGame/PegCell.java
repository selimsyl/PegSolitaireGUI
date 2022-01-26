package PegGame;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Board, Peg cell object class
 */
public class PegCell extends JButton implements Serializable {
  /**
   * Each cell have unique index on board
   */
  private int index;
  /**
   * Cell Type PEG,NOPEG,UNUSED
   */
  private CellType type;
  /**
   * Icon image path
   */
  private static final String imageFolder = System.getProperty("user.dir")+"/iconImages/";
  /**
   * Icon for Peg cells objects
   */
  static ImageIcon peg = new ImageToIcon(imageFolder+"r.jpg").getIcon();
  /**
   * Icon for no peg cells objects
   */
  static ImageIcon Nopeg = new ImageToIcon(imageFolder+"s.png").getIcon();

  /**
   * @param x Cell unique index no
   * @param icon Cell icon peg,Nopeg
   */
  public PegCell(int x, ImageIcon icon) {
    super(icon);
    index = x;
    type = icon == peg ? CellType.PEG : CellType.NOPEG;
  }

  /**
   * @return Cell index no
   */
  public int getIndex() {
    return index;
  }

  /**
   * Set cell icon to peg
   */
  public void setPeg() {
    setIcon(peg);
    type = CellType.PEG;
  }

  /**
   * Toggle cell icon to peg or nopeg
   */
  public void togglePeg() {
    if(type == CellType.PEG)
      unsetPeg();
    else
      setPeg();
  }

  /**
   * Set cell icon to Nopeg
   */
  public void unsetPeg() {
    setIcon(Nopeg);
    type = CellType.NOPEG;
  }

  /**
   * @return True if cell is a peg
   */
  public boolean isPeg() {
    return type == CellType.PEG;
  }

  /**
   * @return True if cell is a Nopeg
   */
  public boolean isNoPeg() {
    return type == CellType.NOPEG;
  }

  /**
   * Unused cell's icon set to null
   */
  public void setUnused() {
    this.setIcon((null));
    type = CellType.UNUSED;
  }

  /**
   * Cell Type enumarators
   */
  private enum CellType implements Serializable {
    /**
     * Indicates cell is a peg
     */
    PEG,
    /**
     * Indicates cell is a Nopeg
     */
    NOPEG,
    /**
     * Indicates cell is not used board
     */
    UNUSED
  }
}

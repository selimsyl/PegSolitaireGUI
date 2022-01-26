package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Game Board used as panel for JButton Cells
 */
public class GameBoard extends JPanel implements Serializable {

    /**
     * @param board Specific board type
     */
    GameBoard(Board.BoardBase board) {
        super(new GridLayout(board.rowSize,board.columnSize));
        this.board = board;
        btnActioner = new PegCellActioner();
        for (var btn:board.getPegCellList()) {
            btn.addActionListener(btnActioner);
        }

        for (var btn : board.getPegCellList()) {
            add(btn);
        }
    }

    /**
     * @return True if there is no more cell to be moved
     */
    boolean isGameFinished() {
        return board.isGameFinished();
    }

    /**
     * Undo last move
     */
    void undo() {
        for (int i=0;i<undoArr.length;++i)
            undoArr[i].togglePeg();
    }

    /**
     * Action listener class for JButton Cells
     */
    private class PegCellActioner implements ActionListener,Serializable {
        /**
         * Reference to first clicked JButton cell
         */
        private PegCell lastClickedPegCell;

        /**
         * @param e JButton Cell objects handler
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!board.isCellValid(((PegCell)e.getSource()).getIndex())) {
                lastClickedPegCell = null;
                return;
            }
            if(lastClickedPegCell == null) {
                if (((PegCell) e.getSource()).isPeg())
                    lastClickedPegCell = (PegCell) e.getSource();
            } else {
                var clickedPegCell = (PegCell) e.getSource();

                if (clickedPegCell.isPeg()) {
                    lastClickedPegCell = null;
                    return;
                }

                var lastIndx = lastClickedPegCell.getIndex();
                var idxDiff = clickedPegCell.getIndex() - lastIndx;

                if(board.isValidMove(idxDiff)){
                    var midCell = board.getCell( lastClickedPegCell.getIndex()+idxDiff/2);
                    if (midCell.isPeg()) {
                        clickedPegCell.setPeg();
                        lastClickedPegCell.unsetPeg();
                        midCell.unsetPeg();

                        undoArr[0] = clickedPegCell;
                        undoArr[1] = lastClickedPegCell;
                        undoArr[2] = midCell;
                    }
                    if(isGameFinished()) {
                        JOptionPane.showMessageDialog(null,"GAME IS FINISHED");
                    }
                }
                lastClickedPegCell = null;
            }
        }
    }


    /**
     * ActionListener handler
     */
    protected PegCellActioner btnActioner;
    /**
     * Specific board type
     */
    protected Board.BoardBase board;
    /**
     * Keep last move in this array to be able to undo
     */
    private final PegCell[] undoArr = new PegCell[3];

}

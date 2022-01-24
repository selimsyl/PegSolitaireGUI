package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class GameBoard extends JPanel implements Serializable {

    protected PegCellActioner btnActioner;
    protected Board.BoardBase board;

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

    boolean isGameFinished() {
        return board.isGameFinished();
    }

    private class PegCellActioner implements ActionListener,Serializable {
        private PegCell lastClickedPegCell;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!board.isCellValid(((PegCell)e.getSource()).getIndex())) {
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
                    }
                    if(isGameFinished()) {
                        JOptionPane.showMessageDialog(null,"GAME IS FINISHED");
                    }
                }
                lastClickedPegCell = null;
            }
        }
    }


}

package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;

public class PegSolitaire implements Serializable {
    private GameBoard board;
    private JFrame frame;
    private JPanel boardPanel,radioPanel,controlButtonPanel;
    private ArrayList<JButton> jButtons;
    private ArrayList<JRadioButton> jRadioButtons;

    PegSolitaire(GameBoard board) {
        this.board = board;
        initGameWindow();
    }

    public void run() {
        frame.setVisible(true);
    }

    public void setBoard(GameBoard board) {
        this.board = board;
        initGameWindow();
    }

    private void closeFrame() {frame.dispose();}

    private void saveGame() {
        FileOutputStream f;
        ObjectOutputStream o;
        try {
            f = new FileOutputStream(new File("SavedGame.txt"));
            o = new ObjectOutputStream(f);
            o.writeObject(board);
            o.close();
            f.close();
        } catch (IOException e) {
            System.out.println("Error while saving...");
        }
    }

    private void loadGame() {
        FileInputStream fi;
        ObjectInputStream oi;
        try {
         fi = new FileInputStream(new File("SavedGame.txt"));
         oi = new ObjectInputStream(fi);
            return (GameBoard) oi.readObject();
        } catch (IOException e) {
            System.out.println("Error while loading...");
        } catch (ClassNotFoundException e) {
            System.out.println("Internal error, inform everyone including USA President");
        }
        return null;
    }

    private void initGameWindow() {
        initButtonsPanel();
        initBoardPanel();
        initMainPanel();
    }

    private void initMainPanel() {
        frame = new JFrame();
        var mainLayout = new BorderLayout(2,0);
        frame.setLayout(mainLayout);
        frame.setSize(750,750);
        frame.add(radioPanel,BorderLayout.PAGE_START);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(controlButtonPanel,BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initBoardPanel() {
        boardPanel = new JPanel(new GridLayout(board.getRowSize(),board.getColumnSize()));

        for (var btn : board.getPegCellList()) {
            boardPanel.add(btn);
        }
    }

    private void initButtonsPanel() {
        controlButtonPanel = new JPanel(new FlowLayout());
        var btnActionListener = new GameButtonListener();
        for (var btnName:new String[]{"Reset","Load","Save","Undo"}) {
            var bnt = new JButton(btnName);
            jButtons.add(bnt);
            bnt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    savetoFile();
                }
            });
            controlButtonPanel.add(bnt);
        }


        radioPanel = new JPanel(new FlowLayout());
        var radioButtonGroup = new ButtonGroup();
        var btnItemListener = new GameRadioButtonListener();
        for (var btnName:new String[]{"Bord-Type-1","Bord-Type-2","Bord-Type-3",
                                        "Bord-Type-4","Bord-Type-5","Bord-Type-6"}) {
            var btn = new JRadioButton(btnName);
            btn.addItemListener(btnItemListener);
            radioButtonGroup.add(btn);
            radioPanel.add(btn);
        }
    }

    private class GameButtonListener implements ActionListener,Serializable {

        @Override
        public void actionPerformed(ActionEvent e) {
            var btn = (JButton)e.getSource();
            switch (btn.getName())
            {
                case "Reset" -> {
                }
                case "Load" -> {
                }
                case "Save" -> {
                }
                case "Undo" -> {
                }
            }
        }
    }

    private class GameRadioButtonListener implements ItemListener,Serializable {

        @Override
        public void itemStateChanged(ItemEvent e) {
            var btn = (JRadioButton)e.getSource();
            switch (btn.getName())
            {
                case "Bord-Type-1" :
                    break;
                case "Bord-Type-2" :
                    break;
                case "Bord-Type-3" :
                    break;
                case "Bord-Type-4" :
                    break;
                case "Bord-Type-5" :
                    break;
                case "Bord-Type-6" :
                    break;
            }
        }
    }
}
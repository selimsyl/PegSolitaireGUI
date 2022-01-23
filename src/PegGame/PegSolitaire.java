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
    private JFrame frame;
    private GameBoard boardPanel;
    private JPanel radioPanel = new JPanel(new FlowLayout()),
            controlButtonPanel = new JPanel(new FlowLayout());
    private ArrayList<JButton> jButtons = new ArrayList<>();
    private ArrayList<JRadioButton> jRadioButtons = new ArrayList<>();

    PegSolitaire() {
        initGameWindow();
    }

    public void run() {
        frame.setVisible(true);
    }

    public void setBoard(GameBoard board) {
        initGameWindow();
    }

    private void closeFrame() {frame.dispose();}

    private void saveGame() {
        FileOutputStream f;
        ObjectOutputStream o;
        try {
            f = new FileOutputStream(new File("SavedGame.txt"));
            o = new ObjectOutputStream(f);
            o.writeObject(boardPanel);
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
            this.boardPanel = (GameBoard) oi.readObject();
        } catch (IOException e) {
            System.out.println("Error while loading...");
        } catch (ClassNotFoundException e) {
            System.out.println("Internal error, inform everyone including USA President");
        }
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
        var board = new BoardFact().makeBoard(BoardFact.BoardTypes.Tpype1);
        boardPanel = new GameBoard(board);
    }

    private void addcontrolButton(String name,ActionListener actioner) {
        var btn = new JButton(name);
        btn.addActionListener(actioner);
        jButtons.add(btn);
        controlButtonPanel.add(btn);
    }

    private void resetGame() {

    }

    private void undo() {

    }

    private void initButtonsPanel() {
        addcontrolButton("Reset", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        addcontrolButton("Load", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        addcontrolButton("Save", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });
        addcontrolButton("Undo", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

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
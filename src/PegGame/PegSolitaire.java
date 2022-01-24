package PegGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static java.awt.event.ItemEvent.SELECTED;

public class PegSolitaire implements Serializable {
    private JFrame frame;
    private GameBoard boardPanel;
    private JPanel radioPanel = new JPanel(new FlowLayout()),
            controlButtonPanel = new JPanel(new FlowLayout());
    private ArrayList<JRadioButton> jRadioButtons = new ArrayList<>();
    private Map<Integer,GameBoard> gameBoardTypeMap;
    private Integer currentSelectedRadioButtonIdx;

    PegSolitaire() {
        initGameWindow();
    }

    public void run() {
        frame.setVisible(true);
//        jRadioButtons.get(currentSelectedRadioButtonIdx).setSelected(true);
    }

    public void setBoard() {
//        frame.remove(boardPanel);
        boardPanel = gameBoardTypeMap.get(currentSelectedRadioButtonIdx);
    }

    private void closeFrame() {frame.dispose();}

    private void saveGame() {
        FileOutputStream f;
        ObjectOutputStream o;
        try {
            f = new FileOutputStream("SavedGame.txt");
            o = new ObjectOutputStream(f);
            o.writeObject(boardPanel);
            o.writeObject(currentSelectedRadioButtonIdx);
            boardPanel.repaint();
            o.close();
            f.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error while saving...");
        }
    }

    private void refreshGame() {
        closeFrame();
        setBoard();
        initMainPanel();
        run();
    }

    private void loadGame() {
        loadGameBoard();
        refreshGame();
    }

    private void loadGameBoard() {
        FileInputStream fi;
        ObjectInputStream oi;
        try {
         fi = new FileInputStream("SavedGame.txt");
         oi = new ObjectInputStream(fi);
            boardPanel = (GameBoard) oi.readObject();
            currentSelectedRadioButtonIdx = (Integer) oi.readObject();
            gameBoardTypeMap.put(currentSelectedRadioButtonIdx,boardPanel);
            jRadioButtons.get(currentSelectedRadioButtonIdx).setSelected(true);
//            return loadBoard;
        } catch (IOException e) {
            System.out.println("Error while loading...");
        } catch (ClassNotFoundException e) {
            System.out.println("Internal error, inform everyone including USA President");
        }
//        return null;
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
        gameBoardTypeMap = new TreeMap<>();
        gameBoardTypeMap.put(0,new GameBoard(BoardFact.makeBoard(0)));
        gameBoardTypeMap.put(1,new GameBoard(BoardFact.makeBoard(1)));
        gameBoardTypeMap.put(2,new GameBoard(BoardFact.makeBoard(2)));
        gameBoardTypeMap.put(3,new GameBoard(BoardFact.makeBoard(3)));
        gameBoardTypeMap.put(4,new GameBoard(BoardFact.makeBoard(4)));
        gameBoardTypeMap.put(5,new GameBoard(BoardFact.makeBoard(5)));
        boardPanel = gameBoardTypeMap.get(0);
        currentSelectedRadioButtonIdx = 0;
    }

    private void addcontrolButton(String name,ActionListener actioner) {
        var btn = new JButton(name);
        btn.addActionListener(actioner);
        controlButtonPanel.add(btn);
    }

    private void resetGame() {
        var btype = new GameBoard(BoardFact.makeBoard(currentSelectedRadioButtonIdx));
        gameBoardTypeMap.put(currentSelectedRadioButtonIdx,btype);
        refreshGame();
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
            if (btnName.equals("Bord-Type-1")) btn.setSelected(true);
            btn.addItemListener(btnItemListener);
            radioButtonGroup.add(btn);
            radioPanel.add(btn);
            jRadioButtons.add(btn);
        }
    }

    private class GameRadioButtonListener implements ItemListener,Serializable {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == SELECTED) {
                var btn = (JRadioButton) e.getSource();
                for (int i = 0; i < jRadioButtons.size(); ++i) {
                    if (jRadioButtons.get(i).equals(btn)) {
                        currentSelectedRadioButtonIdx = i;
                        refreshGame();
                        break;
                    }
                }
            }
        }
    }
}
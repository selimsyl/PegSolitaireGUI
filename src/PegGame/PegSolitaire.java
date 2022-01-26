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

/**
 * Controls game flow
 */
public class PegSolitaire implements Serializable {
    /**
     * Main frame includes all game related gui objects
     */
    private JFrame frame;
    /**
     * Game panel includes JButton cells
     */
    private GameBoard boardPanel;
    /**
     * RadiButton panel includes radio panels
     */
    private JPanel radioPanel = new JPanel(new FlowLayout()),
    /**
     * ControlButton includes control buttons
     */
            controlButtonPanel = new JPanel(new FlowLayout());
    /**
     * RadiButton list
     */
    private ArrayList<JRadioButton> jRadioButtons = new ArrayList<>();
    /**
     * Each board type can be played in same frame, to provide this
     * all boards are created in the begining of game and switching done
     * by radio buttons
     */
    private Map<Integer,GameBoard> gameBoardTypeMap;
    /**
     * Current played board tpye index
     */
    private Integer currentSelectedRadioButtonIdx;

    /**
     * Game save path
     */
    private final String gameSavePath = System.getProperty("user.dir")+"/saveFolder/SavedGame.txt";
    /**
     * No param ctor
     */
    PegSolitaire() {
        initGameWindow();
    }

    /**
     * Set frame to visible
     */
    public void run() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Set current board type
     */
    private void setBoard() {
        boardPanel = gameBoardTypeMap.get(currentSelectedRadioButtonIdx);
    }

    /**
     * Close current frame
     */
    private void closeFrame() {frame.dispose();}

    /**
     * Save current board and its index to "SavedGame.txt" file
     */
    private void saveGame() {
        try {
            var f = new FileOutputStream(gameSavePath);
            var o = new ObjectOutputStream(f);
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

    /**
     * Refresh main frame
     */
    private void refreshGame() {
        closeFrame();
        setBoard();
        initMainPanel();
        run();
    }

    /**
     * Load and refresh game from "SavedGame.txt" file
     */
    private void loadGame() {
        loadGameBoard();
        refreshGame();
    }

    /**
     * Reads saved file and loads game
     */
    private void loadGameBoard() {
        try {
         var fi = new FileInputStream(gameSavePath);
         var oi = new ObjectInputStream(fi);
            boardPanel = (GameBoard) oi.readObject();
            currentSelectedRadioButtonIdx = (Integer) oi.readObject();
            gameBoardTypeMap.put(currentSelectedRadioButtonIdx,boardPanel);
            jRadioButtons.get(currentSelectedRadioButtonIdx).setSelected(true);
        } catch (IOException e) {
            System.out.println("Error while loading...");
        } catch (ClassNotFoundException e) {
            System.out.println("Internal error, inform everyone including USA President");
        }
    }

    /**
     * Initializes panels and main frame
     */
    private void initGameWindow() {
        initButtonsPanel();
        initBoardPanel();
        initMainPanel();
    }

    /**
     * Create main frame and add gui objects
     */
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

    /**
     * Initialize all board types and add them to map
     * to be used when switching between boards by
     * radio buttons
     */
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

    /**
     * @param name Button name
     * @param actioner Button action listener
     */
    private void addcontrolButton(String name,ActionListener actioner) {
        var btn = new JButton(name);
        btn.addActionListener(actioner);
        controlButtonPanel.add(btn);
    }

    /**
     * Reset current board
     */
    private void resetGame() {
        var btype = new GameBoard(BoardFact.makeBoard(currentSelectedRadioButtonIdx));
        gameBoardTypeMap.put(currentSelectedRadioButtonIdx,btype);
        refreshGame();
    }

    /**
     * Undo last move
     */
    private void undo() {
        boardPanel.undo();
    }

    /**
     * Initializes control buttons and radio buttons
     */
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

    /**
     * Radio Button aciton listener
     */
    private class GameRadioButtonListener implements ItemListener,Serializable {

        /**
         * @param e RadioButton action handler
         */
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
package PegGame;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
	// write your code here
        var frame = new JFrame();
        var grid = new BorderLayout(2,0);
        frame.setLayout(grid);
        frame.setSize(500,500);
//        panel.setSize(10,10);

        var panel = new JPanel(new GridLayout(7,7));

//        PegSolitaire.Cell.getImageIcons();

        ArrayList<PegCell> cList = new ArrayList<>();
        for (int i =0; i < 49; ++i) {
            var btn = new PegCell(i,PegCell.peg);
            btn.setBackground(Color.white);
            cList.add(btn);
        }

        PegSolitaire.PegCellActioner btnListener = new PegSolitaire.PegCellActioner(cList);

        for (var btn : cList) {
            btn.addActionListener(btnListener);
            panel.add(btn);
        }

        //Set white, red
        var btn = (JButton)panel.getComponent(0);
//        btn.setIcon(WhiteIcon);
        ((JButton)panel.getComponent(1)).setIcon(PegCell.Nopeg);
        ((JButton)panel.getComponent(8)).setIcon(PegCell.Nopeg);
//        ((JButton)panel.getComponent(15)).setIcon(WhiteIcon);
        ((JButton)panel.getComponent(22)).setIcon(PegCell.Nopeg);


        var panelC = new JPanel(new FlowLayout());
        panelC.add(new JButton("Reset"));
        panelC.add(new JButton("Load"));
        panelC.add(new JButton("Save"));
        panelC.add(new JButton("Undo"));
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panelC,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

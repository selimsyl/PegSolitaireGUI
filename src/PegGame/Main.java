package PegGame;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
	// write your code here
        var frame = new JFrame();
        var grid = new BorderLayout(2,0);
        frame.setLayout(grid);
        frame.setSize(500,500);
//        panel.setSize(10,10);

        var imageIcon = new ImageIcon("/home/mib/IdeaProjects/PegSolitaire/r.jpg");
        var WhiteIcon = new ImageIcon("/home/mib/IdeaProjects/PegSolitaire/s.png");

        Image imageWhite = WhiteIcon.getImage(); // transform it
        Image newWhite = imageWhite.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        WhiteIcon = new ImageIcon(newWhite);  // transform it back

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        var panel = new JPanel(new GridLayout(7,7));
        PegSolitaire.CellActioner btnListener = new PegSolitaire.CellActioner();
        PegSolitaire.Cell.getImageIcons();
        for (int i =0; i < 49; ++i) {
            JButton btn = new PegSolitaire.Cell(i,imageIcon);
            btn.addActionListener(btnListener);
//            btn.setPreferredSize(new Dimension(80, 80));
            btn.setBackground(Color.white);
            panel.add("B"+i,btn);
        }

        //Set white, red
        var btn = (JButton)panel.getComponent(0);

//        btn.setIcon(WhiteIcon);
//        ((JButton)panel.getComponent(1)).setIcon(WhiteIcon);
        ((JButton)panel.getComponent(8)).setIcon(WhiteIcon);
//        ((JButton)panel.getComponent(15)).setIcon(WhiteIcon);
        ((JButton)panel.getComponent(22)).setIcon(WhiteIcon);


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

package edu.chl.ChalmersRisk.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Malin on 2015-04-17.
 *
 *StartScreen for the game. Can be further developed in numerous ways.
 */
public class StartScreen extends JFrame{

    private JButton start;

    public StartScreen(){
        start = new JButton("New game");

        setLayout(new GridLayout(2,2));
        add(start);
        pack();
    }

    public JButton getStartButton(){
        return start;
    }

}

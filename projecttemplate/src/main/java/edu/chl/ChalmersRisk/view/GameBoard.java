package edu.chl.ChalmersRisk.view;

import edu.chl.ChalmersRisk.model.*;


import javax.swing.*;
import java.awt.*;

/**
 * Created by rutanjr on 2015-03-31.
 * A board for the risk game. This class will do the graphical work, holding the map and painting/repainting it.
 */
public class GameBoard extends JFrame{

    private Continent[] continents;

    //either this or make the continents themselves be JButtons or something similar.
    private JButton[] herp;

    public GameBoard(int nmbrOfContinents){

        super("Chalmers RISK!");

        continents = new Continent[nmbrOfContinents];
        herp = new JButton[nmbrOfContinents];


        final GridLayout layout = new GridLayout(2, 2);
        setLayout(layout);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //for now, most likely change later
        for(int i = 0; i < continents.length; i++){
            herp[i] = new JButton(continents[i].getName());
            add(herp[i]);
        }


        pack();
    }



}

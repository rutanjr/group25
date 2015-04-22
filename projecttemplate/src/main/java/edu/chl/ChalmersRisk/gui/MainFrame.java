package edu.chl.ChalmersRisk.gui;



import java.awt.CardLayout;

import javax.swing.JFrame;

/**
 * Created by Malin on 2015-04-21.
 */
    @SuppressWarnings("serial")
    public class MainFrame extends JFrame{

        public MainFrame() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setSize(914, 600);
            this.setLayout(new CardLayout());

        }

}

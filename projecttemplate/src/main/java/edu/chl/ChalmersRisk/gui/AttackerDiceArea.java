package edu.chl.ChalmersRisk.gui;

import javafx.scene.layout.GridPane;

/**
 * Created by Malin on 2015-05-30.
 */
public class AttackerDiceArea extends GridPane{

    private DiceView [] attackerDice;


    public AttackerDiceArea(int size){
        attackerDice = new DiceView[size];

        for(int i = 0; i < size; i++){
            attackerDice[i] = new DiceView();
            this.add(attackerDice[i],i+1,1);
        }
    }

}

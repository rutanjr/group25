package edu.chl.ChalmersRisk.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-30.
 */
public class DefenderDiceArea extends GridPane {


    private DiceView [] defenderDice;


    public DefenderDiceArea(int size){
        defenderDice = new DiceView[size];

        for(int i = 0; i < size; i++){
            defenderDice[i] = new DiceView();
            this.add(defenderDice[i],i+1,2);
        }


        Text text = new Text("Defender's dice");
        this.add(text,1,1);
    }


}

package edu.chl.ChalmersRisk.gui;

import edu.chl.ChalmersRisk.utilities.BubbleSort;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Malin on 2015-05-30.
 *
 *
 * This class represents an area that displays the dice being thrown when a player attacks another territory.
 */
public class DiceArea extends Pane {


    private final DiceView [] dice;

    /**
     * Class constructor
     * @param size the amount of dice to display in the ara
     * @param string the text above the dice
     */
    public DiceArea(int size, String string){
        dice = new DiceView[size];

        //place all the dice in the area
        for(int i = 0; i < size; i++){
            dice[i] = new DiceView();

            dice[i].setLayoutX(0+(i*47));
            dice[i].setLayoutY(5);
            this.getChildren().add(dice[i]);
        }

        //place the dietext
        Text text = new Text(string);
        text.setLayoutX(0);
        text.setLayoutY(0);
        text.setFont(Font.font("Verdana", 12));
        text.setFill(Color.valueOf("#D1E4FF"));
        this.getChildren().add(text);
    }

    /**
     * Method to set the dice pictures. Before setting them we sort the rolls so it's easier for the player
     * to see which dice competed against each other.
     * @param rolls the array of values that the dice pictures are set after.
     */
    public void setDice(int [] rolls){
        //sort the rolls, simple bubblesort: the amount of rolls are never more than 3 anyways
        rolls = BubbleSort.sortInt(rolls);

        for(int i = 0 ; i <rolls.length;i++){
            dice[i].setPicture(rolls[i]);
        }
    }


}

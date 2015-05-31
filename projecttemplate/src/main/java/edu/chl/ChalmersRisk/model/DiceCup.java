package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-05-07.
 *
 * @revisedBy Malin Thelin
 */
public class DiceCup {
    private final Die die;

    public DiceCup() {
        die = new Die();
    }

    /**
     * Rolls a die a number of times and returns the result.
     * @param nbrOfRolls
     * @return rolls of the die.
     */
    public int[] rollDice(int nbrOfRolls, boolean attackRoll){
        int[] rolls;

        //this initialization is done this way so that the rest of the array is filled with zeros, which in turn will
        //display an empty picture on the GameBoard, replacing any other picture before
        if(attackRoll){
            rolls = new int[3];
        }else{
            rolls = new int[2];
        }

        for(int i = 0; i<nbrOfRolls ; i++) {
            rolls[i] = die.rollDie();
        }
        return rolls;
    }

}

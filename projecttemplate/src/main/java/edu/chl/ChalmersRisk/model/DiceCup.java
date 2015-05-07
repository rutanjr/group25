package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-05-07.
 */
public class DiceCup {
    private Die die;

    public DiceCup() {
        die = new Die();
    }

    /**
     * Rolls a die a number of times and returns the resault.
     * @param nbrOfRolls
     * @return rolls of the die.
     */
    public int[] rollDice(int nbrOfRolls){
        //TODO  make the code.
        int[] rolls = new int[nbrOfRolls];
        for(int i = 0; i<nbrOfRolls ; i++) {
            rolls[i] = die.rollDie();
        }
        return rolls;
    }

}

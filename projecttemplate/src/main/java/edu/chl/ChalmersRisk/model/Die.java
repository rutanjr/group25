package edu.chl.ChalmersRisk.model;

import java.util.Random;

/**
 * Created by rutanjr on 2015-03-31.
 * For the dice rolls.
 */
public class Die {



    private static Random dieRoll;

    public Die() {
        if(dieRoll == null) {
            dieRoll = new Random();
        }
    }

    /**
     * Throws a die and returns the result as an integer.
     * @return die roll 1-6.
     */
    public int rollDie() {
        return  dieRoll.nextInt(6)+1;
    }

}

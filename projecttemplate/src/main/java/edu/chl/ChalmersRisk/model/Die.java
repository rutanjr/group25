package edu.chl.ChalmersRisk.model;

import java.util.Random;

/**
 * Created by rutanjr on 2015-03-31.
 * For the dice rolls.
 */
public class Die {


    //TODO throw one die per instance. DieCup.
    private static Random dieRoll;

    public Die() {
        if(dieRoll == null) {
            dieRoll = new Random();
        }
    }

    public int rollDie() {
        return  dieRoll.nextInt(6)+1;
    }

}

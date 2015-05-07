package edu.chl.ChalmersRisk.model;

import java.util.Random;

/**
 * Created by rutanjr on 2015-03-31.
 * For the dice rolls.
 */
public class Dice {


    //TODO throw one die per instance. DieCup.
    private Random dieRoll;
    private int[] lastRolls; //"remembers" the last rolls.

    public Dice() {
        dieRoll = new Random();
    }

    public int rollDie() {
        int roll = dieRoll.nextInt(6)+1;
        lastRolls = new int[] {roll};
        return roll;
    }

    public int[] rollTwoDice() {
        int first = dieRoll.nextInt(6)+1;
        int second = dieRoll.nextInt(6)+1;
        lastRolls = new int[]{first, second};
        return lastRolls;
    }

    public int[] rollThreeDice() {
        int first = dieRoll.nextInt(6)+1;
        int second = dieRoll.nextInt(6)+1;
        int third = dieRoll.nextInt(6)+1;
        lastRolls =  new int[]{first, second, third};
        return lastRolls;
    }
}

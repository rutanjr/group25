package edu.chl.ChalmersRisk.model;

import java.util.Random;

/**
 * Created by rutanjr on 2015-03-31.
 * For the dice rolls.
 */
public class Dice {

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
        int first = dieRoll.nextInt();
        int second = dieRoll.nextInt();
        lastRolls = new int[]{first, second};
        return lastRolls;
    }

    public int[] rollThreeDice() {
        int first = dieRoll.nextInt();
        int second = dieRoll.nextInt();
        int third = dieRoll.nextInt();
        lastRolls =  new int[]{first, second, third};
        return lastRolls;
    }
}

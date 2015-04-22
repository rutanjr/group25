package edu.chl.ChalmersRisk.controller;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.model.Dice;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 */
public class ChalmersRisk {


    //TODO doCombat() - what should this method take?
    public void combat(Territory attacker, Territory defender){
        Dice die = new Dice();
        int atkRoll = 0;
        int defRoll = 0;
        //Attacker selects a number of dice <= #troops - 1 and 3
        int AtkTroops = 3;    //attacker.getNbrOfTroops() - 1;
        if (AtkTroops<1) return; //No troops attacking.

        if (AtkTroops>3) AtkTroops = 3;

        //Attacker selects its highest die
        for (int i=0; i<AtkTroops; i++){
            int j = die.rollDie();
            if (j>atkRoll) atkRoll = j;
        }

        //Defender gets two die if #troops <= 2, #troops = 1 gives 1 die.
        int DefTroops = 2; //defender.getNbrOfTroops();
        if (DefTroops>2) DefTroops = 2;

        //Defender selects its highest die
        for (int i=0; i<DefTroops; i++){
            int j = die.rollDie();
            if (j>defRoll) defRoll = j;
        }

        //if the attacker's die is higher than the defender's then defender loses a troop
        //else attacker loses a troop
        if (atkRoll > defRoll){
            //Defender lose a troop;
        }else{
            //Attacker lose a troop;
        }
    }
}

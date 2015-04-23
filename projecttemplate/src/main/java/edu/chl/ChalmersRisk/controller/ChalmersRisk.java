package edu.chl.ChalmersRisk.controller;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.model.Dice;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 */
public class ChalmersRisk {



    /**
     * A method for resolving combat.
     * @param attacker the Territory that the attacking troops comes from.
     * @param defender the Territory that is being defended.
     */
    public static boolean combat(Territory attacker, Territory defender){
        Dice die = new Dice();
        int[] atkRoll;
        int[] defRoll;
        //Attacker selects a number of dice <= #troops - 1 and 3
        //TODO remove hardcoded value;
        int atkTroops = 3;    //attacker.getNbrOfTroops() - 1;

        //TODO throw exception instead?
        if (atkTroops<1) return false; //No troops attacking.

        if (atkTroops >3) atkTroops = 3;

        //Defender gets two die if #troops <= 2, #troops = 1 gives 1 die.
        //TODO Remove hardcoded value;
        int defTroops = 2; //defender.getNbrOfTroops()
        if (defTroops>2) defTroops = 2;

        //Creating attacker's die array.
        if(atkTroops>=3){
            atkRoll = die.rollThreeDice();
        }
        else if (atkTroops==2){
            atkRoll = die.rollTwoDice();
        } else {
            atkRoll = new int[die.rollDie()];
        }


        //Creating defender's die array.
        if (defTroops>=2){
            defRoll = die.rollTwoDice();
            System.out.println(defRoll[0]);
        } else {
            defRoll = new int[die.rollDie()];
        }

        while (atkTroops > 0 && defTroops >0){
            int atkHighest = 0;
            for (int i=0;i<atkTroops;i++){
                if (atkRoll[i]>atkRoll[atkHighest]){

                    atkHighest = i;
                }
            }

            int defHighest = 0;
            for (int i=0;i<defTroops;i++){
                if (defRoll[i]>defRoll[defHighest]){
                    defHighest = i;
                }
            }

            //if the attacker's die is higher than the defender's then defender loses a troop
            //else attacker loses a troop
            if (atkRoll[atkHighest] > defRoll[defHighest]){
                //Defender lose a troop;
                System.out.println("Defender lose a troop.");
                //TODO Call method for removing troop.
            }else{
                //Attacker lose a troop;
                System.out.println("Attacker lose a troop.");
                //TODO Call method for removing troop.
            }

            //Remove die roll from pool.
            atkRoll[atkHighest] = 0;
            defRoll[defHighest] = 0;

            atkTroops--;
            defTroops--;
        }

        //TODO add if defending territory has lost all its troops return true
        return false;
    }
}

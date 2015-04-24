package edu.chl.ChalmersRisk.controller;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.model.Dice;

import edu.chl.ChalmersRisk.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 * @revisedBy malin thelin
 */
public class ChalmersRisk implements KeyListener, ActionListener {

    //all variables for the map
    private Maps map;
    private Continent continents[];
    private Territory territories[];

    private Player one,two,currentPlayer;
    private int phase, oldPhase;

    //final variables defining the last and first phases. Meaning that
    //the last phase should be the one before a player ends their turn, and the first
    //phase is the first phase a player is in when a turn begins.
    private final int lastPhase = 1;
    private final int firstPhase = 1;

    private Timer gameTimer,phaseTimer;

    //TODO doCombat() - what should this method take?

    public ChalmersRisk(JFrame mainFrame){
        one = new Player("Lol");
        two = new Player("plz");

        currentPlayer = one;
        //phase 1=place troops 2=move
        phase = 1;

        //TODO , some kindof loadMap() method ??
        loadMap("Chalmers");

        startGame(one, two, mainFrame);
    }


    public void startGame(Player one, Player two, JFrame mainFrame){

        //initialize timers
        gameTimer = new Timer(10,this);
        phaseTimer = new Timer(10,new PhaseTimeActionEvent());

        gameTimer.start();
        System.out.println("Nytt spel startat! Mellan spelare " + one.getName() + " och " + two.getName());

        //give currentPlayer their first troops.
        // TODO : this looks awful. Maybe rethink how a player owns his troops etc?
        giveTroops(currentPlayer);


        //set up the mainframe, adds keylistener to the game
        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(this);
        mainFrame.setVisible(true);
        mainFrame.setSize(1, 1);
    }

    public void giveTroops(Player player){

        ArrayList<Troop> troops = new ArrayList<>(nbrOfTroopsToGive(player) + 2);
        for(int i = 0; i< nbrOfTroopsToGive(player) + 2 ; i++){
            troops.add(new Troop(player));
        }
        player.receiveTroops(troops);
    }

    public int nbrOfTroopsToGive(Player player) {
        int total;
        total = player.getnmbrOfTerritories();
        //TODO add continent bonuses.
        return total;
    }


    //in case we want to use different maps
    public void loadMap(String name){

        if(name.equals("Chalmers")){
            map = new ChalmersMap();
        }

        //this code will always be done last
        territories = map.getTerritories();
        continents = map.getContinents();


    }





    //basically all of this code is void and should be moved to somekindof mouselistener/actionlistener later
    @Override
    public void keyPressed(KeyEvent evt) {
        if(phaseTimer.isRunning()) {
            //if currentplayer no longer has any troops to place
            if(phase == 0){
                if(evt.getKeyCode() == KeyEvent.VK_Y){
                    endTurn();
                }
                //TODO : what happens if we press N(o) ? Just ask the same question perhaps..

            } else if(phase == 1){
                //else if currentplayer has troops to place.
                if (evt.getKeyCode() == KeyEvent.VK_A && territories[0].isAvailableTo(currentPlayer)) {
                    currentPlayer.placeTroops(territories[0], 1);
                    System.out.println("Player " + currentPlayer.getName() + " Added a troop to territory A");
                    System.out.println("There are now a total of :" + territories[0].getTroops() +" troops on territory A");
                } else if (evt.getKeyCode() == KeyEvent.VK_B && territories[1].isAvailableTo(currentPlayer)) {
                    currentPlayer.placeTroops(territories[1], 1);
                    System.out.println("Player " + currentPlayer.getName() + " Added a troop to territory B");
                    System.out.println("There are now a total of :" + territories[1].getTroops() +" troops on territory B");
                }
                if(currentPlayer.amountOfTroops() == 0 ){
                    //make phase undefined for now, so above code can't be activated
                    oldPhase = phase;
                    phase = 0;
                }
            }

        }
        phaseTimer.stop();
        gameTimer.start();

        }

    //what should happen when a player ends their turn.
    public void endTurn(){

        //first of all we need to check if currentPlayer has done all their phases
        if(oldPhase == lastPhase ){
            //we should change players
            //this piece of code has to be changed if we in the future want to have more players, and phases
            //because this looks kindof bad
            if(currentPlayer.equals(one)){
                currentPlayer = two;
            }else{
                currentPlayer = one;
            }
            giveTroops(currentPlayer);
        }

        phase = firstPhase;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //phase 1 is for placing troops
        if(phase == 0){
            gameTimer.stop();
            System.out.println("No troops to place. End turn? Y/N");

            phaseTimer.start();

        } else if(phase == 1){
            gameTimer.stop();



            System.out.println("Player: " + currentPlayer.getName() + " you have " + currentPlayer.amountOfTroops() + " troops to place." +
                    " Do you want to place a troop on territory " + checkFreeTerritories());


            phaseTimer.start();
        }else if(phase == 2){
            // TODO: this shit
        }
    }


    //for now this method will return a String. However in the future this should be up to change.
    public String checkFreeTerritories(){




        ArrayList<Territory> availableTerritories = new ArrayList<Territory>();
        for(Territory t : territories){
            if(t.isAvailableTo(currentPlayer)){
                availableTerritories.add(t);
            }
        }


        //first see if
        if(territories[0].isAvailableTo(currentPlayer)){
            if(territories[1].isAvailableTo(currentPlayer)){
                return "A or B???";
            }else{
                return "A?";
            }
        }else if(territories[1].isAvailableTo(currentPlayer)){
            return "B?";
        }else{
            return "NO TERRITORIES";
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * A method for resolving combat.
     * @param attacker the Territory that the attacking troops comes from.
     * @param defender the Territory that is being defended.
     * @return true if the defender has lost all it troops in the territory.
     */
    public static boolean combat(Territory attacker, Territory defender){
        Dice die = new Dice();
        int[] atkRoll;
        int[] defRoll;
        //Attacker selects a number of dice <= #troops - 1 and 3

        int atkTroops = attacker.getTroops() - 1;

        if (atkTroops<1){
            throw new IllegalArgumentException("There are too few troops to attack");
        }

        if (atkTroops >3) atkTroops = 3;

        //Defender gets two die if #troops <= 2, #troops = 1 gives 1 die.

        int defTroops = defender.getTroops();
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
                defender.removeTroops(1);
            }else{
                //Attacker lose a troop;
                attacker.removeTroops(1);
            }

            //Remove die roll from pool.
            atkRoll[atkHighest] = 0;
            defRoll[defHighest] = 0;

            atkTroops--;
            defTroops--;
        }

        if (defender.getTroops()<1){
            return true;
        } else {
            return false;
        }
    }
}

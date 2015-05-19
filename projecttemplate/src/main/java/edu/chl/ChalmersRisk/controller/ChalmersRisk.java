package edu.chl.ChalmersRisk.controller;


import edu.chl.ChalmersRisk.model.*;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import edu.chl.ChalmersRisk.view.ProjectView;
import edu.chl.ChalmersRisk.view.StartScreen;
import javafx.application.Application;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 * @revisedBy malin thelin
 */
public class ChalmersRisk implements Controller {

    //all variables for the map
    private Maps map;
    private ArrayList<Continent> continents;
    private ArrayList<Territory> territories;


    private Player playerOne,playerTwo,currentPlayer;
    private int phase, oldPhase;

    private boolean gameIsRunning;
    private Stage primaryStage;
    private GameBoard gB;


    private StartScreen startScreen;


    //final variables defining the last and first phases. Meaning that
    //the last phase should be the one before a player ends their turn, and the first
    //phase is the first phase a player is in when a turn begins.
    private final int lastPhase = 0;
    private final int firstPhase = 0;

    private Timer gameTimer,phaseTimer;

    //TODO doCombat() - what should this method take?

    public ChalmersRisk(StartScreen startScreen){

        //set the startButton
        this.startScreen = startScreen;
        this.startScreen.getStartButton().setOnAction(new StartButtonPressed());

    }

    public void startGame(String[] players, Stage primaryStage) {
        //set all variables
        playerOne = new Player(players[0]);
        playerTwo = new Player(players[1]);
        currentPlayer = playerOne;
        phase = 0;
        gameIsRunning = true;
        this.primaryStage = primaryStage;



        System.out.println("HALLLÅÅÅ");
        System.out.println(playerOne.getName());
        System.out.println(playerTwo.getName());

        // TODO : what if you want a different map? Future thing
        Maps map = new ChalmersMap();
        continents = map.getContinents();
        territories = map.getTerritories();

        gB = new GameBoard(map,this);


        Scene gameBoard = new Scene(gB, Constants.width,Constants.height);

        gB.setMessage("A new game started between players:\n "+playerOne.getName()+" and "+playerTwo.getName());
        gB.setGameText("Player " + playerOne.getName() + "'s turn");


        //sätt
        gB.getInfoStrip().getNextButton().setOnAction(new NextButtonPressed());


        primaryStage.setScene(gameBoard);

        loopGame();
    }


    public void loopGame(){
        while(gameIsRunning){
            setTheScene();
            placeTroopPhase();
        }
    }

    public void placeTroopPhase(){
        //first give the troops to the player
        giveTroops(currentPlayer);
        System.out.println("gave "+currentPlayer.getTroopsToPlce().size()+ " troops to player "+currentPlayer.getName());
        gB.setController(new PlaceTroopController(currentPlayer, gB));
    }

    public void setTheScene(){
        if(phase == 0){

            System.out.println("phase 0");
            gameIsRunning = false;
        }
    }




  /*  public void startGame(Player one, Player two, JFrame mainFrame){

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
    }*/

    public void giveTroops(Player player){
        int lol = 0;
        ArrayList<Troop> troops = new ArrayList<>(nbrOfTroopsToGive(player) + 2);
        for(int i = 0; i< nbrOfTroopsToGive(player) + 2 ; i++){
            troops.add(new Troop(player));
            lol++;
        }
        player.receiveTroops(troops);
    }

    public int nbrOfTroopsToGive(Player player) {
        int total = player.getnmbrOfTerritories();
        //for all continents in continents see if player is an owner
        for(Continent c : continents){
            if(c.isContinentOwned(currentPlayer)){
                total += c.getValue();
            }
        }

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

    //what should happen when a player ends their turn.
    public void endTurn(){

        //first of all we need to check if currentPlayer has done all their phases
        if(oldPhase == lastPhase ){
            //we should change players
            //this piece of code has to be changed if we in the future want to have more players, and phases
            //because this looks kindof bad
            if(currentPlayer.equals(playerOne)){
                currentPlayer = playerTwo;
            }else{
                currentPlayer = playerOne;
            }


            //and we should also set the phase to first
            phase = firstPhase;
        }
        gameIsRunning = true;
        loopGame();
    }

    //for now this method will return a String. However in the future this should be up to change.
    public String checkFreeTerritories(){

        String result = "";
        int count = 0;

        //ArrayList<Territory> availableTerritories = new ArrayList<Territory>(); this is probably unnecessary
        for(Territory t : territories){
            if(t.isAvailableTo(currentPlayer)){
                //availableTerritories.add(t);
                result += t.getName()+ ", ";
                count ++;
            }
        }

        if(count == 0){
            return "NO TERRITORIES";
        }else{
            return result;
        }

    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public ArrayList<Continent> getContinents() {
        return continents;
    }

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

        int atkTroops = attacker.getAmountOfTroops() - 1;

        if (atkTroops<1){
            throw new IllegalArgumentException("There are too few troops to attack");
        }

        if (atkTroops >3) atkTroops = 3;

        //Defender gets two die if #troops <= 2, #troops = 1 gives 1 die.

        int defTroops = defender.getAmountOfTroops();
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

        if (defender.getAmountOfTroops()<1){
            return true;
        } else {
            return false;
        }
    }

    public boolean moveTroops(Territory fromTerritory, Territory toTerritory){
        return moveTroops(fromTerritory,toTerritory,fromTerritory.getAmountOfTroops()-1);
    }

    //TODO Should this return a boolean or throw exceptions?
    /**
     * A method for moving troops from one territory to another.
     * @param fromT the territory to move the troops from.
     * @param toT the territory to move the troops to.
     * @param amount the amount of troops.
     * @return if the move was successful.
     */
    public boolean moveTroops(Territory fromT, Territory toT, int amount){
        //TODO add a test to see if owner is equal to the current active player.
        //Tests if the territories are owned by the same player.
        if(fromT.getOwner()!=toT.getOwner()){
            //throw new IllegalArgumentException("Territories aren't owned by the same player.");
            return false;
        }

        //Tests if there is a path between two territories.
        if (territoriesAreConnected(fromT,toT,fromT.getOwner())){
            //TODO this removes an amount of troops and adds the same amount somewhere else
            // should it move the actual troops instead?
            if (fromT.getAmountOfTroops()>amount){
                fromT.removeTroops(amount);
                toT.addTroops(amount);
                return true;
            }
        }
        return false;
        //throw new IllegalArgumentException("There are no path between the territories.");

    }

    //TODO write comments.
    /**
     * A method for finding a path of territories that is owned by the same player
     * between two territories. Based on a depth first algorithm.
     * @param fromT the territory to start checking.
     * @param toT the terrtiroy to find.
     * @param owner the player who owns of the territories.
     * @return
     */
    private boolean territoriesAreConnected(Territory fromT, Territory toT, Player owner) {
        boolean hasPath = false;
        Stack<Territory> toTest = new Stack<Territory>();
        List<Territory> discovered = new LinkedList<Territory>();
        toTest.push(fromT);

        while (!toTest.isEmpty() && !hasPath) {
            Territory search = toTest.pop();
            if (search.equals(toT)) {
                hasPath = true;
                //This could be move to anywhere with a return true statement.
            } else {
                discovered.add(search);
                for (Territory it : search.getAdjacentTerritories()) {
                    if (it.getOwner().equals(owner)) {
                        Boolean isUndiscovered = true;

                        //Loop through the discovered territories to see if it has already been searched.
                        int i = 0;
                        while (i < discovered.size() && isUndiscovered) {
                            if (discovered.get(i).equals(it)) {
                                isUndiscovered = false;
                            }
                            i++;
                        }

                        if (isUndiscovered) {
                            toTest.push(it);
                        }
                    }
                }
            }
        }
        return hasPath;
    }



    private class StartButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String[] players = new String[2];
            players[0] = startScreen.getPlayerOne().getText();
            players[1] = startScreen.getPlayerTwo().getText();
            startGame(players, startScreen.getPrimaryStage());
        }
    }


    private class NextButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            oldPhase = phase;
            phase++;
            phase %= 3;
            System.out.println("PHASE :" + phase);
            endTurn();
        }
    }
}



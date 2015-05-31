package edu.chl.ChalmersRisk.controller;


import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.*;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import edu.chl.ChalmersRisk.view.StartScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 * @revisedBy malin thelin, Björn Bergqvist
 */
public class ChalmersRisk implements Controller {

    //all variables for the map
    private Maps map;
    private ArrayList<Continent> continents;
    private ArrayList<Territory> territories;


    private Player playerOne,playerTwo,currentPlayer;
    private int phase, oldPhase;

    private GameBoard gB;


    private StartScreen startScreen;


    //final variables defining the last and first phases. Meaning that
    //the last phase should be the one before a player ends their turn, and the first
    //phase is the first phase a player is in when a turn begins.
    private final int lastPhase = 2;
    private final int firstPhase = 0;



    public ChalmersRisk(StartScreen startScreen){

        //set the startButton
        this.startScreen = startScreen;
        this.startScreen.getStartButton().setOnAction(new StartButtonPressed());

    }


    //This is an empty constructor to used test some methods that don't require a working view.
    public ChalmersRisk(){
    }

    private void startGame(String[] players, Stage primaryStage) {
        //set all variables
        playerOne = new Player(players[0],"0000ff");
        playerTwo = new Player(players[1],"ff0000");
        currentPlayer = playerOne;
        phase = 0;

        Stage stage = primaryStage;


        // TODO : what if you want a different map? Future thing
        //loadMap("Chalmers");
        loadMap("Test");

        gB = new GameBoard(map,this);

        Scene gameBoard = new Scene(gB, map.getWidth(),Constants.height);

        gB.setMessage("A new game started between players:\n " + playerOne.getName() + " and " + playerTwo.getName());
        gB.setGameText("Player " + playerOne.getName() + "'s turn");

        //sätt
        gB.getInfoStrip().getNextButton().setOnAction(new NextButtonPressed());


        stage.setScene(gameBoard);

        //give initialtroops

        loopGame();
    }

    private boolean areAllTerritoriesTaken(){

        //go through all the territories
        for(Territory t : map.getTerritories()){
            //if at least one of them doesn't have an owner
            if(t.getOwner().equals(Constants.EMPTY_PLAYER)){
               return false;
            }
        }
        //else true
        return true;
    }

    private void loopGame(){
        setTheScene();
        if(phase == 0){
            placeTroopPhase();
        }else if(phase == 1){
            attackPhase();
        }else if(phase == 2){
            moveTroopsPhase();
        }

    }

    private boolean initialPhase(){

        //players have to alternate between themselves, placing one troop until there are atleast Constant.begTroops troops
        return !(playerOne.getPlacedTroops().size() + playerTwo.getPlacedTroops().size() >= Constants.begTroops);
    }

    private void placeTroopPhase(){
        //first give the troops to the player
        giveTroops(currentPlayer);
        gB.setMessage(currentPlayer.getName() + " recieved "+currentPlayer.getTroopsToPlace().size() + " number of troops this turn.");
        gB.setController(new PlaceTroopController(currentPlayer, gB));
    }

    private void attackPhase(){

        gB.setGameText("ATTACK PHASE");
        gB.setMessage("Välj ett territory att attackera ifrån");

        gB.setController(new AttackPhaseController(currentPlayer, gB));
    }

    private void moveTroopsPhase(){
        gB.setGameText("MOVE TROOP PHASE");
        gB.setController(new MoveTroopController(currentPlayer,gB));
    }




    private void setTheScene(){
        //remove all effects on the buttons
        for(TerritoryView t: gB.getTerritoryViews()) {
            t.removeFocused();
        }

    }

    private void giveTroops(Player player){

        ArrayList<Troop> troops = new ArrayList<>(nbrOfTroopsToGive(player) + 2);

        if(initialPhase()){
            troops.add(new Troop(player));
        }else{
            for(int i = 0; i< nbrOfTroopsToGive(player) + 2 ; i++){
                troops.add(new Troop(player));
            }
        }
        player.receiveTroops(troops);
    }

    private int nbrOfTroopsToGive(Player player) {
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
    private void loadMap(String name){

        if(name.equals("Chalmers")){
            map = new ChalmersMap();
        }else if(name.equals("Test")){
            map = new TestMap();
        }

        //this code will always be done last
        territories = map.getTerritories();
        continents = map.getContinents();


    }

    /**
     * method to determine whether or not you can actually end your turn. used when a user clicks the nextButton.
     * @return false if you cant end your turn and true if you can
     */
    private boolean canEndTurn(){
        switch(phase){
            case 0: if(!currentPlayer.getTroopsToPlace().isEmpty()){
                gB.setMessage("You can't end your turn yet.\nYou still have "+currentPlayer.getTroopsToPlace().size()+ " troops to place!");
                return false;
            }

        }
        return true;
    }

    //what should happen when a player ends their turn.
    private void endTurn(){

        //trivial, if we were at the last phase, we should change players.
        if(oldPhase == lastPhase ){
            changePlayers();
        } else if(areAllTerritoriesTaken() || canPlayerGoToAttack()){
            //we may only come to the attackPhase if we can go to attack and all the territories are taken.
        }else{
            //just change players
            changePlayers();
        }

        //then continue with the game
        loopGame();
    }


    /**
     * Simple method to check if you can actually attack. So that a player does not even get into the
     * attackphase when he/she can't attack. A player needs more than one troop on at least one territory to be able to
     * attack.
     * @return true if the player can attack, false if they cannot.
     */
    private boolean canPlayerGoToAttack(){
        //if phase is 1 => attackphase
        if(phase == 1){
            for(Territory t: currentPlayer.getTerritories()){
                if(t.getAmountOfTroops() > 1){
                    return true;
                }
            }
            return false;

        }
        return false;

    }

    private void changePlayers(){
        if(currentPlayer.equals(playerOne)){
            currentPlayer = playerTwo;
        }else{
            currentPlayer = playerOne;
        }

        gB.setMessage("");
        gB.setGameText("Player " + currentPlayer.getName() + "'s turn");

        phase = firstPhase;

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

        int[] atkRoll;
        int[] defRoll;
        int atkTroops = attacker.getAmountOfTroops()-1;
        DiceCup cupOfDice = new DiceCup();

        //Attacker selects a number of dice <= #troops - 1 and 3

        if(attacker.getOwner().equals(defender.getOwner())){
            throw new IllegalArgumentException("Both territories are own by the same player.");
        }

        if (atkTroops<1){
            throw new IllegalArgumentException("There are too few troops to attack");
        }

        if (atkTroops >3) atkTroops = 3;

        //Defender gets two die if #troops <= 2, #troops = 1 gives 1 die.

        int defTroops = defender.getAmountOfTroops();
        if (defTroops>2) defTroops = 2;

        //Creating attacker's die array.
        if(atkTroops>=3){
            atkRoll = cupOfDice.rollDice(3);
        }
        else if (atkTroops==2){
            atkRoll = cupOfDice.rollDice(2);
        } else {
            atkRoll = cupOfDice.rollDice(1);
   }


        //Creating defender's die array.
        if (defTroops>=2){
            defRoll = cupOfDice.rollDice(2);
          System.out.println(defRoll[0]);
        } else {
            defRoll = cupOfDice.rollDice(1);
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

    /**
     * A method for moving all available troops from one territory to another.
     * @param fromTerritory the territory to move the troops from.
     * @param toTerritory the territory to move the troops to.
     * @return if the move was successful.
     */
    public boolean moveTroops(Territory fromTerritory, Territory toTerritory){
        return moveTroops(fromTerritory,toTerritory,fromTerritory.getAmountOfTroops()-1);
    }

    /**
     * A method for moving troops from one territory to another.
     * @param fromT the territory to move the troops from.
     * @param toT the territory to move the troops to.
     * @param amount the amount of troops.
     * @return if the move was successful.
     */
    public static boolean moveTroops(Territory fromT, Territory toT, int amount){
        //Return false if less than 1 troops should be moved.
        if (amount<1){
            return false;
        }
        //TODO add a test to see if owner is equal to the current active player.
        //Tests if the territories are owned by the same player.
        if(fromT.getOwner()!=toT.getOwner()){
            //throw new IllegalArgumentException("Territories aren't owned by the same player.");
            return false;
        }

        //Tests if there is a path between two territories.
        if (territoriesAreConnected(fromT,toT,fromT.getOwner())){

            // should it move the actual troops instead?
            if (fromT.getAmountOfTroops()-1>=amount){
                fromT.removeTroops(amount);
                toT.addTroops(amount);
                return true;
            }
        }
        return false;
        //throw new IllegalArgumentException("There are no path between the territories.");

    }

    /**
     * A method for finding a path of territories that is owned by the same player
     * between two territories. Based on a depth first algorithm.
     * @param fromT the territory to start checking.
     * @param toT the terrtiroy to find.
     * @param owner the player who owns of the territories.
     * @return
     */
    public static boolean territoriesAreConnected(Territory fromT, Territory toT, Player owner) {
        boolean hasPath = false;
        Stack<Territory> toTest = new Stack<Territory>();
        List<Territory> discovered = new LinkedList<Territory>();
        toTest.push(fromT);

        // This is the search algorithm
        // it has an extra condition that end it if a path from A to B has been discovered.
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




    /**
     * HERE LIETH THE CLASSES FOR BUTTONPRESSES
     */

    private class StartButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {


            if(startScreen.getPlayerOne().getText().equals("") || startScreen.getPlayerTwo().getText().equals("")){
                startScreen.setWarningText("Please enter names of the players");
            }else{
                String[] players = new String[2];
                players[0] = startScreen.getPlayerOne().getText();
                players[1] = startScreen.getPlayerTwo().getText();
                startGame(players, startScreen.getPrimaryStage());
            }



        }
    }


    private class NextButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //first of all, see if they can end their turn

            if(canEndTurn()){

                oldPhase = phase;
                if(initialPhase()){
                    //when it's the initialphase the players shouldn't get out of phase 0
                }else{
                    phase++;
                    phase %= 3;
                }
                System.out.println("PHASE :" + phase);
                endTurn();
            }
        }
    }


}



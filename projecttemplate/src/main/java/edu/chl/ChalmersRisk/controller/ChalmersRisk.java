package edu.chl.ChalmersRisk.controller;


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
    private final int lastPhase = 1;
    private final int firstPhase = 0;

    private Timer gameTimer,phaseTimer;


    public ChalmersRisk(StartScreen startScreen){

        //set the startButton
        this.startScreen = startScreen;
        this.startScreen.getStartButton().setOnAction(new StartButtonPressed());

    }


    //This is an empty constructor to used test some methods that don't require a working view.
    public ChalmersRisk(){
    }

    public void startGame(String[] players, Stage primaryStage) {
        //set all variables
        playerOne = new Player(players[0],"0000ff");
        playerTwo = new Player(players[1],"ff0000");
        currentPlayer = playerOne;
        phase = 0;
        gameIsRunning = true;
        this.primaryStage = primaryStage;



        //TODO : remove when not needed
        System.out.println("HALLLÅÅÅ");
        System.out.println(playerOne.getName());
        System.out.println(playerTwo.getName());

        // TODO : what if you want a different map? Future thing
        loadMap("Chalmers");

        gB = new GameBoard(map,this);


        Scene gameBoard = new Scene(gB, Constants.width,Constants.height);

        gB.setMessage("A new game started between players:\n " + playerOne.getName() + " and " + playerTwo.getName());
        gB.setGameText("Player " + playerOne.getName() + "'s turn");


        //sätt
        gB.getInfoStrip().getNextButton().setOnAction(new NextButtonPressed());


        primaryStage.setScene(gameBoard);


        //give initialtroops


        loopGame();
    }

    public boolean areAllTerritoriesTaken(){

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


    public void loopGame(){
        setTheScene();
        if(phase == 0){
            placeTroopPhase();
        }else if(phase == 1){
            attackPhase();
        }

    }

    public boolean initialPhase(){

        //players have to alternate between themselves, placing one troop until there are atleast Constant.begTroops troops
        if(playerOne.getPlacedTroops().size() + playerTwo.getPlacedTroops().size() >= Constants.begTroops){
            return false;
        }else{
            return true;
        }
    }

    public void placeTroopPhase(){
        //first give the troops to the player
        giveTroops(currentPlayer);
        gB.setMessage(currentPlayer.getName() + " recieved "+currentPlayer.getTroopsToPlace().size() + " number of troops this turn.");
        gB.setController(new PlaceTroopController(currentPlayer, gB));
    }

    public void attackPhase(){


        gB.setGameText("ATTACK PHASE");
        gB.setMessage("Välj ett territory att attackera ifrån");

        gB.setController(new AttackPhaseController(currentPlayer, gB));
    }

    public void setTheScene(){
            System.out.println("phase 0");
            gameIsRunning = false;

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

    /**
     * method to determine whether or not you can actually end your turn. used when a user clicks the nextButton.
     * @return false if you cant end your turn and true if you can
     */
    public boolean canEndTurn(){
        switch(phase){
            case 0: if(!currentPlayer.getTroopsToPlace().isEmpty()){
                gB.setMessage("You can't end your turn yet.\nYou still have "+currentPlayer.getTroopsToPlace().size()+ " troops to place!");
                return false;
            };

        }
        return true;
    }

    //what should happen when a player ends their turn.
    public void endTurn(){

        //first first of all we have to see if there are still empty territories.
        if(areAllTerritoriesTaken() && canPlayerGoToAttack()){
            System.out.println("Vi hamnar här :(");
            //first of all we need to check if currentPlayer has done all their phases
            if(oldPhase == lastPhase ){
                changePlayers();
            }
        }else{
            System.out.println("yay, vi hamnar här!!");
            //just change players
            changePlayers();
        }

        gameIsRunning = true;
        //then continue with the game
        loopGame();
    }


    /**
     * Simple method to check if you can actually attack. So that a player does not even get into the
     * attackphase when he/she can't attack. A player needs more than one troop on at least one territory to be able to
     * attack.
     * @return true if the player can attack, false if they cannot.
     */
    public boolean canPlayerGoToAttack(){
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

    public void changePlayers(){
        if(currentPlayer.equals(playerOne)){
            currentPlayer = playerTwo;
        }else{
            currentPlayer = playerOne;
        }

        gB.setMessage("");
        gB.setGameText("Player " + currentPlayer.getName() + "'s turn");

        phase = firstPhase;

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
     * @param atkTroops the amount of troops to attack with.
     * @return true if the defender has lost all it troops in the territory.
     */
    public static boolean combat(Territory attacker, Territory defender, int atkTroops){

        int[] atkRoll;
        int[] defRoll;
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

        System.out.println("ATTACKER RULLADE ::: "+atkRoll[0]);

        System.out.println("DEFENDER RULLADE ::: "+ defRoll[0]);



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


            if(startScreen.getPlayerOne().getText().toString().equals("") || startScreen.getPlayerTwo().getText().toString().equals("")){
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



package edu.chl.ChalmersRisk.controller;


import edu.chl.ChalmersRisk.cardModels.*;
import edu.chl.ChalmersRisk.model.*;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.CardView;
import edu.chl.ChalmersRisk.view.GameBoard;
import edu.chl.ChalmersRisk.view.StartScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;



/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 * @revisedBy malin thelin, Björn Bergqvist, Robin Jansson
 */
public class ChalmersRisk implements Controller {

    //all variables for the map
    private Maps map;
    private ArrayList<Continent> continents;
    private ArrayList<Territory> territories;


    private Player playerOne,playerTwo,currentPlayer = Constants.EMPTY_PLAYER;
    private int phase, oldPhase;

    private Stage primaryStage;
    private GameBoard gB;

    private DeckOfCards deck;
    private ICard eventCard = Constants.EMPTY_CARD;
    public static boolean mayDrawCard = false;

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

    /**
     * Starts the game and sets all the components and initializes objects in the game.
     * @param players the names of the players of the game.
     * @param primaryStage the mainframe where all that will contain all graphical components.
     */
    public void startGame(String[] players, Stage primaryStage) {
        //set all variables
        playerOne = new Player(players[0],"0000ff");
        playerTwo = new Player(players[1],"ff0000");
        currentPlayer = playerOne;
        phase = 0;
        this.primaryStage = primaryStage;

        // TODO : what if you want a different map? Future thing
        loadMap("Chalmers");
        //loadMap("Test");

        gB = new GameBoard(map,this);

        Scene gameBoard = new Scene(gB, map.getWidth(),Constants.height);

        gB.setMessage("A new game started between players:\n " + playerOne.getName() + " and " + playerTwo.getName());
        gB.setGameText("Player " + playerOne.getName() + "'s turn");

        //sätt
        gB.getInfoStrip().getNextButton().setOnAction(new NextButtonPressed());

        primaryStage.setScene(gameBoard);

        // initilizes the deck
        createDeck();

        loopGame();
    }

    /**
     * Checks if all territories are taken, if one or more territory is still owned by the "EMPTY_PLAYER"
     * there are still territorys that are free to claim by placing troops here.
     * @return true if all are claimed, false if they are not.
     */
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

    /**
     * This is the gameloop that handles all the phases. Keeps the game running.
     */
    public void loopGame(){
        if(phase == 0){
            // checks if the player is allowed to draw an event card
            if (mayDrawCard) {
                if (deck.size() < 1) {
                    deck.resetDeck();
                }

                eventCard = deck.pullCard();
                mayDrawCard = false;
            }

            CardView.display(eventCard);

            if (eventCard.phaseCheck() == 0) { // activates certaint event cards
                eventCard.turnCard();

                if (eventCard.getTitle().equals("Ops!")) {
                    gB.update(2);
                } else {
                    gB.update(1);
                }
            }

            placeTroopPhase();
        }else if(phase == 1){
            attackPhase();
        }else if(phase == 2){
            moveTroopsPhase();
        }

    }

    /**
     * Checks if the amount of placed troops is enough and all territories are claimed. If one of these conditions
     * aren't met the game cannot get of of the initialphase where players may only place one troops at a time per turn.
     * @return Whether the game is still in the initial phase of the game where the players get to claim territories. True if its i, false otherwise.
     */
    public boolean initialPhase(){

        //players have to alternate between themselves, placing one troop until there are atleast Constant.begTroops troops
        if(playerOne.getPlacedTroops().size() + playerTwo.getPlacedTroops().size() >= Constants.begTroops && areAllTerritoriesTaken()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * The method to makes sure that the player may only placeTroops in the placeTroops phase.
     * Sets the gameBoard controller to PlaceTroopController.
     */
    public void placeTroopPhase(){
        //first give the troops to the player
        giveTroops(currentPlayer);
        gB.setMessage(currentPlayer.getName() + " recieved "+currentPlayer.getTroopsToPlace().size() + " number of troops this turn.");
        if (eventCard.phaseCheck() == 1) { // if the event card that affect placable troops is drawn
            eventCard.turnCard();
        }
        gB.setController(new PlaceTroopController(currentPlayer, gB));
    }

    /**
     * The method to makes sure that the player may only attack during the attack phase.
     * Sets the gameBoard controller to AttackPhaseController.
     */
    public void attackPhase(){
        gB.setGameText("ATTACK PHASE");
        gB.setMessage("Välj ett territory att attackera ifrån");
        gB.setController(new AttackPhaseController(currentPlayer, gB));
    }

    /**
     * The method to makes sure that the player may only move troops in the moveTroops phase.
     * Sets the gameBoard controller to MoveTroopController.
     */
    public void moveTroopsPhase(){
        gB.setGameText("MOVE TROOP PHASE");
        gB.setController(new MoveTroopController(currentPlayer,gB));
    }

    /**
     * Gives a player the correct amount of troops this turn. Varies dependent on how many territories
     * the player controls aswell as whether the player controls entire continents.
     * @param player to be given troops.
     */
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

    /**
     * Calculates how many troops the player should get this turn.
     * @param player the player that should receive troops.
     * @return amount of troops to get this turn.
     */
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

    /**
     * This method was implemented due to the fact that we might want to add options of loading a map.
     * This is in case we wanted to extend the model.
     * @param name of the map to load.
     */
    public void loadMap(String name){

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
    public boolean canEndTurn(){
        switch(phase){
            case 0: if(!currentPlayer.getTroopsToPlace().isEmpty()){
                gB.setMessage("You can't end your turn yet.\nYou still have "+currentPlayer.getTroopsToPlace().size()+ " troops to place!");
                return false;
            };

        }
        return true;
    }


    /**
     * This method checks which phase to go to when net button is pressed. This method makes sure
     * the game doesn't enter any unneccesary state, like moveTroops phase when the current player
     * doesn't have any troops available to move.
     */
    public void endTurn(){

        if (!eventCard.getTitle().equals(Constants.EMPTY_CARD.getTitle())) { //to avoid placing EMPTY_CARD back in the deck
            deck.addCardToBackOfDeck(eventCard);
            eventCard = Constants.EMPTY_CARD;
        }

        //trivial, if we were at the last phase, we should change players.
        if(oldPhase == lastPhase ){
            changePlayers();
        } else if(areAllTerritoriesTaken() && canPlayerGoToAttack()){
            //we may only come to the attackPhase if we can go to attack and all the territories are taken.
        } else if(canPlayerMoveTroops()) {
            
        } else{
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

    /**
     * Simple check to see if the current player can move troops or not, if not then we skip this phase.
     * @return true if moves are possible for the current player and false otherwise.
     */
    public boolean canPlayerMoveTroops() {
        if(phase == 2){
            for(Territory t: currentPlayer.getTerritories()){
                if(t.getAmountOfTroops() > 1){
                    return true;
                }
            }
            return false;

        }
        return false;
    }

    /**
     * A method to make sure this twoplayer game runs smoothly and the players turn works properly.
     * If current player is first, this method changes current player to player two.
     */
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

    public ArrayList<Territory> getTerritories() {
        return territories;
    }


    /**
     * This is a class for the eventhandling of startbutton pressed. It's the button on the startscreen
     * where the players get to enter their names. It sets the names of the players according to the
     * input from the startscreen. Also makes sure that the players doesn't try to enter blank names.
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

    /**
     * A class to handle the presses of the next button. It has checks if the press is okey at the current time of the game.
     * If the button is pressed at an accepted point of the game it will move on to the next phase of this game.
     * This implimentation of ChalmersRisk has three phases, placeTroop phase, attackPhase and moveTroops phase.
     * It implements EventHandler wich is the standard way of handling events in javaFX.
     */
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
                endTurn();
            }
        }
    }

    /**
     * A method that creates and shuffles the deck.
     */
    private void createDeck() {
        deck  = new DeckOfCards();
        for (int i = 0 ; i < 300 ; i++) {
            //deck.addCardToDeck(new BlankCard());
        }
        for (int i = 0 ; i < 1 ; i++) {

            //deck.addCardToDeck(new AdditionalTroopsCard(this.currentPlayer, 2)); ------------ probably OK
            //deck.addCardToDeck(new AllChangeTroopCard(getContinents(), 1)); -------------OK

            //deck.addCardToDeck(new LoseTerritoryCard(this.currentPlayer));
            deck.addCardToDeck(new LoseTerritoryCard(playerOne, playerTwo)); // this will effect a random player
            //deck.addCardToDeck(new TerritoryChangeCard(playerOne, playerTwo));
           // deck.addCardToDeck(new TerritoryTroopCard(this.currentPlayer, 3));
           // deck.addCardToDeck(new TerritoryTroopCard( getContinents().get(0).getTerritories().get(0), 3 ));
           // deck.addCardToDeck(new TerritoryTroopCard( getContinents().get(0).getTerritories().get(1), 2 ));
        }

        deck.resetDeck();
    }
}

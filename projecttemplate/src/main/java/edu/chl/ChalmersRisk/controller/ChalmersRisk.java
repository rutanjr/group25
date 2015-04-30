package edu.chl.ChalmersRisk.controller;


import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import edu.chl.ChalmersRisk.view.ProjectView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import edu.chl.ChalmersRisk.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Stack;
import java.util.List;


/**
 * Created by rutanjr on 2015-03-31.
 * A class to be the main controller of the game, controls the board and then actions performed in the game.
 *
 * @revisedBy malin thelin
 */
public class ChalmersRisk{

    //all variables for the map
    private Maps map;
    private ArrayList<Continent> continents;
    private ArrayList<Territory> territories;

    private static Player playerOne, playerTwo;

    private Player one,two,currentPlayer;
    private int phase, oldPhase;
    private ProjectView projectView;

    //final variables defining the last and first phases. Meaning that
    //the last phase should be the one before a player ends their turn, and the first
    //phase is the first phase a player is in when a turn begins.
    private final int lastPhase = 1;
    private final int firstPhase = 1;

    private Timer gameTimer,phaseTimer;

    //TODO doCombat() - what should this method take?

    public ChalmersRisk(){
        one = new Player("Lol");
        two = new Player("plz");

        currentPlayer = one;
        //phase 1=place troops 2=move
        phase = 1;


        //TODO , some kindof loadMap() method ??
        loadMap("Chalmers");

        Application.launch(ProjectView.class, null);

        //projectView.start(new Stage());

        //startGame(one, two, mainFrame);
    }

    public static void startGame(String[] players, Stage primaryStage) {
        playerOne = new Player(players[0]);
        playerTwo = new Player(players[1]);
        System.out.println(playerOne.getName());
        System.out.println(playerTwo.getName());

        GameBoard gameBoard = new GameBoard(new ChalmersMap());

        Scene scene = new Scene(gameBoard, 200,200);
        Button[] territoryButtons = gameBoard.getButtons();

        for (Button button: territoryButtons){
            button.setOnAction(new EventHandler<ActionEvent>() {
                int i = 0;
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("hejsan");
                    if(i % 2 == 0){
                        button.setTextFill(Paint.valueOf("pink"));
                    }else{
                        System.out.println("Else");
                        button.setTextFill(Paint.valueOf("blue"));
                    }
                    i++;
                }
            });
        }
        primaryStage.setScene(scene);
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
        System.out.println(lol);
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
            if(currentPlayer.equals(one)){
                currentPlayer = two;
            }else{
                currentPlayer = one;
            }
            giveTroops(currentPlayer);
        }

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
     * @return true if the defender has lost all it troops in the territory.
     */
    public boolean combat(Territory attacker, Territory defender){
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

    //TODO create javadoc
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

    //TODO write comments.
    /**
     * A method for finding a path of territories that is owned by the same player
     * between two territories. Based on a depth first algorithm.
     * @param fromT the territory to start checking.
     * @param toT the terrtiroy to find.
     * @param owner the player who owns of the territories.
     * @return
     */
    public static boolean territoriesAreConnected(Territory fromT, Territory toT, Player owner){
        boolean hasPath = false;
        Stack<Territory> toTest = new Stack<Territory>();
        List<Territory> discovered = new LinkedList<Territory>();
        toTest.push(fromT);

        while(!toTest.isEmpty()&&!hasPath){
            Territory search = toTest.pop();
            if (search.equals(toT)){
                hasPath=true;
                //This could be move to anywhere with a return true statement.
            }else{
                discovered.add(search);
                for (Territory it : search.getAdjacentTerritories()){
                    if (it.getOwner().equals(owner)){
                        Boolean isUndiscovered = true;

                        //Loop through the discovered territories to see if it has already been searched.
                        int i = 0;
                        while (i < discovered.size() && isUndiscovered) {
                            if (discovered.get(i).equals(it)){
                                isUndiscovered=false;
                            }
                            i++;
                        }

                        if (isUndiscovered){
                            toTest.push(it);
                        }
                    }
                }
            }
        }
        return hasPath;
    }
}

package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.model.Troop;

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

    private Territory A, B;
    private Continent continent;
    private Player one,two,currentPlayer;
    private int phase, oldPhase;

    //final variables defining the phases
    private final int lastPhase = 1;
    private final int firstPhase = 1;

    Timer gameTimer,phaseTimer;

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
        giveCurrentPlayerTroops();


        //set up the mainframe, adds keylistener to the game
        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(this);
        mainFrame.setVisible(true);
        mainFrame.setSize(1, 1);
    }

    public void giveCurrentPlayerTroops(){
        ArrayList<Troop> troops = new ArrayList<>(2);
        for(int i = 0; i< 2;i++){
            troops.add(new Troop(currentPlayer));
        }
        currentPlayer.receiveTroops(troops);
    }


    //in case we want to use different maps
    public void loadMap(String name){

        if(name.equals("Chalmers")){
            continent = new Continent("Chalmers");

            A = new Territory("A",continent);
            B = new Territory("B", continent);
        }
    }


    public void doAction(){
        //if we're in placing troops phase
        if(phase == 1){
            System.out.println("Välj ett territorium att placera en trupp på: A eller B");


        }
    }



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
                if (evt.getKeyCode() == KeyEvent.VK_A) {
                    currentPlayer.placeTroops(A, 1);
                    System.out.println("Player " + currentPlayer.getName() + " Added a troop to territory A");
                } else if (evt.getKeyCode() == KeyEvent.VK_B) {
                    currentPlayer.placeTroops(B, 1);
                    System.out.println("Player " + currentPlayer.getName() + " Added a troop to territory B");
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
            if(currentPlayer.equals(one)){
                currentPlayer = two;
            }else{
                currentPlayer = one;
            }
            giveCurrentPlayerTroops();
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
                    " Do you want to place a troop on territory A or B?");
            phaseTimer.start();
        }else if(phase == 2){
            // TODO: this shit
        }


    }


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.Maps;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import edu.chl.ChalmersRisk.view.WinView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

/**
 * Created by Malin on 2015-05-20.
 * @revisedBy Robin Jansson
 * @revisedBy rutanjr, Oskar Rutqvist
 *
 * Class used to control the GameBoard during the attack phase.
 */
public class AttackPhaseController implements Controller {

    private Player player;
    private GameBoard gameBoard;
    private boolean canAttack;
    private Territory attackFrom;
    private TerritoryView attackButton;
    private Maps map;
    ChalmersRisk chalmersRisk;


    /**
     * Class constructor
     * @param player the player who is in control
     * @param gameBoard the viev
     * @param chalmersRisk used to be able to reference back in case the player wins
     */
    public AttackPhaseController(Player player, GameBoard gameBoard, ChalmersRisk chalmersRisk){
        this.player = player;
        this.gameBoard = gameBoard;
        canAttack = false;
        map = gameBoard.getMap();
        this.chalmersRisk = chalmersRisk;


        //sets listener to all the TerritoryViews
        TerritoryView[] territoryViews = gameBoard.getTerritoryViews();
        for (TerritoryView tv: territoryViews) {
            //tv.setOnAction(new ButtonPressed());
            tv.getImage().setOnMouseClicked(new ButtonPressed());
        }

    }


    /**
     * Private class for the pressed territories in the game. Used to attack.
     */
    private class ButtonPressed implements EventHandler {
        @Override
        public void handle(Event event) {

            TerritoryView btn = (TerritoryView)((ImageView) event.getSource()).getParent();

            //first if the player chooses a territory that he owns
            if(btn.getTerritory().getOwner().equals(player)  && btn.getTerritory().getAmountOfTroops() > 1){

                //sets a black border around the button
                attackButton = btn;
                attackButton.setFocused();

                gameBoard.setMessage("Välj nu ett område att attackera!");
                attackFrom = btn.getTerritory();
                canAttack = true;
            }else if(btn.getTerritory().getAmountOfTroops() <= 1 && btn.getTerritory().getOwner().equals(player)){ //
                // if you pick a territory where you don't have enough troops to attack with
                gameBoard.setMessage("Du har för få trupper här");
                attackFrom = btn.getTerritory();

                //you shouldn't be able to attack
                canAttack = false;
            }
            //if the territory clicked is neither empty nor owned by the player itself : ergo it's owned by another player
            // and if the player can attack
            else if(territoryOwnedBySomeoneElse(btn.getTerritory()) && canAttack){

                Territory defendingTerritory = btn.getTerritory();

                //if this returns true it means that the defender territory got empty
                if(player.combat(attackFrom,defendingTerritory)){

                    //and if it got empty we should move the attacker players
                    //  defendingTerritory.setnewOwner(player); //set new owner

                    player.addTerritory(defendingTerritory);
                    player.moveTroops(attackFrom, defendingTerritory, attackFrom.getAmountOfTroops() - 1);

                    gameBoard.setMessage("");

                    if (ChalmersRisk.cardWinnerA == Constants.EMPTY_PLAYER) {
                        ChalmersRisk.cardWinnerA = player;
                        ChalmersRisk.aMayDrawCard = true;
                    } else if (ChalmersRisk.cardWinnerB == Constants.EMPTY_PLAYER) {
                        ChalmersRisk.cardWinnerB = player;
                        ChalmersRisk.bMayDrawCard = true;
                    }

                    //check if the player won the game
                    if(playerWon()){
                        gameBoard.setMessage("Congratulations you won!!!");
                        new WinView();
                        chalmersRisk.startNewGame();

                    }
                }

                //draw the dice on the gameboard
                gameBoard.setDiceArea(player.getAttackRolls(),true);
                gameBoard.setDiceArea(player.getDefenderRolls(),false);


                canAttack = attackFrom.getAmountOfTroops()>1;

                attackButton.removeFocused();



                //but no matter what, we should update

                gameBoard.update(2);

            }



        }

        /**
         * Simple method to see if a territory is owned by someone else.
         * @param territory the territory to check
         * @return true if it is owned by someone else (or is empty), false if it isn't
         */
        private boolean territoryOwnedBySomeoneElse(Territory territory){
            return !territory.isAvailableTo(player);
        }

        /**
         * Checks of this player holds all territories and therefore wins the game.
         * @return true if the player wins.
         */
        private boolean playerWon(){

            for(Territory t: map.getTerritories()){
                //if there is even one territory which the player does not own..
                if(!player.isMyTerritory(t)){
                    return false;
                }
            }
            //however, if we leave the for-loop: return true
            return true;
        }
    }


}

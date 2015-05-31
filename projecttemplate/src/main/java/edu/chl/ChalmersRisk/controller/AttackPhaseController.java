package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.Maps;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.view.GameBoard;
import edu.chl.ChalmersRisk.view.WinView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

/**
 * Created by Malin on 2015-05-20.
 */
public class AttackPhaseController implements Controller {

    private Player player;
    private GameBoard gameBoard;
    private boolean canAttack;
    private Territory attackFrom;
    private TerritoryView attackButton;
    private Maps map;

    public AttackPhaseController(Player player, GameBoard gameBoard){
        this.player = player;
        this.gameBoard = gameBoard;
        canAttack = false;
        map = gameBoard.getMap();

        TerritoryView[] territoryViews = gameBoard.getTerritoryViews();
        for (TerritoryView tv: territoryViews) {
            //tv.setOnAction(new ButtonPressed());
            tv.getImage().setOnMouseClicked(new ButtonPressed());
        }

    }


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

                    //check if the player won the game
                    if(playerWon()){
                        //TODO : some sort of endGame method.. somewhere? ChalmersRisk or here...?
                        new WinView();
                        gameBoard.setMessage("GRATTIS DU VANN!!");

                    }


                }

                canAttack = attackFrom.getAmountOfTroops()>1;
                attackButton.removeFocused();



                //but no matter what, we should update

                gameBoard.update(2);

            }



        }

        public boolean territoryOwnedBySomeoneElse(Territory territory){
            return !territory.isAvailableTo(player);
        }

        public boolean playerWon(){

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

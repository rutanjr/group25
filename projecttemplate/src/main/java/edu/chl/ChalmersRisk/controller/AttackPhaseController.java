package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.view.GameBoard;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by Malin on 2015-05-20.
 */
public class AttackPhaseController implements Controller {

    private Player player;
    private GameBoard gameBoard;
    private boolean canAttack;
    private Territory attackFrom;

    public AttackPhaseController(Player player, GameBoard gameBoard){

        this.player = player;
        this.gameBoard = gameBoard;
        canAttack = false;

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


            //first see if there are more than one troop on the territory


            //first if the player chooses a territory that he owns
            if(btn.getTerritory().getOwner().equals(player)  && btn.getTerritory().getAmountOfTroops() > 1){
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
            else if(territoryOwned(btn.getTerritory()) && canAttack){

                Territory defendingTerritory = btn.getTerritory();

                //if this returns true it means that the defender territory got empty
                if(player.combat(attackFrom,defendingTerritory)){
                    //and if it got empty we should move the attacker players
                   player.receiveTroops((ArrayList)attackFrom.getTroops());

                    for(int i = 0; i<=attackFrom.getAmountOfTroops();i++){
                        player.placeTroops(defendingTerritory,1);
                    }


                    player.moveTroops(attackFrom, defendingTerritory, attackFrom.getAmountOfTroops() - 1);


                    attackFrom.removeTroops(attackFrom.getAmountOfTroops() - 1);
                    gameBoard.setMessage("");
                }

                canAttack = attackFrom.getAmountOfTroops()>1;


                //but no matter what, we should update

                gameBoard.update(2);

            }



        }

        public boolean territoryOwned(Territory territory){
            return !territory.isAvailableTo(player);
        }
    }


}

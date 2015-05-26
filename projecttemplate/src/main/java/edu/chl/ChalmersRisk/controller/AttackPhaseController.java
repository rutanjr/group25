package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryButton;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import javafx.event.Event;
import javafx.event.EventHandler;

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

        TerritoryButton[] territoryButtons = gameBoard.getButtons();
        for (TerritoryButton tb: territoryButtons){
            tb.setOnAction(new ButtonPressed());
        }

    }


    private class ButtonPressed implements EventHandler {
        @Override
        public void handle(Event event) {

            TerritoryButton btn = (TerritoryButton)event.getSource();


            //first see if there are more than one troop on the territory


            //first if the player chooses a territory that he owns
            if(btn.getTerritory().getOwner().equals(player)  && btn.getTerritory().getAmountOfTroops() > 1){
                gameBoard.setMessage("Välj nu ett område att attackera!");
                canAttack = true;
                attackFrom = btn.getTerritory();
            }else if(btn.getTerritory().getAmountOfTroops() <= 1 && btn.getTerritory().getOwner().equals(player)){
                gameBoard.setMessage("Du har för få trupper här");
            }
            //if the territory clicked is neither empty nor owned by the player itself : ergo it's owned by another player
            // and if the player can attack
            else if(territoryOwned(btn.getTerritory()) && canAttack){

                Territory defendingTerritory = btn.getTerritory();

                //if this returns true it means that the defender territory got empty
                if(ChalmersRisk.combat(attackFrom,defendingTerritory)){
                    //and if it got empty we should move the attacker players
                    player.receiveTroops((ArrayList)attackFrom.getTroops());
                    
                    for(int i = 0; i<attackFrom.getAmountOfTroops();i++){
                        player.placeTroops(btn.getTerritory(),1);
                    }


                    attackFrom.removeTroops(attackFrom.getAmountOfTroops() - 1);
                    gameBoard.setMessage("");
                    canAttack = false;
                }


                //but no matter what, we should update

                gameBoard.update(2);

            }



        }

        public boolean territoryOwned(Territory territory){
            return !territory.isAvailableTo(player);
        }
    }


}

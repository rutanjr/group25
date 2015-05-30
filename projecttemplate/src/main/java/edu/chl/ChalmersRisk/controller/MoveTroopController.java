package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

/**
 * Created by Malin on 2015-05-29.
 */
public class MoveTroopController implements Controller {


    private Player player;
    private GameBoard gameBoard;
    private TerritoryView moveFrom;
    private int amtOfTroops;

    public MoveTroopController(Player player, GameBoard gameBoard){

        this.player = player;
        this.gameBoard = gameBoard;
        amtOfTroops = 0;


        TerritoryView[] territoryViews = gameBoard.getTerritoryViews();
        for (TerritoryView tv: territoryViews) {
            //tv.setOnAction(new ButtonPressed());
            tv.getImage().setOnMouseClicked(new ButtonPressed());
        }
    }



    /**
     * Class for the button presses, the pressed territories in the game. Moving troops.
     */
    private class ButtonPressed implements EventHandler {
        @Override
        public void handle(Event event) {

            TerritoryView btn = (TerritoryView)((ImageView) event.getSource()).getParent();

            //first let the player pick a territory
            if(btn.getTerritory().getOwner().equals(player) && btn.getTerritory().getAmountOfTroops()>1 && amtOfTroops == 0){

                amtOfTroops ++;
                moveFrom = btn;
                gameBoard.setGameText("You want to move troops from "+moveFrom.getTerritory().getName()+"\n Troops to be moved: "+amtOfTroops);

            }else if(amtOfTroops > 0 && btn.equals(moveFrom)){  // if we have picked a territory to move from

                amtOfTroops ++;

                gameBoard.setGameText("You want to move troops from "+moveFrom.getTerritory().getName()+"\n Troops to be moved: "+amtOfTroops);

            }else if(amtOfTroops > 0 && btn.getTerritory().getOwner().equals(player) && !btn.equals(moveFrom) ){ //if we pick a territory to move to

                player.moveTroops(moveFrom.getTerritory(),btn.getTerritory(),amtOfTroops);

                //reset
                amtOfTroops = 0;

                gameBoard.setGameText("Your turn is over!");

            }

            gameBoard.update(2);



        }
    }


}

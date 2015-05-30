package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

/**
 * Created by Malin on 2015-05-12.
 */
public class PlaceTroopController implements Controller {


    private Player player;
    private GameBoard gameBoard;

    public PlaceTroopController(Player player, GameBoard gameBoard){
        this.player = player;
        this.gameBoard = gameBoard;

        //fetches the territorys to be able to set the presses.
        TerritoryView[] territoryViews = gameBoard.getTerritoryViews();

        //setting press listeners for each territory.
        for (TerritoryView tv: territoryViews){
            tv.getImage().setOnMouseClicked(new ButtonPressed());
        }

        gameBoard.setGameText("Player "+player.getName()+"'s turn\nTroops to place:"+player.getTroopsToPlace().size());
    }

    /**
     * Class for the button presses, the pressed territories in the game. Placing troops.
     */
    private class ButtonPressed implements EventHandler {
        @Override
        public void handle(Event event) {
            if(!player.getTroopsToPlace().isEmpty()){
                //Clicks on the image view, we want the TerritoryView here. --> .getParent();
                TerritoryView btn = (TerritoryView)((ImageView) event.getSource()).getParent();

                //see if the player owns the territory OR if the territory is empty
                if(btn.getTerritory().getOwner().equals(Constants.EMPTY_PLAYER) || player.isMyTerritory(btn.getTerritory())){

                    player.placeTroops(btn.getTerritory(),1);
                    gameBoard.setGameText("Player "+player.getName()+"'s turn\nTroops to place:"+player.getTroopsToPlace().size());

                    //the number is representing the phase.
                    gameBoard.update(1);
                }
            }else{
                gameBoard.setMessage("You don't have any troops left to place.\nPlease press the next button :)");
            }
        }
    }




}

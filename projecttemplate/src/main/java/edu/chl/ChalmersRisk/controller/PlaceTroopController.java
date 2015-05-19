package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryButton;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import edu.chl.ChalmersRisk.utilities.Constants;
import edu.chl.ChalmersRisk.view.GameBoard;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

/**
 * Created by Malin on 2015-05-12.
 */
public class PlaceTroopController implements Controller {


    private Player player;
    private GameBoard gameBoard;

    public PlaceTroopController(Player player, GameBoard gameBoard){
        this.player = player;
        this.gameBoard = gameBoard;


        TerritoryButton[] territoryButtons = gameBoard.getButtons();
        for (TerritoryButton tb: territoryButtons){
            tb.setOnAction(new ButtonPressed());
        }


    }


    private class ButtonPressed implements EventHandler {
        @Override
        public void handle(Event event) {
            if(!player.getTroopsToPlce().isEmpty()){
                TerritoryButton btn = (TerritoryButton)event.getSource();
                //see if the player owns the territory OR if the territory is empty
                if(btn.getTerritory().getOwner().equals(Constants.EMPTY_PLAYER) || player.isMyTerritory(btn.getTerritory())){
                    player.placeTroops(btn.getTerritory(),1);

                    //the number is representing the phase.
                    gameBoard.update(1);
                }
            }
        }
    }




}

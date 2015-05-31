package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.gui.TerritoryView;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.view.GameBoard;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

/**
 * Created by Malin on 2015-05-29.
 *
 *
 * Class used to control the GameBoard during the move troop phase.
 */
public class MoveTroopController implements Controller {


    private final Player player;
    private final GameBoard gameBoard;
    private TerritoryView moveFrom;
    private int amtOfTroops;

    /**
     * Class constructor
     * @param player the player who is in control
     * @param gameBoard the view
     */
    public MoveTroopController(Player player, GameBoard gameBoard){

        this.player = player;
        this.gameBoard = gameBoard;
        amtOfTroops = 0;

        //sets listener to all the TerritoryViews
        TerritoryView[] territoryViews = gameBoard.getTerritoryViews();
        for (TerritoryView tv: territoryViews) {
            tv.getImage().setOnMouseClicked(new ButtonPressed());
        }

        //because move troops is the last phase, when you press the nextButton it's the next player's turn.
        gameBoard.getInfoStrip().getNextButton().setText("END TURN");
    }



    /**
     * Private class for the pressed territories in the game. Moves troops.
     */
    private class ButtonPressed implements EventHandler {
        @Override
        public void handle(Event event) {

            //the button being pressed
            TerritoryView btn = (TerritoryView)((ImageView) event.getSource()).getParent();

            //first let the player pick a territory
            if(btn.getTerritory().getOwner().equals(player) && btn.getTerritory().getAmountOfTroops() > 1 && amtOfTroops == 0){

                amtOfTroops ++;
                moveFrom = btn;
                gameBoard.setMessage("You want to move troops from " + moveFrom.getTerritory().getName() + "\n Troops to be moved: " + amtOfTroops);

            }else if(amtOfTroops > 0 && btn.equals(moveFrom) && amtOfTroops < moveFrom.getTerritory().getAmountOfTroops()-1){  // if we have picked a territory to move from

                amtOfTroops ++;
                gameBoard.setMessage("You want to move troops from " + moveFrom.getTerritory().getName() + "\n Troops to be moved: " + amtOfTroops);

            }else if(amtOfTroops > 0 && btn.getTerritory().getOwner().equals(player) && !btn.equals(moveFrom) ){ //if we pick a territory to move to

                if(player.moveTroops(moveFrom.getTerritory(),btn.getTerritory(),amtOfTroops)){ // the move was possible
                    gameBoard.setMessage("Your turn is over!");
                    //reset
                    amtOfTroops = 0;
                }



            }

            gameBoard.update(2);



        }
    }


}

package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Troop;

import java.util.ArrayList;

/**
 * A card that upon being drawn will grant you additional troops to deploy
 *
 * Created by chrh on 12-May-15.
 */
public class AdditionalTroopsCard implements ICard {

    private String title;
    private int bonusTroops;
    private String message;
    private Player currentPlayer;

    public AdditionalTroopsCard(Player currentPlayer, int bonusTroops ) {
        this.title = "Additional Troops";
        this.bonusTroops = bonusTroops;
        this.currentPlayer = currentPlayer;

        this.message = "Congratulations, thanks to your previous conquest " +
                "your army has been blessed with many new recruits " + this.bonusTroops + " new soldiers join your ranks.";
    }
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int phaseCheck() {
        return 0;
    }

    @Override
    public void turnCard() {

        ArrayList<Troop> listTemp = new ArrayList<Troop>();

        for (int i = 0; i < bonusTroops; i++) {
            listTemp.add(new Troop(currentPlayer));
        }
        listTemp.addAll(currentPlayer.getTroopsToPlace());

        currentPlayer.receiveTroops(listTemp);
        //TODO TurnCard needs to be called AFTER the initial troops has been recieved ADD PRE/POST CONDITIONS
    }
}

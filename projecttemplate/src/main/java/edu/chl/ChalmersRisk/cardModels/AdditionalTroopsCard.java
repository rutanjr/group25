package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;
import edu.chl.ChalmersRisk.model.Player;

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
    public void turnCard() {
        //TODO effect of the card should be resolved before the player gets to place his troops and increase that amount.
    }
}

package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;

import java.util.ArrayList;

/**
 * A card that upon being drawn will switch a random territory with one random territory owned by your opponent.
 *
 * Created by chrh on 2015-05-19.
 */
public class TerritoryChangeCard implements ICard {

    private String title = "Traitors! and new Allies.";
    private Player playerA, playerB;

    public TerritoryChangeCard(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return "Surprise midterm exams! Due to panic some soldiers have switched alliances.";
    }

    @Override
    public int phaseCheck() {
        return 0;
    }

    @Override
    public void turnCard() {
        ArrayList tempListA, tempListB;
        int tempRandA, tempRandB;
        Territory tempTerrA, tempTerrB;

        tempListA = playerA.getTerritories();
        tempListB = playerB.getTerritories();
        tempRandA = (int)((Math.random()*tempListA.size()));
        tempRandB = (int)((Math.random()*tempListB.size()));

        tempTerrA = (Territory)tempListA.get(tempRandA);
        tempTerrB = (Territory)tempListB.get(tempRandB);

        tempTerrA.setnewOwner(playerB);
        tempTerrB.setnewOwner(playerA);

        //TODO check if this works
    }
}

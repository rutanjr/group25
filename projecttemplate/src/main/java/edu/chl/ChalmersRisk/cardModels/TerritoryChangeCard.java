package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;
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
    public void turnCard() {
        private ArrayList tempListA, tempListB;
        private int tempRandA, tempRandB;
        private Territory tempTerrA, tempTerrB;

        tempListA = playerA.getTerritories();
        tempListB = playerB.getTerritories();
        tempRandA = (int)(Math.random()*tempListA.size()-1);
        tempRandB = (int)(Math.random()*tempListB.size()-1);

        tempTerrA = (Territory)tempListA.get(tempRandA);
        tempTerrB = (Territory)tempListB.get(tempRandB);

        tempTerrA.setnewOwner(playerB);
        tempTerrB.setnewOwner(playerA);

        //TODO check if this works
    }
}

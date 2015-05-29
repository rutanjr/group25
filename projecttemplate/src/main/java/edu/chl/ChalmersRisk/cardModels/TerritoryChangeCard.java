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

    private String title, message;
    private Player playerA, playerB;

    public TerritoryChangeCard(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.title  = "Traitors! And new Allies.";
        this.message = "Surprise midterm exams! Due to panic some students have switched alliances.";
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
        ArrayList tempListA, tempListB;
        int tempRandA, tempRandB;
        Territory tempTerrA, tempTerrB;

        tempListA = playerA.getTerritories();
        tempListB = playerB.getTerritories();
        tempRandA = (int)((Math.random()* (tempListA.size() - 1)));
        tempRandB = (int)((Math.random()* (tempListB.size() - 1)));

        tempTerrA = (Territory)tempListA.get(tempRandA);
        tempTerrB = (Territory)tempListB.get(tempRandB);

        playerA.removeTerritory(tempTerrA);
        playerB.removeTerritory(tempTerrB);

        playerA.addTerritory(tempTerrB);
        playerB.addTerritory(tempTerrA);
    }
}

package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.ICard;

/**
 * A card that upon being drawn will switch a random territory with one random territory owned by your opponent.
 *
 * Created by chrh on 2015-05-19.
 */
public class TerritoryChangeCard implements ICard {

    private String title = "Traitors! and new Allies.";

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
        //TODO
    }
}

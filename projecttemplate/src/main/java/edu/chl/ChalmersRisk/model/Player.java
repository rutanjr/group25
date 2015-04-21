package edu.chl.ChalmersRisk.model;

import java.util.ArrayList;

/**
 * Created by rutanjr on 2015-03-31.
 * Keeps track of the name of the player mainly.
 */
public class Player {

    private String name;
    private ArrayList<Territory> territories = new ArrayList<Territory>();
    private ArrayList<Troop> placedTroops = new ArrayList<Troop>();
    private ArrayList<Troop> troopsToPlace;
    //TODO territory reference, troop reference

    public Player(String name){
        this.name = name;
    }

    public void receiveTroops (ArrayList<Troop> received){
        troopsToPlace = received;
    }

    public ArrayList<Troop> getPlaceTroops() {
        return placedTroops;
    }

    public ArrayList<Troop> getTroopsToPlce() {
        return troopsToPlace;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public String getName() {
        return name;
    }
}

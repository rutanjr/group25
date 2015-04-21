package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-03-31.
 * Keeps track of the name of the player mainly.
 */
public class Player {

    private String name;
    //TODO territory reference, troop reference

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

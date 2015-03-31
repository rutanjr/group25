package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-03-31.
 * This is to create troops, each player recieves troops each turn and this is the class that represents them.
 * The troops can't change owner, the troops belongs to a player until death. When they die they "dissapear" and the player receives new troops next turn.
 *
 */
public class Troop {
    private final Player owner;

    public Troop(Player owner) {
        this.owner = owner;
    }

}

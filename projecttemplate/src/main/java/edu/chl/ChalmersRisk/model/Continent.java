package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to represent continents. Controlling an entire continent grants bonus troops.
 */
public class Continent {

    private String name;

    //TODO territory references.

<<<<<<< Updated upstream
    //player owns all contintent Bololean? Player? Where to check this?
=======
    //player owns all contintent Bololean? Player?
>>>>>>> Stashed changes

    public Continent(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}

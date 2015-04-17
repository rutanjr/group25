package edu.chl.ChalmersRisk.model;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to represent continents. Controlling an entire continent grants bonus troops.
 */
public class Continent {

    private String name;

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

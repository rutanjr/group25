package edu.chl.ChalmersRisk.model;

import edu.chl.ChalmersRisk.utilities.Constants;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by rutanjr on 2015-03-31.
 * A class to represent each territory. Certain territories together is a continent. All territories belongs to a continent.
 *
 * @revisedBy Robin Jansson
 * @revisedBy Malin Thelin
 */
public class Territory {

    // Variables

    private String name;
    private Player owner;
    private Continent continent;
    private ArrayList<Territory> adjacentTerritories = null;
    private ArrayList<Troop> troops = new ArrayList<>();
    private Point2D.Double pos;
    private String url;

    // Constructors

    public Territory(String name){
        this(name, Constants.EMPTY_CONTINENT);
    }

    public Territory(String name, Continent continent) {
        this(name, continent,Constants.EMPTY_PLAYER);
    }

    public Territory(String name, Continent continent, Player player) {
        this.name = name;
        this.continent = continent;
        this.owner = player;
        this.adjacentTerritories = new ArrayList<Territory>();
    }

    public Territory(String name, Continent continent, Point2D.Double pos, String url) {
        this.name = name;
        this.continent = continent;
        this.owner = Constants.EMPTY_PLAYER;
        this.adjacentTerritories = new ArrayList<Territory>();
        this.pos = pos;
        this.url = url;
    }

    // Command - Methods

    /**
     * Add troops to a Territory.
     * @param newTroops an positive integer that indicates the number of troops that should be added to the current Territory.
     * @throws java.lang.IllegalArgumentException if the player sends a negative integer.
     */
    public void addTroops(int newTroops) {
        if (newTroops < 0) {
            throw new IllegalArgumentException("Tried to add a negative amount of troops");
        }

        for (int i = newTroops; i > 0; i--) {
            troops.add(new Troop(this.getOwner()));
        }
    }

    /**
     * Removes troops from a Territory.
     * @param remTroops an positive integer that indicates the number of troops that should be removed from the current Territory.
     * @throws java.lang.IllegalArgumentException if the player sends a negative integeror tries to remove more troops than the Territory owns.
     */
    public void removeTroops(int remTroops) {
        if (remTroops < 0 || remTroops > this.getAmountOfTroops()) {
            throw new IllegalArgumentException("Tried to remove a negative amount of troops or the player has tried to remove more troops than the Territory owns");
        }

        for (int i = remTroops; i > 0; i--) {
            troops.remove((troops.size() - 1));
        }
    }

    public void setnewOwner(Player newOwner) {

        owner.loseTerritory(this);
        owner = newOwner;
        owner.addTerritories(this);
    }

    /**
     * Adds an arraylist as adjacent territories.
     */
    public void addAdjacent(ArrayList<Territory> newAdjacent) {
          for (int i = 0; i <newAdjacent.size(); i++) {
            addNeighbor(newAdjacent.get(i));
        }
    }

    /*
     * Atempts to create a conection back to the main territory from the newly added neighbor
     * as to prevent error where A is a neighbor to B, but B isn't a neighbor to A.
     */
    public void addNeighbor(Territory neighbor) {

        // Adds input territory as neighbor to active territory
        if (!this.getAdjacentTerritories().contains(neighbor)) {
            this.getAdjacentTerritories().add(neighbor);
        }

        // Adds active territory as neighbor to input territory
        if (!neighbor.getAdjacentTerritories().contains(this)) {
            neighbor.getAdjacentTerritories().add(this);
        }
    }

    // Query - Methods

    public String getName() {
        return this.name;
    }

    public Continent getContinent() {
        return this.continent;
    }

    /**
     * @return Returns the owner of the current Territory.
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * @return returns the amount of troops in the current Territory.
     */
    public int getAmountOfTroops() {return this.troops.size();}

    /**
     * @return returns a List of troops in the current Territory.
     */
    public List<Troop> getTroops() {return this.troops;}

    /**
     * @return returns a list with Territories that are adjacent to the current Territory.
     */
    public ArrayList<Territory> getAdjacentTerritories() {
        return adjacentTerritories;
    }

    /**
     * @param possibleNeighbor a Territory that might be adjacent to the current Territory
     * @return returns true if the Territory in the argument is adjacent to the current Territory.
     */
    public boolean isAdjacent(Territory possibleNeighbor) {
        return this.getAdjacentTerritories().contains(possibleNeighbor);
    }

    //TODO JAVADOC
    public boolean isAvailableTo(Player player){
        return (owner.  equals(Constants.EMPTY_PLAYER) || owner.equals(player));
    }


    public String getUrl() {
        return url;
    }

    public Point2D.Double getPos() {
        return pos;
    }

    /**
     * A method for finding a path of territories that is owned by the same player
     * between two territories. Based on a depth first algorithm.
     * @param toTerritory the territory to find.
     * @return
     */
    public boolean isConnectedTo(Territory toTerritory) {
        boolean hasPath = false;
        Stack<Territory> toTest = new Stack<Territory>();
        List<Territory> discovered = new LinkedList<Territory>();
        toTest.push(this);

        // This is the search algorithm
        // it has an extra condition that end it if a path from A to B has been discovered.
        while (!toTest.isEmpty() && !hasPath) {
            Territory search = toTest.pop();
            if (search.equals(toTerritory)) {
                hasPath = true;
                //This could be move to anywhere with a return true statement.
            } else {
                discovered.add(search);
                for (Territory it : search.getAdjacentTerritories()) {
                    if (it.getOwner().equals(owner)) {
                        Boolean isUndiscovered = true;

                        //Loop through the discovered territories to see if it has already been searched.
                        int i = 0;
                        while (i < discovered.size() && isUndiscovered) {
                            if (discovered.get(i).equals(it)) {
                                isUndiscovered = false;
                            }
                            i++;
                        }

                        if (isUndiscovered) {
                            toTest.push(it);
                        }
                    }
                }
            }
        }
        return hasPath;
    }


}
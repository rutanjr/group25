package edu.chl.ChalmersRisk.view;


import edu.chl.ChalmersRisk.model.*;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Created by rutanjr on 2015-03-31.
 * A board for the risk game. This class will do the graphical work, holding the map and painting/repainting it.
 */
public class GameBoard {

    private ArrayList<Continent> continents;
    private ArrayList<ArrayList<Territory>> territorys; //ArrayList(size: amount of continents) of ArrayLists with territorys (for each continent).
    private Button[] territoryButtons;

    public GameBoard() {
    }

    public GameBoard(ArrayList<Continent> continents) {


        for (Continent c: continents)
            System.out.println(c.getName());

        System.out.println("Got into gameboard constructor");
        this.continents = continents;
        for (Continent c: continents)
            System.out.println(c.getName());
        territorys = new ArrayList<ArrayList<Territory>>(continents.size());


        int i = 0;
        for(ArrayList<Territory> a : territorys) {
            a = (ArrayList<Territory>) continents.get(i).getTerritories();
            i++;
        }
        i = 0;
        for (ArrayList<Territory> a: territorys) {
            for (Territory t: a) {
                System.out.println(t.getName());
                territoryButtons[i] = new Button(t.getName() + " : " + t.getTroops());
                i++;
            }
        }
    }

    public Button[] getTerritoryButtons() {
        return territoryButtons;
    }

}

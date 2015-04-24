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

        this.continents = continents;
        territorys = new ArrayList<ArrayList<Territory>>(continents.size());

        int i = 0;
        for(ArrayList<Territory> a : territorys) {
            //a = continents.get(i).getTerritories();
            i++;
        }
        i = 0;
        for (ArrayList<Territory> a: territorys) {
            for (Territory t: a) {
                //territoryButtons[i] = new Button(t.getName() + " : " + t.getTroops().size());
                i++;
            }
        }
    }

    public Button[] getTerritoryButtons() {
        return territoryButtons;
    }

}

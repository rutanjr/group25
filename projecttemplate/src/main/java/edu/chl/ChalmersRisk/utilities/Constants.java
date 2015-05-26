package edu.chl.ChalmersRisk.utilities;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;

/**
 * Created by Malin on 2015-04-22.
 *
 * A class to keep constants in.
 */
public class Constants {

    public static final Player EMPTY_PLAYER = new Player("EMPTY","FFFFFF");
    public static final int width = 500;
    public static final int height = 500;
    public static final Continent EMPTY_CONTINENT = new Continent("EMPTY",0);
    public static final Territory EMPTY_TERRITORY = new Territory("EMPTY");
    public static final int begTroops = 3;

}

package edu.chl.ChalmersRisk.utilities;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


/**
 * Created by Malin on 2015-04-22.
 * @revisedBy rutanjr, Oskar Rutqvist
 * A class to keep constants in.
 */
public class Constants {

    public static final Player EMPTY_PLAYER = new Player("EMPTY","FFFFFF");
    public static final int width = 500;
    public static final int height = 500;
    public static final Continent EMPTY_CONTINENT = new Continent("EMPTY",0);
    public static final Territory EMPTY_TERRITORY = new Territory("EMPTY");
    public static final int begTroops = 3;


    public static DropShadow createDropShadow(Color color,double spread) {
        DropShadow ds = new DropShadow(10, color);
        ds.setSpread(spread);
        return ds;
    }

    public static DropShadow createDropShadow(){
        return createDropShadow(Color.GRAY, 1.0);
    }

    public static DropShadow createDropShadow(Color color){
      return createDropShadow(color,0.7);
    }

}

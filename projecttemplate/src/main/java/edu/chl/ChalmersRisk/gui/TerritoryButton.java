package edu.chl.ChalmersRisk.gui;

import edu.chl.ChalmersRisk.model.Territory;
import javafx.scene.control.Button;

/**
 * Created by Malin on 2015-05-14.
 */
public class TerritoryButton extends Button {


    private Territory territory;

    public TerritoryButton(){
        super();
    }

    public TerritoryButton(Territory territory){
        super();
        this.territory = territory;
        this.setStyle("-fx-background-color: #FFFFFF;");
    }


    public void paintButton() {

        this.setStyle("-fx-background-color: #" + this.getTerritory().getOwner().getColor()+";");

    }


    public Territory getTerritory(){
        return territory;
    }


    public void setTerritory(Territory territory){
        this.territory = territory;
    }
}
